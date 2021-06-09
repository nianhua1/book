package com.teng.dao.impl;

import com.teng.bean.User;
import com.teng.dao.UserDAO;

/**
 * @author shkstart
 * @create 2021-04-17 17:09
 */
public class UserDAOImpl extends BaseDAO implements UserDAO{
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select id,username,password,email from t_user where username = ?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return upDate(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
