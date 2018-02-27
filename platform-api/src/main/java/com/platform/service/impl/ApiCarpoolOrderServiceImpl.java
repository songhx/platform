package com.platform.service.impl;

import com.platform.constants.CarpoolConstant;
import com.platform.constants.CommonConstant;
import com.platform.dao.CarpoolOrderMapper;
import com.platform.dao.CarpoolPublishMapper;
import com.platform.dto.CarpoolOrderVo;
import com.platform.entity.CarpoolOrder;
import com.platform.entity.CarpoolPublish;
import com.platform.service.ApiCarpoolOrderService;
import com.platform.utils.GEOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zuimeideshiguang on 18/2/12.
 */
@Service("apiCarpoolOrderService")
public class ApiCarpoolOrderServiceImpl extends BasicSetServiceImpl<CarpoolOrder> implements ApiCarpoolOrderService {


    @Autowired
    private CarpoolOrderMapper carpoolOrderMapper;
    @Autowired
    private CarpoolPublishMapper carpoolPublishMapper;

    @Override
    public void order(CarpoolOrderVo carpoolOrder) {
        Date time = new Date();
        carpoolOrder.setCreateTime(time);
        carpoolOrder.setUpdateTime(time);
        carpoolOrder.setStartPointGeo(GEOUtils.cateGeoCode(carpoolOrder.getStartPointLongitude(),carpoolOrder.getStartPointLatitude()));
        carpoolOrder.setDestinationGeo(GEOUtils.cateGeoCode(carpoolOrder.getDestinationLongitude(),carpoolOrder.getDestinationLatitude()));
        carpoolOrder.setDataStatus(CommonConstant.USEABLE_STATUS);
        carpoolOrder.setStatus(CarpoolConstant.ORDERING_STATUS);
        carpoolOrder.setOperatorId(carpoolOrder.getOrderUserId());
        carpoolOrder.setOperatorName(carpoolOrder.getOrderUserName());
        int rs = carpoolOrderMapper.insertSelective(carpoolOrder);
        if (rs > 0){ // 修改发布信息
            CarpoolPublish carpoolPublish = new CarpoolPublish();
            carpoolPublish.setId(carpoolOrder.getPublishId());
            carpoolPublish.setDataStatus(CommonConstant.USEABLE_STATUS);
            CarpoolPublish publish = carpoolPublishMapper.selectOne(carpoolPublish);
            if (publish != null){
                if (publish.getPassengerNum() - 1 < 0){
                    throw new RuntimeException("位置已被预订完了");
                }
                carpoolPublish.setPassengerNum(publish.getPassengerNum() - 1 );
                carpoolPublishMapper.updateByPrimaryKeySelective(carpoolPublish);
            }

        }
    }
}
