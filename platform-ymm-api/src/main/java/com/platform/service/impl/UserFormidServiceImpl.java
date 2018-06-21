package com.platform.service.impl;

import com.platform.dao.UserFormidMapper;
import com.platform.entity.UserFormid;
import com.platform.service.IUserFormidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bjsonghongxu
 * @create 2018-02-28 11:13
 **/
@Service
public class UserFormidServiceImpl extends BasicSetServiceImpl<UserFormid> implements IUserFormidService {

    @Autowired
    private UserFormidMapper userFormidMapper;
    @Override
    public int removeExpireFormId() {
        return userFormidMapper.removeExpireFormId();
    }
}
