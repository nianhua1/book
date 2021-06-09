package com.teng.dao.impl;

import com.teng.bean.OrderItem;
import com.teng.dao.OrderItemDAO;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-04-25 22:53
 */
public class OrderItemDAOImpl extends BaseDAO implements OrderItemDAO {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return upDate(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select `name`,`count`,`price`,`total_price` totalPrice,`order_id` orderId from t_order_item where `order_id` = ?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
