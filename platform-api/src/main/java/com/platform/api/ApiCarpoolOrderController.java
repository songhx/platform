package com.platform.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.constants.CarpoolConstant;
import com.platform.constants.CommonConstant;
import com.platform.entity.CarpoolOrder;
import com.platform.entity.CarpoolOrderLog;
import com.platform.entity.UserVo;
import com.platform.service.ApiCarpoolOrderService;
import com.platform.service.CarpoolOrderLogService;
import com.platform.util.ApiBaseAction;
import com.platform.vo.RequestPageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zuimeideshiguang on 18/2/13.
 * 预约处理控制器
 */
@RestController
@RequestMapping("/api/car/order")
public class ApiCarpoolOrderController extends ApiBaseAction {

   @Autowired
   private ApiCarpoolOrderService apiCarpoolOrderService;

   @Autowired
   private CarpoolOrderLogService carpoolOrderLogService;


    /**
     * 预约或者抢单
     * @param carpoolOrder
     * @return
     */
    @RequestMapping("order")
    public Object order(CarpoolOrder carpoolOrder) {

        ///校验
        if (null == carpoolOrder.getPublishId()){
            return  toResponsFail("行程不存在！");
        }
        if (null == carpoolOrder.getOrderUserId()){
            return  toResponsFail("用户在系统中不存在，请先登录！");
        }

        try {
            Date time = new Date();
            carpoolOrder.setCreateTime(time);
            carpoolOrder.setUpdateTime(time);
            apiCarpoolOrderService.insertSelective(carpoolOrder);
            saveLogs(carpoolOrder,carpoolOrder.getOrderUserName() + "预订成功！");
            return toResponsSuccess("预订成功！");
        }catch (Exception e){
            //saveLogs(loginUser,carpoolOrder.getId(),carpoolOrder.getOrderUserName() + "预订失败！");
            return  toResponsFail("预订失败！");
        }

    }

    /**
     * 确认或取消
     * @param carpoolOrder
     * @return
     */
    @RequestMapping("confirmOrRefuse")
    public Object confirmOrRefuseOrder( CarpoolOrder carpoolOrder) {

        ///校验
        if (null == carpoolOrder.getId()){
            return  toResponsFail("参数错误！");
        }
        try {
            Date time = new Date();
            carpoolOrder.setUpdateTime(time);
            apiCarpoolOrderService.updateByPrimaryKeySelective(carpoolOrder);
            String msg = carpoolOrder.getStatus().intValue() == CarpoolConstant.FINISHED_STATUS ? "确认" : "拒绝";
            saveLogs(carpoolOrder,carpoolOrder.getOrderUserName() + msg + "预订！");
            return toResponsSuccess("操作成功！");
        }catch (Exception e){
            //saveLogs(loginUser,carpoolOrder.getId(),carpoolOrder.getOrderUserName() + "预订失败！");
            return  toResponsFail("操作失败！");
        }

    }

    /**
     * 拼车记录
     * @param requestPageParameter
     * @param carpoolOrder
     * @return
     */
    @RequestMapping("list")
    public Object list(RequestPageParameter requestPageParameter , CarpoolOrder carpoolOrder) {


        if (null == carpoolOrder.getOrderUserId()){
            return  toResponsFail("用户在系统中不存在，请先登录！");
        }
        PageHelper.startPage(requestPageParameter.getStart(), requestPageParameter.getLimit(), true, false); //设置分页

        carpoolOrder.setDataStatus(CommonConstant.USEABLE_STATUS); //可用
        List<CarpoolOrder> list = apiCarpoolOrderService.select(carpoolOrder);

        PageInfo<CarpoolOrder> pageInfo = new PageInfo<CarpoolOrder>(list);

        Map<String, Object> returnMap = new HashMap<>();
        //设置返回参数
        returnMap.put("total",pageInfo.getTotal());
        returnMap.put("list", list);

        return toResponsSuccess(returnMap);
    }


    //记录预约记录
    private void saveLogs(CarpoolOrder carpoolOrder , String content ){
        CarpoolOrderLog log = new CarpoolOrderLog();
        log.setContent(content);
        log.setOrderId(carpoolOrder.getId());
        log.setOperatorId(carpoolOrder.getOrderUserId());
        log.setOperatorName(carpoolOrder.getOrderUserName());
        carpoolOrderLogService.insertSelective(log);
    }
}
