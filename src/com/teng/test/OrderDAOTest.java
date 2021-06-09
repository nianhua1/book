package com.teng.test;

import com.teng.bean.Order;
import com.teng.dao.OrderDAO;
import com.teng.dao.impl.OrderDAOIpml;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @create 2021-04-25 22:58
 */
public class OrderDAOTest {
    private OrderDAO orderDao = new OrderDAOIpml();
    @Test
    public void saveOrder() {

        orderDao.saveOrder(new Order("1234567891",new Date(),new BigDecimal(100),0, 1));
    }

    @Test
    public void queryOrders(){
        for (Order order : orderDao.queryOrders()) {
            System.out.println(order);
        }
    }
    @Test
    public void changeOrderStatus(){
        orderDao.changeOrderStatus("16193655850351","1");

    }
    @Test
    public void queryOrdersByUserId(){
        List<Order> orders = orderDao.queryOrdersByUserId(1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}