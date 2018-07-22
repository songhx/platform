package com.platform.controller.sc;

import java.util.List;
import java.util.Map;

import com.platform.entity.sc.ScInfoEntity;
import com.platform.service.sc.ScInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 圈子信息表Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-21 23:29:44
 */
@Controller
@RequestMapping("scinfo")
public class ScInfoController {
    @Autowired
    private ScInfoService scInfoService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("scinfo:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ScInfoEntity> scInfoList = scInfoService.queryList(query);
        int total = scInfoService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(scInfoList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("scinfo:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        ScInfoEntity scInfo = scInfoService.queryObject(id);

        return R.ok().put("scInfo", scInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("scinfo:save")
    @ResponseBody
    public R save(@RequestBody ScInfoEntity scInfo) {
        scInfoService.save(scInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("scinfo:update")
    @ResponseBody
    public R update(@RequestBody ScInfoEntity scInfo) {
        scInfoService.update(scInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("scinfo:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        scInfoService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ScInfoEntity> list = scInfoService.queryList(params);

        return R.ok().put("list", list);
    }
}
