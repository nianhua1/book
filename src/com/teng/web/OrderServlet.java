package com.teng.web;

import com.teng.bean.Cart;
import com.teng.bean.Order;
import com.teng.bean.OrderItem;
import com.teng.bean.User;
import com.teng.service.OrderService;
import com.teng.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author
 * @create 2021-04-25 23:36
 */
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取userId
        User user = (User) req.getSession().getAttribute("user");
        if(user == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        //调用OrderService方法生成订单
        String orderId = orderService.createOrder(cart, user.getId());
        //订单号保存到request域中
        req.setAttribute("orderId",orderId);
        // 请求转发到/pages/cart/checkout.jsp
        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
    }

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> order = orderService.showAllOrders();

        //订单信息保存到request域中
        req.setAttribute("order",order);
        //req.getSession().setAttribute("order",order);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String orderId = req.getParameter("orderId");
        String status = req.getParameter("status");
        orderService.sendOrder(orderId,status);
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showAllOrders");

    }

    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        req.setAttribute("orderItems",orderItems);
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req,resp);
    }

    protected void showMyOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if(user == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        List<Order> myOrders = orderService.showMyOrders(user.getId());
        req.setAttribute("myOrders",myOrders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        String status = req.getParameter("status");

        orderService.receiverOrder(orderId,status);
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showMyOrder");
    }
}
