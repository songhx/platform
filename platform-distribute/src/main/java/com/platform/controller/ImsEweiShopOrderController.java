package com.platform.controller;

import com.platform.entity.ImsEweiShopOrderEntity;
import com.platform.service.ImsEweiShopOrderService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-18 18:07:31
 */
@Controller
@RequestMapping("imseweishoporder")
public class ImsEweiShopOrderController {
    @Autowired
    private ImsEweiShopOrderService imsEweiShopOrderService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("imseweishoporder:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<ImsEweiShopOrderEntity> imsEweiShopOrderList = imsEweiShopOrderService.queryList(query);
        int total = imsEweiShopOrderService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(imsEweiShopOrderList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("imseweishoporder:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        ImsEweiShopOrderEntity imsEweiShopOrder = imsEweiShopOrderService.queryObject(id);

        return R.ok().put("imsEweiShopOrder", imsEweiShopOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("imseweishoporder:save")
    @ResponseBody
    public R save(@RequestBody ImsEweiShopOrderEntity imsEweiShopOrder) {
        imsEweiShopOrderService.save(imsEweiShopOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("imseweishoporder:update")
    @ResponseBody
    public R update(@RequestBody ImsEweiShopOrderEntity imsEweiShopOrder) {
        imsEweiShopOrderService.update(imsEweiShopOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("imseweishoporder:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        imsEweiShopOrderService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<ImsEweiShopOrderEntity> list = imsEweiShopOrderService.queryList(params);

        return R.ok().put("list", list);
    }
}
