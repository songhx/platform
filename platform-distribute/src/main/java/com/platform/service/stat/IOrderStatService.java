package com.platform.service.stat;

import com.platform.entity.stat.OrderGoodsVo;
import com.platform.entity.stat.OrderStatVo;

import java.util.List;
import java.util.Map;

/**
 * 订单数据统计服务接口
 *
 * @author bjsonghongxu
 * @create 2018-07-19 15:57
 **/
public interface IOrderStatService  {

    /**
     * 分页查询
     * @param map
     * @return
     */
    List<OrderStatVo> queryList(Map<String, Object> map);

    /**
     * 查询总数
     * @param map
     * @return
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 查询订单商品集合
     * @param map
     * @return
     */
    List<OrderGoodsVo> querOrderGoodsList(Map<String, Object> map);
}
