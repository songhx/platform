package com.platform.service.sc.impl;

import com.platform.dao.sc.ScInfoDao;
import com.platform.entity.sc.ScInfoEntity;
import com.platform.service.sc.ScInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 圈子信息表Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-21 23:29:44
 */
@Service("scInfoService")
public class ScInfoServiceImpl implements ScInfoService {
    @Autowired
    private ScInfoDao scInfoDao;

    @Override
    public ScInfoEntity queryObject(Integer id) {
        return scInfoDao.queryObject(id);
    }

    @Override
    public List<ScInfoEntity> queryList(Map<String, Object> map) {
        return scInfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return scInfoDao.queryTotal(map);
    }

    @Override
    public int save(ScInfoEntity scInfo) {
        return scInfoDao.save(scInfo);
    }

    @Override
    public int update(ScInfoEntity scInfo) {
        return scInfoDao.update(scInfo);
    }

    @Override
    public int delete(Integer id) {
        return scInfoDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return scInfoDao.deleteBatch(ids);
    }
}
