package com.platform.service.impl;

import com.platform.dao.CarpoolPublishMapper;
import com.platform.dto.CarpoolPublishVo;
import com.platform.entity.CarpoolPublish;
import com.platform.service.CarpoolPublishService;
import com.platform.utils.GEOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zuimeideshiguang on 18/2/12.
 */
@Service("carpoolPublishService")
public class CarpoolPublishServiceImpl extends BasicSetServiceImpl<CarpoolPublish> implements CarpoolPublishService {

    @Autowired
    private CarpoolPublishMapper carpoolPublishMapper;

    @Override
    public Map<String, Object> queryPublishListByPage(CarpoolPublishVo carpoolPublish) {
        List<Object> geos = null;

        ///起点地理查询范围设置
        if(carpoolPublish.getStartPointLatitude() != null && carpoolPublish.getStartPointLongitude() != null){
            carpoolPublish.setCustLatitude(carpoolPublish.getStartPointLatitude());
            carpoolPublish.setCustLongitude(carpoolPublish.getStartPointLongitude());
        }
        if (carpoolPublish.getCustLatitude() != null && carpoolPublish.getCustLongitude() != null){
            geos = GEOUtils.createGeoCodes(carpoolPublish.getCustLongitude(),carpoolPublish.getCustLatitude());
        }
        carpoolPublish.setGeos(geos); // 设置地理查询范围

        ///设置终点地理范围
    /*    if (carpoolPublish.getDestinationLatitude() != null && carpoolPublish.getDestinationLongitude() != null){
            carpoolPublish.setDestinationGeos(GEOUtils.createGeoCodes(carpoolPublish.getDestinationLongitude(),carpoolPublish.getDestinationLatitude()));
        }*/

        List<CarpoolPublish> datas = carpoolPublishMapper.selectTrips(carpoolPublish);
        Map<String, Object> returnMap = new HashMap<>();
        //设置返回参数
        int total = datas != null ? datas.size() : 0;
        int page = total / carpoolPublish.getLimit().intValue() + ((total % carpoolPublish.getLimit().intValue() == 0) ? 0 : 1);
        returnMap.put("totalPage",page);
        returnMap.put("list", pageAndSort(carpoolPublish,datas));
        return returnMap;
    }

    /**
     * 分页加排序
     * @param carpoolPublish
     * @param datas
     * @return
     */
    private  List<CarpoolPublish> pageAndSort(CarpoolPublishVo carpoolPublish ,  List<CarpoolPublish> datas){
        if(null == datas) return null;
        List<CarpoolPublish> list = new ArrayList<>();
        if( null != datas && datas.size() > 0){
            ///填充距离
            for(CarpoolPublish po : datas){
                po.setDistance(GEOUtils.calcDistance(carpoolPublish.getCustLongitude(),carpoolPublish.getCustLatitude(),po.getStartPointLongitude(),po.getStartPointLatitude()));
            }
            //根据距离排序
            Collections.sort(datas, new Comparator<CarpoolPublish>() {
                public int compare(CarpoolPublish o1, CarpoolPublish o2) {
                    Double distanceA = o1.getDistance();
                    Double distanceB = o2.getDistance();
                    // 升序
                    return distanceA.compareTo(distanceB);
                }
            });
            if (datas.size() < carpoolPublish.getLimit().intValue()){
                return  datas;
            }
            //分页
            int startIndex = 0;
            int endIndex = 0;
            if(carpoolPublish.getStart() == 0){
                startIndex = 0;
                endIndex = carpoolPublish.getLimit();
            }else  if(carpoolPublish.getStart() == 1){
                startIndex = 0;
                endIndex =  carpoolPublish.getLimit() ;
            }else{
                startIndex = (carpoolPublish.getStart() - 1) *  carpoolPublish.getLimit();
                endIndex = carpoolPublish.getStart() *  carpoolPublish.getLimit() ;
            }
            if (datas.size() > 0 && datas.size() <= carpoolPublish.getStart() *  carpoolPublish.getLimit() ) endIndex = datas.size();
            if (startIndex > datas.size())return null;//防止前端错误传递start参数
            list =  datas.subList(startIndex,endIndex);

        }
        return 	list;
    }
}
