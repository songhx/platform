package com.platform.utils;

import java.math.BigDecimal;

/**
 * 分销计算器
 *
 * @author bjsonghongxu
 * @create 2018-07-19 17:15
 **/
public class CommissionCalUtil {

    private CommissionCalUtil(){}

    public static final BigDecimal STANDARD_RATE = BigDecimal.valueOf(0.06); //标准率

    public static final BigDecimal SALER_RATE = BigDecimal.valueOf(0.62); //销售佣金率

    public static final BigDecimal COMMISSION1_RATE = BigDecimal.valueOf(0.10); //一级分销商佣金率

    public static final BigDecimal COMMISSION2_RATE = BigDecimal.valueOf(0.05); //二级分销商佣金率

    public static final BigDecimal COMMISSION3_RATE = BigDecimal.valueOf(0.05); //三级分销商佣金率

    public static final BigDecimal PROVINCE_RATE = BigDecimal.valueOf(0.02); //省级佣金率

    public static final BigDecimal CITY_RATE = BigDecimal.valueOf(0.02); //市级佣金率

    public static final BigDecimal PLATFORM_RATE = BigDecimal.valueOf(0.14); //平台佣金率

    /**
     * 计算纯利润
     * @param marketprice 零售价
     * @param costprice 成本价
     * @return
     */
    public static BigDecimal calPureProfit(BigDecimal marketprice , BigDecimal costprice ){
        BigDecimal profit = new BigDecimal(0.00);
       //纯利润=零售价-成本价-零售价*0.06
        if (!marketprice.equals(BigDecimal.ZERO)){
            profit = marketprice.subtract(costprice).subtract(marketprice.multiply(STANDARD_RATE));
        }
        if (profit.compareTo(BigDecimal.ZERO) < 0){
            return BigDecimal.ZERO;
        }
        return profit.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算分成利润
     * @param pureProfit
     * @param rate
     * @return
     */
    public static BigDecimal calCommissionProfit(BigDecimal pureProfit , BigDecimal rate ){
        BigDecimal profit = new BigDecimal(0.00);
        //纯利润=零售价-成本价-零售价*0.06
        if (!pureProfit.equals(BigDecimal.ZERO)){
            profit = pureProfit.multiply(rate);
        }
        return profit.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {
        System.out.println(calPureProfit(BigDecimal.valueOf(10.00),BigDecimal.valueOf(10.00)));
    }

}
