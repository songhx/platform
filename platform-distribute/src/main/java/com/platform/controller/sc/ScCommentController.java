package com.platform.controller.sc;

import java.util.List;
import java.util.Map;

import com.platform.entity.sc.ScCommentEntity;
import com.platform.service.sc.ScCommentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 评论表Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-21 23:29:44
 */
@Controller
@RequestMapping("sccomment")
public class ScCommentController {
    @Autowired
    private ScCommentService scCommentService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ScCommentEntity> scCommentList = scCommentService.queryList(query);
        int total = scCommentService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(scCommentList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        ScCommentEntity scComment = scCommentService.queryObject(id);

        return R.ok().put("scComment", scComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sccomment:save")
    @ResponseBody
    public R save(@RequestBody ScCommentEntity scComment) {
        scCommentService.save(scComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sccomment:update")
    @ResponseBody
    public R update(@RequestBody ScCommentEntity scComment) {
        scCommentService.update(scComment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        scCommentService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ScCommentEntity> list = scCommentService.queryList(params);

        return R.ok().put("list", list);
    }
}
