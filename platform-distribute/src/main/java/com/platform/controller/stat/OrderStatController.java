package com.platform.controller.stat;

import com.platform.entity.stat.OrderGoodsVo;
import com.platform.entity.stat.OrderStatVo;
import com.platform.service.ImsEweiShopOrderService;
import com.platform.service.stat.IOrderStatService;
import com.platform.utils.CommissionCalUtil;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单统计控制器
 *
 * @author bjsonghongxu
 * @create 2018-07-19 15:06
 **/
@Controller
@RequestMapping("orderStat")
public class OrderStatController {

    public static final Logger logger = LoggerFactory.getLogger(OrderStatController.class);

    @Autowired
    private ImsEweiShopOrderService imsEweiShopOrderService;

    @Autowired
    private IOrderStatService iOrderStatService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("orderStat:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<OrderStatVo> list = iOrderStatService.queryList(query);
        int total = iOrderStatService.queryTotal(query);
        setOrderGoodsInfo(list);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    ///设置商品信息
    private void setOrderGoodsInfo(List<OrderStatVo> list){
        if (null == list) return;
        Map<String,Object> map = null;
        for (OrderStatVo orderStatVo : list) {
            map = new HashMap<>();
            map.put("orderid",orderStatVo.getId());
            List<OrderGoodsVo> orderGoodsVos = iOrderStatService.querOrderGoodsList(map);
            if (null != orderGoodsVos && orderGoodsVos.size() > 0){
                BigDecimal marketprice = BigDecimal.ZERO;
                BigDecimal costprice = BigDecimal.ZERO;
                BigDecimal pureProfit = BigDecimal.ZERO;
                for (OrderGoodsVo orderGoodsVo : orderGoodsVos) {
                    marketprice = marketprice.add(orderGoodsVo.getMarketprice());
                    costprice  = costprice.add(orderGoodsVo.getCostprice());
                }
                pureProfit = CommissionCalUtil.calPureProfit(marketprice,costprice);
                calCommission(orderStatVo,pureProfit);
                orderStatVo.setOrderGoodsVoList(orderGoodsVos);
            }

        }
    }

    ///设置佣金
    private void calCommission(OrderStatVo orderStatVo,BigDecimal pureProfit){
        orderStatVo.setPlatformCommission(CommissionCalUtil.calCommissionProfit(pureProfit,CommissionCalUtil.PLATFORM_RATE));
        orderStatVo.setSalerCommission(CommissionCalUtil.calCommissionProfit(pureProfit,CommissionCalUtil.SALER_RATE));
        orderStatVo.setProvinceCommission(CommissionCalUtil.calCommissionProfit(pureProfit,CommissionCalUtil.PROVINCE_RATE));
        orderStatVo.setCityCommission(CommissionCalUtil.calCommissionProfit(pureProfit,CommissionCalUtil.CITY_RATE));
        orderStatVo.setCommission1(CommissionCalUtil.calCommissionProfit(pureProfit,CommissionCalUtil.COMMISSION1_RATE));
        orderStatVo.setCommission2(CommissionCalUtil.calCommissionProfit(pureProfit,CommissionCalUtil.COMMISSION2_RATE));
        orderStatVo.setCommission3(CommissionCalUtil.calCommissionProfit(pureProfit,CommissionCalUtil.COMMISSION3_RATE));
    }


}
