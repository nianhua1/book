package com.teng.service.impl;

import com.teng.bean.*;
import com.teng.dao.BookDAO;
import com.teng.dao.OrderDAO;
import com.teng.dao.OrderItemDAO;
import com.teng.dao.impl.BookDAOImpl;
import com.teng.dao.impl.OrderDAOIpml;
import com.teng.dao.impl.OrderItemDAOImpl;
import com.teng.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author
 * @create 2021-04-25 23:09
 */
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO = new OrderDAOIpml();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private BookDAO bookDAO = new BookDAOImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDAO.saveOrder(order);

        //int a = 12/0;

        Set<Map.Entry<Integer, CartItem>> entrySet = cart.getItems().entrySet();
        for (Map.Entry<Integer, CartItem> entry : entrySet) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDAO.saveOrderItem(orderItem);

            //更新库存和销量
            Book book = bookDAO.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDAO.updateBook(book);
        }

        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDAO.queryOrders();
    }

    @Override
    public void sendOrder(String orderId, String status) {
        orderDAO.changeOrderStatus(orderId,status);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDAO.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrders(Integer id) {
        return orderDAO.queryOrdersByUserId(id);
    }

    @Override
    public void receiverOrder(String orderId, String status) {
        orderDAO.changeOrderStatus(orderId,status);
    }
}
