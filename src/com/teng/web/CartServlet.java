package com.teng.web;

import com.google.gson.Gson;
import com.teng.bean.Book;
import com.teng.bean.Cart;
import com.teng.bean.CartItem;
import com.teng.service.BookService;
import com.teng.service.impl.BookServiceImpl;
import com.teng.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @create 2021-04-25 20:15
 */
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookid = WebUtils.conventInt(req.getParameter("bookid"),0);
        int count = WebUtils.conventInt(req.getParameter("count"),1);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.updateCount(bookid,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void clearItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookid = WebUtils.conventInt(req.getParameter("bookid"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.deleteItem(bookid);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        int bookid = WebUtils.conventInt(req.getParameter("bookid"), 0);
        // 调用 bookService.queryBookById(id):Book 得到图书的信息
        Book book = bookService.queryBookById(bookid);

        // 把图书信息，转换成为 CartItem 商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 调用 Cart.addItem(CartItem);添加商品项

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
           cart = new Cart();
           req.getSession().setAttribute("cart",cart);
        }

        cart.addItem(cartItem);

        System.out.println(cart);
        System.out.println(req.getHeader("Referer"));

        req.getSession().setAttribute("lastBookName",cartItem.getName());

        //重定向Referer
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        int bookid = WebUtils.conventInt(req.getParameter("bookid"), 0);
        // 调用 bookService.queryBookById(id):Book 得到图书的信息
        Book book = bookService.queryBookById(bookid);

        // 把图书信息，转换成为 CartItem 商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 调用 Cart.addItem(CartItem);添加商品项

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }

        cart.addItem(cartItem);

        req.getSession().setAttribute("lastBookName",cartItem.getName());
        Map<String,Object> cartMap = new HashMap<>();
        cartMap.put("cartTotalCount",cart.getTotalCount());
        cartMap.put("lastBookName",cartItem.getName());

        Gson gson = new Gson();
        String jsonStr = gson.toJson(cartMap);
        resp.getWriter().write(jsonStr);

    }
}
