package com.teng.web;

import com.teng.service.UserService;
import com.teng.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 * @create 2021-04-17 20:17
 */
public class RegistServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        System.out.println(username+","+password+","+email+","+code);

        if("abcd".equalsIgnoreCase(code)){
            if(service.existsUsername(username)){
                System.out.println("用户名已存在["+username+"]");
                req.setAttribute("msg","用户名已存在！");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.setAttribute("code",code);
                req.getRequestDispatcher("pages/user/regist.jsp").forward(req,resp);
            }else{
                service.registUser(new User(null, username, password, email));
                System.out.println("注册成功");
                req.getRequestDispatcher("pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            System.out.println("验证码错误["+code+"]");
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.setAttribute("code",code);
            req.getRequestDispatcher("pages/user/regist.jsp").forward(req,resp);
        }*/

    }
}
