package com.platform.api;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platform.constants.CommonConstant;
import com.platform.dto.ArticleVo;
import com.platform.entity.Article;
import com.platform.service.IArticleService;
import com.platform.util.ApiBaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章控制器
 *
 * @author bjsonghongxu
 * @create 2019-03-18 19:25
 **/
@RestController
@RequestMapping("/api/article")
public class ApiArticleController extends ApiBaseAction {

    @Autowired
    private IArticleService iArticleService;

    /**
     * 分页查询文章列表
     * @param articleVo
     * @return
     */
    @RequestMapping("list")
    public Object list(@RequestBody ArticleVo articleVo) {
        PageHelper.startPage(articleVo.getStart(), articleVo.getLimit(), true, false); //设置分页
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dataStatus", CommonConstant.USEABLE_STATUS);
        example.setOrderByClause(" createTime DESC"); ///创建时间倒叙输出
        List<Article> list = iArticleService.selectByExample(example);

        PageInfo<Article> pageInfo = new PageInfo<>(list);

        Map<String, Object> returnMap = new HashMap<>();
        //设置返回参数
        returnMap.put("totalPage",pageInfo.getPages());
        returnMap.put("list", list);
        return toResponsSuccess(returnMap);
    }

    /**
     * 根据id查询文章详情
     * @param articleVo
     * @return
     */
    @RequestMapping("detail")
    public Object detail(@RequestBody ArticleVo articleVo) {
        articleVo.setDataStatus(CommonConstant.USEABLE_STATUS); //可用
        return toResponsSuccess(iArticleService.selectOne(articleVo));
    }
}
