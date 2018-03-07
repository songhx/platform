package com.platform.dao;

import com.platform.entity.CarpoolOrder;
import com.platform.service.IBasicSetMapper;

/**
 * Created by zuimeideshiguang on 18/2/12.
 * 拼车预约单数据接口
 */
public interface CarpoolOrderMapper extends IBasicSetMapper<CarpoolOrder> {

    /**
     * 设置行程下预约过期
     * @param publishId
     * @return
     */
    int setOrderExpired(Integer publishId);
}
