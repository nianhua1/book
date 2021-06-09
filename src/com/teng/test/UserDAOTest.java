package com.teng.test;

import com.teng.bean.User;
import com.teng.dao.impl.UserDAOImpl;
import org.junit.Test;

/**
 * @author
 * @create 2021-04-17 17:21
 */
public class UserDAOTest {
    UserDAOImpl dao = new UserDAOImpl();

    @Test
    public void queryUserByUsername() {
        User user = dao.queryUserByUsername("admin");
        System.out.println("user = " + user);
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        User user = dao.queryUserByUsernameAndPassword("admin","admin");
        if (user == null){
            System.out.println("用户名或密码错误");
        }else {
            System.out.println("登录成功");
        }
        System.out.println("user = " + user);
    }

    @Test
    public void saveUser() {
        User user = new User(1, "周贤腾", "1209",  "zxt@formail.com");
        int count = dao.saveUser(user);
        System.out.println("count = " + count);
    }
}
