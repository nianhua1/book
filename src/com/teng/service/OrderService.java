package com.teng.service;

import com.teng.bean.Cart;
import com.teng.bean.Order;
import com.teng.bean.OrderItem;

import java.util.List;

/**
 * @author
 * @create 2021-04-25 23:08
 */
public interface OrderService {
    public String createOrder(Cart cart, Integer userId);

    public List<Order> showAllOrders();

    public void sendOrder(String orderId, String status);

    public List<OrderItem> showOrderDetail(String orderId);

    public List<Order> showMyOrders(Integer id);

    public void receiverOrder(String orderId, String status);
}
