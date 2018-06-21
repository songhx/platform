package com.platform.dao;

import com.platform.entity.UserFormid;
import com.platform.service.IBasicSetMapper;

/**
 * 拼车模板消息标识数据接口
 *
 * @author bjsonghongxu
 * @create 2018-02-28 11:11
 **/
public interface UserFormidMapper extends IBasicSetMapper<UserFormid> {
    /**
     * 移除过期的formid
     * @return
     */
    int removeExpireFormId();
}
