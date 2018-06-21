package com.platform.dao;

import com.platform.entity.ScComment;
import com.platform.service.IBasicSetMapper;
import org.springframework.stereotype.Repository;

/**
 * 评论数据接口
 *
 * @author bjsonghongxu
 * @create 2018-06-21 14:52
 **/
@Repository
public interface CommentMapper extends IBasicSetMapper<ScComment> {
}
