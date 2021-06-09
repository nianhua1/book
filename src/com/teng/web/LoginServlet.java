package com.teng.web;

import com.teng.service.UserService;
import com.teng.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 * @create 2021-04-17 21:26
 */
public class LoginServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*//1、获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2、调用service方法判断用户是否存在
        User user = service.login(new User(null, username, password, null));
        if (user == null){
            //3、登录失败，用户名或密码错误
            //回显数据
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
        }else {
            //4、登录成功
            req.getRequestDispatcher("pages/user/login_success.jsp").forward(req,resp);
        }*/

    }
}
