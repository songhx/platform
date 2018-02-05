package com.platform.service.impl;

import com.platform.dao.UserWithdrawDao;
import com.platform.entity.UserWithdraw;
import com.platform.service.UserWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 提现
 *
 * @author bjsonghongxu
 * @create 2018-02-05 15:58
 **/
@Service("userWithdrawService")
public class UserWithdrawServiceImpl implements UserWithdrawService {

    @Autowired
    private UserWithdrawDao userWithdrawDao;

    @Override
    public UserWithdraw queryObject(Integer id) {
        return userWithdrawDao.queryObject(id);
    }

    @Override
    public List<UserWithdraw> queryList(Map<String, Object> map) {
        return userWithdrawDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userWithdrawDao.queryTotal(map);
    }

    @Override
    public int save(UserWithdraw user) {
        return userWithdrawDao.save(user);
    }

    @Override
    public int update(UserWithdraw user) {
        return userWithdrawDao.update(user);
    }

    @Override
    public int delete(Integer id) {
        return userWithdrawDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return userWithdrawDao.deleteBatch(ids);
    }
}
