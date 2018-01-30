package com.platform.controller;

import com.platform.entity.CouponCodesEntity;
import com.platform.service.CouponCodesService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by zuimeideshiguang on 18/1/29.
 */
@RestController
@RequestMapping("couponCode")
public class CouponCodesController {
    @Autowired
    private CouponCodesService couponCodesService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<CouponCodesEntity> couponList = couponCodesService.queryList(query);
        int total = couponCodesService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(couponList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        CouponCodesEntity codesEntity = couponCodesService.queryObject(id);

        return R.ok().put("code", codesEntity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CouponCodesEntity code) {
        couponCodesService.save(code);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("coupon:update")
    public R update(@RequestBody CouponCodesEntity code) {
        couponCodesService.update(code);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("coupon:delete")
    public R delete(@RequestBody Integer[] ids) {
        couponCodesService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<CouponCodesEntity> list = couponCodesService.queryList(params);

        return R.ok().put("list", list);
    }

}
