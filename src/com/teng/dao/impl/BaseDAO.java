package com.teng.dao.impl;

import com.teng.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author shkstart
 * @create 2021-04-17 16:39
 */
public abstract class BaseDAO {
    private QueryRunner runner = new QueryRunner();

    /**
     * @param sql
     * @param args
     * @return返回-1表示执行失败，其他为正常
     */
    public int upDate(String sql,Object ...args){
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return runner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param clazz
     * @param sql
     * @param args
     * @param <T>返回类型的泛型
     * @return返回一个javaBean
     */
    public <T> T queryForOne(Class<T> clazz,String sql,Object ...args){
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return runner.query(conn,sql,new BeanHandler<T>(clazz),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return 返回多个查询语句
     */
    public <T>List<T> queryForList(Class<T> clazz,String sql,Object ...args){
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return runner.query(conn,sql,new BeanListHandler<T>(clazz),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param sql
     * @param args
     * @return 返回特殊值
     */
    public Object queryForValue(String sql,Object ...args){
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return runner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
