package com.platform.service;

import com.platform.dto.CarpoolPublishVo;
import com.platform.entity.CarpoolPublish;

import java.util.Map;

/**
 * Created by zuimeideshiguang on 18/2/12.
 * 发布业务处理接口
 */
public interface CarpoolPublishService extends IBasicSetMapper<CarpoolPublish> {

    /**
     * 分页查询行程信息
     * @param carpoolPublish
     * @return
     */
    Map<String, Object> queryPublishListByPage(CarpoolPublishVo carpoolPublish);
}
