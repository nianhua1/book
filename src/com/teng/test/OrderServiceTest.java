package com.teng.test;

import com.teng.bean.Cart;
import com.teng.bean.CartItem;
import com.teng.bean.Order;
import com.teng.bean.OrderItem;
import com.teng.service.OrderService;
import com.teng.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author
 * @create 2021-04-25 23:27
 */
public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();
    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        System.out.println( "订单号是：" + orderService.createOrder(cart, 1) );
    }

    @Test
    public void showAllOrders(){
        for (Order order : orderService.showAllOrders()) {
            System.out.println(order);
        }
    }
    @Test
    public void sendOrder(){
        orderService.sendOrder("16193655850351","0");
    }

    @Test
    public void showOrderDetail(){
        List<OrderItem> orderItems = orderService.showOrderDetail("16193655850351");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }

    @Test
    public void showMyOrders(){
        List<Order> orders = orderService.showMyOrders(1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }

}