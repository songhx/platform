package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ImsEweiShopOrderDao;
import com.platform.entity.ImsEweiShopOrderEntity;
import com.platform.service.ImsEweiShopOrderService;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-18 18:07:31
 */
@Service("imsEweiShopOrderService")
public class ImsEweiShopOrderServiceImpl implements ImsEweiShopOrderService {
    @Autowired
    private ImsEweiShopOrderDao imsEweiShopOrderDao;

    @Override
    public ImsEweiShopOrderEntity queryObject(Integer id) {
        return imsEweiShopOrderDao.queryObject(id);
    }

    @Override
    public List<ImsEweiShopOrderEntity> queryList(Map<String, Object> map) {
        return imsEweiShopOrderDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return imsEweiShopOrderDao.queryTotal(map);
    }

    @Override
    public int save(ImsEweiShopOrderEntity imsEweiShopOrder) {
        return imsEweiShopOrderDao.save(imsEweiShopOrder);
    }

    @Override
    public int update(ImsEweiShopOrderEntity imsEweiShopOrder) {
        return imsEweiShopOrderDao.update(imsEweiShopOrder);
    }

    @Override
    public int delete(Integer id) {
        return imsEweiShopOrderDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return imsEweiShopOrderDao.deleteBatch(ids);
    }
}
