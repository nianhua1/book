package com.teng.web;

import com.teng.bean.Book;
import com.teng.bean.Page;
import com.teng.service.BookService;
import com.teng.service.impl.BookServiceImpl;
import com.teng.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 * @create 2021-04-23 20:55
 */
public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        int pageNo = WebUtils.conventInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.conventInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用 BookService.page(pageNo，pageSize)：Page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3保存到request域中
        req.setAttribute("page",page);
        //4 请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        int pageNo = WebUtils.conventInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.conventInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.conventInt(req.getParameter("min"), 0);
        int max = WebUtils.conventInt(req.getParameter("max"), Integer.MAX_VALUE);
        //2 调用 BookService.pageByPrice(pageNo，pageSize,min,max)：Page 对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);

        StringBuilder builder = new StringBuilder("client/bookServlet?action=pageByPrice");
        if(req.getParameter("min") != null){
            builder.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max") != null){
            builder.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(builder.toString());
        //3保存到request域中
        req.setAttribute("page",page);
        //4 请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

}
