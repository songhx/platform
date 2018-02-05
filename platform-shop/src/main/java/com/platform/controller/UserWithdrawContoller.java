package com.platform.controller;

import com.platform.entity.UserLevelEntity;
import com.platform.entity.UserWithdraw;
import com.platform.service.UserService;
import com.platform.service.UserWithdrawService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 提现业务
 *
 * @author bjsonghongxu
 * @create 2018-02-05 16:01
 **/
@RestController
@RequestMapping("withdraw")
public class UserWithdrawContoller {

    @Autowired
    private UserWithdrawService userWithdrawService;

    @Autowired
    private UserService userService;


    /**
     * 查看列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UserWithdraw> userWithdrawList = userWithdrawService.queryList(query);
        int total = userWithdrawService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userWithdrawList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        UserWithdraw userWithdraw = userWithdrawService.queryObject(id);

        return R.ok().put("userWithdraw", userWithdraw);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserWithdraw userWithdraw) {
        userWithdrawService.save(userWithdraw);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("userlevel:update")
    public R update(@RequestBody UserWithdraw userWithdraw) {

        userWithdraw.setUpdateTime(new Date());
        int rs = userWithdrawService.update(userWithdraw);

        if (rs > 0){ //处理账户余额和冻结资金
            UserWithdraw uw = userWithdrawService.queryObject(userWithdraw.getId());
            dealUserWallet(uw);
        }

        return R.ok();
    }
    //处理账户余额和冻结资金
    private void dealUserWallet(UserWithdraw userWithdraw){
        Map<String,Object> map = new HashedMap();
        if (userWithdraw.getStatus().intValue() == 1 ){
            map.put("reduceBalance","reduceBalance");
        }else if (userWithdraw.getStatus().intValue() == 2 ) {
            map.put("addBalance","addBalance");
        }else{
            return;
        }
        map.put("money",userWithdraw.getWithdrawMoney());
        map.put("reduceFreeze","reduceFreeze"); //删除冻结部分
        map.put("userId",userWithdraw.getUserId());
        userService.updateUserWallet(map);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("userlevel:delete")
    public R delete(@RequestBody Integer[] ids) {
        userWithdrawService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @RequestMapping("queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<UserWithdraw> userWithdrawList = userWithdrawService.queryList(params);
        return R.ok().put("list", userWithdrawList);
    }
}
