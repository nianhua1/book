package com.teng.test;

import com.teng.bean.OrderItem;
import com.teng.dao.OrderItemDAO;
import com.teng.dao.impl.OrderItemDAOImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author
 * @create 2021-04-25 23:01
 */
public class OrderItemDAOTest {
    private OrderItemDAO OrderItemDAO = new OrderItemDAOImpl();
    @Test
    public void saveOrderItem() {

        OrderItemDAO.saveOrderItem(new OrderItem(null,"java 从入门到精通", 1,new BigDecimal(100),new BigDecimal(100),"1234567891"));
        OrderItemDAO.saveOrderItem(new OrderItem(null,"javaScript 从入门到精通", 2,new BigDecimal(100),new BigDecimal(200),"1234567891"));
        OrderItemDAO.saveOrderItem(new OrderItem(null,"Netty 入门", 1,new BigDecimal(100),new BigDecimal(100),"1234567891"));
    }

    @Test
    public void queryOrderItemsByOrderId(){
        List<OrderItem> orderItems = OrderItemDAO.queryOrderItemsByOrderId("16193655850351");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }
}