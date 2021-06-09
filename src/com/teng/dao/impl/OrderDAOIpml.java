package com.teng.dao.impl;

import com.teng.bean.Order;
import com.teng.dao.OrderDAO;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-04-25 22:49
 */
public class OrderDAOIpml extends BaseDAO implements OrderDAO {
    @Override
    public List<Order> queryOrdersByUserId(Integer id) {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order where `user_id` = ? ";
        return queryForList(Order.class,sql,id);
    }

    @Override
    public int changeOrderStatus(String orderId, String status) {
        String sql = "update t_order set `status`=? where `order_id` = ?";
        return upDate(sql,status,orderId);
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return upDate(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
