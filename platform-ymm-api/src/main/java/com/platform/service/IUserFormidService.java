package com.platform.service;

import com.platform.entity.UserFormid;

/**
 * 业务接口
 *
 * @author bjsonghongxu
 * @create 2018-02-28 11:12
 **/
public interface IUserFormidService extends IBasicSetMapper<UserFormid> {
    int removeExpireFormId();
}
