package com.teng.test;

import com.teng.bean.User;
import com.teng.service.UserService;
import com.teng.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * @author
 * @create 2021-04-17 17:45
 */
class UserServiceTest {

    private UserService service = new UserServiceImpl();
    @Test
    void registUser() {
        User user = new User(1, "zxt", "1212", "zxt@qq.com");
        service.registUser(user);
        System.out.println("注册成功");
    }

    @Test
    void login() {
        User user = new User(1, "zxt", "1209", "zxt@qq.com");
        User login = service.login(user);
        if (login == null){
            System.out.println("用户名或密码错误");
        }else {
            System.out.println("登录成功");
        }
    }

    @Test
    void existsUsername() {
        User user = new User(1, "zy", "1212", "zxt@qq.com");
        boolean name = service.existsUsername("admin");
        if(name){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }
}