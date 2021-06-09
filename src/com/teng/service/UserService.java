package com.teng.service;

import com.teng.bean.User;

/**
 * @author
 * @create 2021-04-17 17:32
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，false为用户名可用
     */
    public boolean existsUsername(String username);
}
