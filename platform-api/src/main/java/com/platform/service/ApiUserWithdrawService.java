package com.platform.service;

import com.platform.dao.ApiUserWithdrawMapper;
import com.platform.entity.UserWithdrawVo;
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
@Service
public class ApiUserWithdrawService {

    @Autowired
    private ApiUserWithdrawMapper apiUserWithdrawMapper;

    public UserWithdrawVo queryObject(Integer id) {
        return apiUserWithdrawMapper.queryObject(id);
    }

    public List<UserWithdrawVo> queryList(Map<String, Object> map) {
        return apiUserWithdrawMapper.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return apiUserWithdrawMapper.queryTotal(map);
    }

    public int save(UserWithdrawVo user) {
        return apiUserWithdrawMapper.save(user);
    }

    public int update(UserWithdrawVo user) {
        return apiUserWithdrawMapper.update(user);
    }

    public int delete(Integer id) {
        return apiUserWithdrawMapper.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return apiUserWithdrawMapper.deleteBatch(ids);
    }
}
