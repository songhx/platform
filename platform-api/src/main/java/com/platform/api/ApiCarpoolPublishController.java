package com.platform.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.constants.CarpoolConstant;
import com.platform.constants.CommonConstant;
import com.platform.dto.CarpoolCarVo;
import com.platform.dto.CarpoolPublishVo;
import com.platform.entity.CarpoolCar;
import com.platform.entity.CarpoolPublish;
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
