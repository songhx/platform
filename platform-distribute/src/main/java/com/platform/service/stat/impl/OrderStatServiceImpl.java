package com.platform.service.stat.impl;

import com.platform.dao.stat.OrderStatDao;
import com.platform.entity.stat.OrderGoodsVo;
import com.platform.entity.stat.OrderStatVo;
import com.platform.service.stat.IOrderStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author bjsonghongxu
 * @create 2018-07-19 15:59
 **/
@Service
public class OrderStatServiceImpl implements IOrderStatService {

    @Autowired
    private OrderStatDao orderStatDao;

    @Override
    public List<OrderStatVo> queryList(Map<String, Object> map) {
        return orderStatDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderStatDao.queryTotal(map);
    }

    @Override
    public List<OrderGoodsVo> querOrderGoodsList(Map<String, Object> map) {
        return orderStatDao.querOrderGoodsList(map);
    }
}
