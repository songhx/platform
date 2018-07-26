package com.platform.service.member.impl;

import com.platform.dao.member.ImsEweiShopMemberDao;
import com.platform.entity.member.ImsEweiShopMemberEntity;
import com.platform.service.member.ImsEweiShopMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-26 21:55:37
 */
@Service("imsEweiShopMemberService")
public class ImsEweiShopMemberServiceImpl implements ImsEweiShopMemberService {
    @Autowired
    private ImsEweiShopMemberDao imsEweiShopMemberDao;

    @Override
    public ImsEweiShopMemberEntity queryObject(Integer id) {
        return imsEweiShopMemberDao.queryObject(id);
    }

    @Override
    public List<ImsEweiShopMemberEntity> queryList(Map<String, Object> map) {
        return imsEweiShopMemberDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return imsEweiShopMemberDao.queryTotal(map);
    }

    @Override
    public int save(ImsEweiShopMemberEntity imsEweiShopMember) {
        return imsEweiShopMemberDao.save(imsEweiShopMember);
    }

    @Override
    public int update(ImsEweiShopMemberEntity imsEweiShopMember) {
        return imsEweiShopMemberDao.update(imsEweiShopMember);
    }

    @Override
    public int delete(Integer id) {
        return imsEweiShopMemberDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return imsEweiShopMemberDao.deleteBatch(ids);
    }
}
