package com.platform.service.distributor.impl;

import com.platform.dao.distributor.DistributorRateDao;
import com.platform.entity.distributor.DistributorRateEntity;
import com.platform.service.distributor.DistributorRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 分销商分配率配置表Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-08-09 22:37:03
 */
@Service("distributorRateService")
public class DistributorRateServiceImpl implements DistributorRateService {
    @Autowired
    private DistributorRateDao distributorRateDao;

    @Override
    public DistributorRateEntity queryObject(Integer id) {
        return distributorRateDao.queryObject(id);
    }

    @Override
    public List<DistributorRateEntity> queryList(Map<String, Object> map) {
        return distributorRateDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return distributorRateDao.queryTotal(map);
    }

    @Override
    public int save(DistributorRateEntity distributorRate) {
        return distributorRateDao.save(distributorRate);
    }

    @Override
    public int update(DistributorRateEntity distributorRate) {
        return distributorRateDao.update(distributorRate);
    }

    @Override
    public int delete(Integer id) {
        return distributorRateDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return distributorRateDao.deleteBatch(ids);
    }
}
