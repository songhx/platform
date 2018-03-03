package com.platform.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.constants.CarpoolConstant;
import com.platform.constants.CommonConstant;
import com.platform.dto.CarpoolCarVo;
import com.platform.dto.CarpoolPublishVo;
import com.platform.entity.CarpoolCar;
import com.platform.entity.CarpoolPublish;
import com.platform.entity.CarpoolUser;
import com.platform.service.ApiCarpoolUserService;
import com.platform.service.CarpoolCarService;
import com.platform.service.CarpoolPublishService;
import com.platform.util.ApiBaseAction;
import com.platform.util.CarPoolUtil;
import com.platform.utils.GEOUtils;
import com.platform.vo.RequestPageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zuimeideshiguang on 18/2/13.
 */
@RestController
@RequestMapping("/api/car/publish")
public class ApiCarpoolPublishController extends ApiBaseAction {


    @Autowired
    private CarpoolCarService carpoolCarService;

    @Autowired
    private CarpoolPublishService carpoolPublishService;

    @Autowired
    private ApiCarpoolUserService apiCarpoolUserService;


    /**
     * 车找人 人找车查询
     * @param carpoolPublish
     * @return
     */
    @RequestMapping("list")
    public Object list(@RequestBody CarpoolPublishVo carpoolPublish) {
        //数据可用
        carpoolPublish.setStatus(CarpoolConstant.PUBLISHING_STATUS); // 发布中
        carpoolPublish.setDataStatus(CommonConstant.USEABLE_STATUS); // 可用

        if (null == carpoolPublish.getUserType()){
            return toResponsFail("未知查询");
        }
        return toResponsSuccess(carpoolPublishService.queryPublishListByPage(carpoolPublish));
    }

    /**
     * 查询拼车发布历史
     * @param carpoolPublish
     * @return
     */
    @RequestMapping("history")
    public Object history(@RequestBody CarpoolPublishVo carpoolPublish) {
        //数据可用
        carpoolPublish.setDataStatus(CommonConstant.USEABLE_STATUS); // 可用

        PageHelper.startPage(carpoolPublish.getStart(), carpoolPublish.getLimit(), true, false); //设置分页

        List<CarpoolPublish> list = carpoolPublishService.select(carpoolPublish);

        PageInfo<CarpoolPublish> pageInfo = new PageInfo<CarpoolPublish>(list);

        Map<String, Object> returnMap = new HashMap<>();
        //设置返回参数
        returnMap.put("totalPage",pageInfo.getPages());
        returnMap.put("list", list);

        return toResponsSuccess(returnMap);
    }

    /**
     * 查询单条行程信息
     * @param carpoolPublish
     * @return
     */
    @RequestMapping("queryTrip")
    public Object queryTrip(@RequestBody CarpoolPublish carpoolPublish) {
        if (null == carpoolPublish.getId()){
            return toResponsFail("参数错误");
        }
        CarpoolPublish publish = new CarpoolPublish();
        publish.setId(carpoolPublish.getId());
        publish.setDataStatus(CommonConstant.USEABLE_STATUS); // 可用
        CarpoolPublish  p =carpoolPublishService.selectOne(publish);
        if (p != null){
            CarpoolUser u = new CarpoolUser();
            u.setId(p.getPublishUserId());
            CarpoolUser user = apiCarpoolUserService.selectOne(u);
            if (user != null){
                p.setUserName(user.getNickName());
                p.setAvatar(user.getAvatar());
            }

        }
        return toResponsSuccess(p);
    }


    /**
     * 发布行程
     * @param carpoolPublish
     * @return
     */
    @RequestMapping("publish")
    public Object publish(@RequestBody  CarpoolPublish carpoolPublish) {

        if (null == carpoolPublish.getPublishUserId()){
            return  toResponsFail("用户在系统中不存在，请先登录！");
        }
        Date time = new Date();
        carpoolPublish.setStartPointGeo(GEOUtils.cateGeoCode(carpoolPublish.getStartPointLongitude(),carpoolPublish.getStartPointLatitude()));
        carpoolPublish.setDestinationGeo(GEOUtils.cateGeoCode(carpoolPublish.getDestinationLongitude(),carpoolPublish.getDestinationLatitude()));
        carpoolPublish.setCreateTime(time);
        carpoolPublish.setUpdateTime(time);
        fillCarInfo(carpoolPublish); // 填充车辆信息

        carpoolPublishService.insertSelective(carpoolPublish);

        return toResponsSuccess("发布成功！");
    }

    /**
     * 更新行程
     * @param carpoolPublish
     * @return
     */
    @RequestMapping("updateTrip")
    public Object updateTrip(@RequestBody  CarpoolPublish carpoolPublish) {

        if (null == carpoolPublish.getId()){
            return  toResponsFail("参数错误！");
        }
        Date time = new Date();
        carpoolPublish.setStartPointGeo(GEOUtils.cateGeoCode(carpoolPublish.getStartPointLongitude(),carpoolPublish.getStartPointLatitude()));
        carpoolPublish.setDestinationGeo(GEOUtils.cateGeoCode(carpoolPublish.getDestinationLongitude(),carpoolPublish.getDestinationLatitude()));
        carpoolPublish.setUpdateTime(time);
        fillCarInfo(carpoolPublish); // 填充车辆信息

        carpoolPublishService.updateByPrimaryKeySelective(carpoolPublish);

        return toResponsSuccess("更新成功！");
    }


    @RequestMapping("cancel")
    public Object cancelPublish(@RequestBody  CarpoolPublish carpoolPublish) {

        if (null == carpoolPublish.getId()){
            return  toResponsFail("参数不足！");
        }
        Date time = new Date();

        carpoolPublish.setUpdateTime(time);

        carpoolPublishService.cnacelPublish(carpoolPublish);

        return toResponsSuccess("发布成功！");
    }

    //填充车辆信息
    private void fillCarInfo(CarpoolPublish carpoolPublish){
        CarpoolCarVo carpoolCarVo = null;
        CarpoolCar cc =  new CarpoolCar();
        cc.setUserId(carpoolPublish.getPublishUserId());
        if (carpoolPublish.getCarId() != null) {cc.setId(carpoolPublish.getCarId());}
        List<CarpoolCar> carpoolCarList = carpoolCarService.select(cc);
        if (carpoolCarList != null && carpoolCarList.size() > 0){
            carpoolCarVo = CarPoolUtil.getCarInfo(carpoolCarList.get(0));
        }

        if (null != carpoolCarVo){
            carpoolPublish.setCarBrand(carpoolCarVo.getCarBrand());
            carpoolPublish.setCarColor(carpoolCarVo.getCarColor());
            carpoolPublish.setCarNo(carpoolCarVo.getCarNo());
            carpoolPublish.setCarType(carpoolCarVo.getCarType());
        }
    }
}
