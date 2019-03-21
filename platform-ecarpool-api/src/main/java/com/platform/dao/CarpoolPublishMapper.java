package com.platform.dao;

import com.platform.dto.CarpoolPublishVo;
import com.platform.dto.StatCarpoolVo;
import com.platform.entity.CarpoolPublish;
import com.platform.service.IBasicSetMapper;

import java.util.List;

/**
 * Created by zuimeideshiguang on 18/2/12.
 */
public interface CarpoolPublishMapper extends IBasicSetMapper<CarpoolPublish> {

    /**
     * 查询拼车行程信息,只提供简单信息查询
     * @param carpoolPublish
     * @return
     */
    List<CarpoolPublish> selectTrips(CarpoolPublishVo carpoolPublish);

    /**
     * 统计用户拼车次数
     * @param carpoolPublish
     * @return
     */
    List<StatCarpoolVo> statCarpoolTimes(CarpoolPublishVo carpoolPublish);
}
