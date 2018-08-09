package com.platform.controller.stat;

import com.platform.entity.ImsEweiShopOrderEntity;
import com.platform.entity.distributor.DistributorRateEntity;
import com.platform.entity.member.ImsEweiShopMemberEntity;
import com.platform.entity.stat.OrderGoodsVo;
import com.platform.entity.stat.OrderStatVo;
import com.platform.service.ImsEweiShopOrderService;
import com.platform.service.distributor.DistributorRateService;
import com.platform.service.member.ImsEweiShopMemberService;
import com.platform.service.stat.IOrderStatService;
import com.platform.utils.CommissionCalUtil;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    private ImsEweiShopMemberService imsEweiShopMemberService;

    @Autowired
    private DistributorRateService distributorRateService;

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
        setOrderAgentInfo(list);
        setOrderGoodsInfo(list);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    ///设置代理商信息
    private void setOrderAgentInfo( List<OrderStatVo> list){
        if (null == list) return;

        List<ImsEweiShopMemberEntity> agetList = null;



        Map<String, Object> params = new HashMap<>();
        //params.put("agentid",0);
        params.put("isagent",1);
        agetList = imsEweiShopMemberService.queryList(params); //所有代理商


        for (OrderStatVo orderStatVo : list) {
            ///取当前订单的代理商级别信息
            if (null != agetList && agetList.size() > 0){
                for (ImsEweiShopMemberEntity agent : agetList) {
                    //一级代理商
                    ImsEweiShopMemberEntity lv1 = null;
                    //二级代理商
                    ImsEweiShopMemberEntity lv2 = null;
                    //三级代理商
                    ImsEweiShopMemberEntity lv3 = null;

                    if (agent.getId().intValue() == orderStatVo.getAgentid().intValue()){
                        lv1 = agent;
                        if (null != lv1.getAgentid()){
                            //lv2 = lv1;
                            for (ImsEweiShopMemberEntity ag1 : agetList) {
                                if (ag1.getId().intValue() == lv1.getAgentid().intValue()){
                                    //lv1 = null;
                                    lv2 = ag1;
                                    if (null != lv1.getAgentid()){
//                                        lv3 = lv2;
//                                        lv2 = null;
//                                        lv2 = lv1;
                                        for (ImsEweiShopMemberEntity ag2 : agetList) {
                                            if (ag2.getId().intValue() == lv2.getAgentid().intValue()){
                                                lv3 = ag2;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    ///分配代理商信息
                    if (null != lv1){
                        orderStatVo.setAgentLv1(lv1.getAgentlevel());
                        orderStatVo.setAgentLv1Name(lv1.getNickname());
                    }
                    if (null != lv2){
                        orderStatVo.setAgentLv2(lv2.getAgentlevel());
                        orderStatVo.setAgentLv2Name(lv2.getNickname());
                    }
                    if (null != lv3){
                        orderStatVo.setAgentLv3(lv3.getAgentlevel());
                        orderStatVo.setAgentLv3Name(lv3.getNickname());
                    }
                }
            }



        }
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
                    //orderGoodsVo.setTitle(orderGoodsVo.getTitle() + " " + orderGoodsVo.getTotal() + "件");
                    marketprice = marketprice.add(orderGoodsVo.getMarketprice().multiply(BigDecimal.valueOf(orderGoodsVo.getTotal())));
                    costprice  = costprice.add(orderGoodsVo.getCostprice().multiply(BigDecimal.valueOf(orderGoodsVo.getTotal())));
                }

                DistributorRateEntity distributorRate = distributorRateService.queryObject(1);
                if (null != distributorRate){
                    Map<String,BigDecimal> distributorRateMap = new HashMap<String,BigDecimal>();
                    distributorRateMap.put("0_1",distributorRate.getDistributorLv1S1());
                    distributorRateMap.put("0_2",distributorRate.getDistributorLv1S2());
                    distributorRateMap.put("0_3",distributorRate.getDistributorLv1S3());
                    distributorRateMap.put("1_1",distributorRate.getDistributorLv2S1());
                    distributorRateMap.put("1_2",distributorRate.getDistributorLv2S2());
                    distributorRateMap.put("1_3",distributorRate.getDistributorLv2S3());
                    distributorRateMap.put("2_1",distributorRate.getDistributorLv3S1());
                    distributorRateMap.put("2_2",distributorRate.getDistributorLv3S2());
                    distributorRateMap.put("2_3",distributorRate.getDistributorLv3S3());
                    pureProfit = CommissionCalUtil.calPureProfit(distributorRate.getStandarRate(),marketprice,costprice);
                    orderStatVo.setPureProfit(pureProfit);
                    calCommission(orderStatVo,pureProfit,distributorRate,distributorRateMap);
                }

                orderStatVo.setOrderGoodsVoList(orderGoodsVos);
            }

        }
    }

    ///设置佣金
    private void calCommission(OrderStatVo orderStatVo, BigDecimal pureProfit,  DistributorRateEntity distributorRate,Map<String,BigDecimal> distributorRateMap){




        BigDecimal platform = BigDecimal.ZERO;
        orderStatVo.setSalerCommission(CommissionCalUtil.calCommissionProfit(pureProfit,distributorRate.getSlaerRate()));
        orderStatVo.setProvinceCommission(CommissionCalUtil.calCommissionProfit(pureProfit,distributorRate.getProvinceRate()));
        orderStatVo.setCityCommission(CommissionCalUtil.calCommissionProfit(pureProfit,distributorRate.getCityRate()));

        if(orderStatVo.getProvinceCommission() != null){platform =  pureProfit.subtract(orderStatVo.getProvinceCommission() );}
        if(orderStatVo.getCityCommission() != null){platform =  pureProfit.subtract(orderStatVo.getCityCommission() );}
        if(orderStatVo.getSalerCommission() != null){platform =  pureProfit.subtract(orderStatVo.getSalerCommission() );}

        if (StringUtils.isNotBlank(orderStatVo.getAgentLv1Name())){
            orderStatVo.setCommission1(CommissionCalUtil.calCommissionProfit(pureProfit,distributorRateMap.get(String.valueOf(orderStatVo.getAgentLv1()).concat("_1"))));
            platform =  pureProfit.subtract(orderStatVo.getCommission1() );
        }
        if (StringUtils.isNotBlank(orderStatVo.getAgentLv2Name())){
            orderStatVo.setCommission2(CommissionCalUtil.calCommissionProfit(pureProfit,distributorRateMap.get(String.valueOf(orderStatVo.getAgentLv1()).concat("_2"))));
            platform =  pureProfit.subtract(orderStatVo.getCommission2() );
        }
        if (StringUtils.isNotBlank(orderStatVo.getAgentLv3Name())){
            orderStatVo.setCommission3(CommissionCalUtil.calCommissionProfit(pureProfit,distributorRateMap.get(String.valueOf(orderStatVo.getAgentLv1()).concat("_3"))));
            platform =  pureProfit.subtract(orderStatVo.getCommission3() );
        }




        orderStatVo.setPlatformCommission(CommissionCalUtil.calCommissionProfit(pureProfit,CommissionCalUtil.PLATFORM_RATE));
    }




}
