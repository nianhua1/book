package com.teng.web;

import com.google.gson.Gson;
import com.teng.bean.User;
import com.teng.service.UserService;
import com.teng.service.impl.UserServiceImpl;
import com.teng.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author
 * @create 2021-04-20 20:54
 */
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、销毁session
        req.getSession().invalidate();
        //2、重定向到首页（或登录页面）
        resp.sendRedirect(req.getContextPath());
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取参数
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
            //保存用户登录信息到session域中
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("pages/user/login_success.jsp").forward(req,resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        //获取验证码
        String attr = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String code = req.getParameter("code");


        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());

        if(attr!=null && attr.equalsIgnoreCase(code)){
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
        }
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existsUsername = service.existsUsername(username);
        Map<String,Object> existMap = new HashMap<>();
        existMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(existMap);
        resp.getWriter().write(json);


    }
}
