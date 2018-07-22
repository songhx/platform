package com.platform.service.sc.impl;

import com.platform.dao.sc.ScCommentDao;
import com.platform.entity.sc.ScCommentEntity;
import com.platform.service.sc.ScCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 评论表Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-21 23:29:44
 */
@Service("scCommentService")
public class ScCommentServiceImpl implements ScCommentService {
    @Autowired
    private ScCommentDao scCommentDao;

    @Override
    public ScCommentEntity queryObject(Integer id) {
        return scCommentDao.queryObject(id);
    }

    @Override
    public List<ScCommentEntity> queryList(Map<String, Object> map) {
        return scCommentDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return scCommentDao.queryTotal(map);
    }

    @Override
    public int save(ScCommentEntity scComment) {
        return scCommentDao.save(scComment);
    }

    @Override
    public int update(ScCommentEntity scComment) {
        return scCommentDao.update(scComment);
    }

    @Override
    public int delete(Integer id) {
        return scCommentDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return scCommentDao.deleteBatch(ids);
    }
}
