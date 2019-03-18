package com.platform.dao;

import com.platform.entity.Article;
import com.platform.service.IBasicSetMapper;
import org.springframework.stereotype.Repository;

/**
 * 文章数据接口
 *
 * @author bjsonghongxu
 * @create 2019-03-18 19:20
 **/
@Repository
public interface ArticleMapper extends IBasicSetMapper<Article> {
}
