package com.platform.controller.distributor;

import java.util.List;
import java.util.Map;

import com.platform.entity.distributor.DistributorRateEntity;
import com.platform.service.distributor.DistributorRateService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 分销商分配率配置表Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-08-09 22:37:03
 */
@Controller
@RequestMapping("distributorrate")
public class DistributorRateController {
    @Autowired
    private DistributorRateService distributorRateService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("distributorrate:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<DistributorRateEntity> distributorRateList = distributorRateService.queryList(query);
        int total = distributorRateService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(distributorRateList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("distributorrate:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        DistributorRateEntity distributorRate = distributorRateService.queryObject(id);

        return R.ok().put("distributorRate", distributorRate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("distributorrate:save")
    @ResponseBody
    public R save(@RequestBody DistributorRateEntity distributorRate) {
        distributorRateService.save(distributorRate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("distributorrate:update")
    @ResponseBody
    public R update(@RequestBody DistributorRateEntity distributorRate) {
        distributorRateService.update(distributorRate);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("distributorrate:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        distributorRateService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<DistributorRateEntity> list = distributorRateService.queryList(params);

        return R.ok().put("list", list);
    }
}
