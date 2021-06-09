package com.teng.dao;

import com.teng.bean.Order;

import java.util.List;

/**
 * @author
 * @create 2021-04-25 22:48
 */
public interface OrderDAO {
    public int saveOrder(Order order);

    public List<Order> queryOrders();

    public int changeOrderStatus(String orderId, String status);

    public List<Order> queryOrdersByUserId(Integer id);
}
