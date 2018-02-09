package com.platform.service.impl;

import com.platform.dao.*;
import com.platform.entity.CouponCodesEntity;
import com.platform.entity.OrderEntity;
import com.platform.entity.ShippingEntity;
import com.platform.entity.UserCouponEntity;
import com.platform.service.OrderService;
import com.platform.utils.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ShippingDao shippingDao;
    @Autowired
    private UserCouponDao userCouponDao;

    @Autowired
    private CouponCodesDao couponCodesDao;

    @Override
    public OrderEntity queryObject(Integer id) {
        return orderDao.queryObject(id);
    }

    @Override
    public List<OrderEntity> queryList(Map<String, Object> map) {
        return orderDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderDao.queryTotal(map);
    }

    @Override
    public int save(OrderEntity order) {
        return orderDao.save(order);
    }

    @Override
    public int update(OrderEntity order) {
        return orderDao.update(order);
    }

    @Override
    public int delete(Integer id) {
        return orderDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return orderDao.deleteBatch(ids);
    }

    @Override
    public int confirm(Integer id) {
        OrderEntity orderEntity = queryObject(id);
        Integer shippingStatus = orderEntity.getShippingStatus();//发货状态
        Integer payStatus = orderEntity.getPayStatus();//付款状态
        if (2 != payStatus) {
            throw new RRException("此订单未付款，不能确认收货！");
        }
        if (4 == shippingStatus) {
            throw new RRException("此订单处于退货状态，不能确认收货！");
        }
        if (0 == shippingStatus) {
            throw new RRException("此订单未发货，不能确认收货！");
        }
        orderEntity.setOrderStatus(301);//订单已发货
        orderEntity.setShippingStatus(2);
        return orderDao.update(orderEntity);
    }

    @Override
    public int sendGoods(OrderEntity order) {
        Integer payStatus = order.getPayStatus();//付款状态
        if (2 != payStatus) {
            throw new RRException("此订单未付款！");
        }

        ShippingEntity shippingEntity = shippingDao.queryObject(order.getShippingId());
        if (null != shippingEntity) {
            order.setShippingName(shippingEntity.getName());
        }
        order.setOrderStatus(300);//订单已发货
        order.setShippingStatus(1);//已发货
        return orderDao.update(order);
    }

    @Override
    public int refund(Integer id) {
        OrderEntity orderEntity = queryObject(id);
        Integer shippingStatus = orderEntity.getShippingStatus();//发货状态
        Integer payStatus = orderEntity.getPayStatus();//付款状态
        if (2 != payStatus) {
            throw new RRException("此订单未付款，不能确退款！");
        }

        if (1 == shippingStatus) {
            throw new RRException("此订单未付款，不能确退款！");
        }

        if (0 == shippingStatus) {
            orderEntity.setOrderStatus(401);//没有发货，退款
        }

        if (2 == shippingStatus) { //已收货
            orderEntity.setOrderStatus(402);//已收货，退款退货
            orderEntity.setShippingStatus(4);
        }

        //退优惠券
        if(orderEntity.getUserCouponId() != null){
            UserCouponEntity userCouponEntity = userCouponDao.queryObject(orderEntity.getUserCouponId());
            if (userCouponEntity != null){
                userCouponEntity.setId(orderEntity.getUserCouponId());
                userCouponEntity.setIsUsed(0); // 标注未使用
                userCouponEntity.setClearUseTime(1);//清除使用时间
                userCouponEntity.setClearOrderId(1); //清除订单号
                userCouponDao.update(userCouponEntity);

                CouponCodesEntity cce = new CouponCodesEntity();
                cce.setId(userCouponEntity.getCouponCodeId());
                cce.setStatus(0);//可用
                couponCodesDao.update(cce);
            }

        }


        //退货，退钱线下操作


        return orderDao.update(orderEntity);
    }
}
