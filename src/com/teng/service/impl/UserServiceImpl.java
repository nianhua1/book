package com.teng.service.impl;

import com.teng.bean.User;
import com.teng.dao.UserDAO;
import com.teng.dao.impl.UserDAOImpl;
import com.teng.service.UserService;

/**
 * @author
 * @create 2021-04-17 17:37
 */
public class UserServiceImpl implements UserService{
    private UserDAO dao = new UserDAOImpl();

    @Override
    public void registUser(User user) {
        dao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return dao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(dao.queryUserByUsername(username) == null){
            return false;
        }
        return true;
    }
}
