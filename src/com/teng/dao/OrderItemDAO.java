package com.teng.dao;

import com.teng.bean.OrderItem;

import java.util.List;

/**
 * @author
 * @create 2021-04-25 22:49
 */
public interface OrderItemDAO {
    public int saveOrderItem(OrderItem orderItem);

    public List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
