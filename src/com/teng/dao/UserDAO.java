package com.teng.dao;

import com.teng.bean.User;

/**
 * @author
 * @create 2021-04-17 17:03
 */
public interface UserDAO {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 若返回null，则说明没有这个用户
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return若返回null，则说明没有这个用户
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);
}
