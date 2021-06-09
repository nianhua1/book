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
import java.util.List;

/**
 * @author
 * @create 2021-04-21 21:39
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        int pageNo = WebUtils.conventInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.conventInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用 BookService.page(pageNo，pageSize)：Page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("manager/bookServlet?action=page");
        //3保存到request域中
        req.setAttribute("page",page);
        //4 请求转发
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.conventInt(req.getParameter("pageNo"), 0);
        pageNo++;
        //获取请求对象封住成book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //保存图书
        bookService.addBook(book);
        //重定向路径
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo=" + pageNo );
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的id
        int id = WebUtils.conventInt(req.getParameter("id"), 0);
        //调用delete
        bookService.deleteBookById(id);
        //重定向到图书页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //修改图书
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数图书编号
        int id = WebUtils.conventInt(req.getParameter("id"), 0);
        //2 调用 bookService.queryBookById 查询图书
        Book book = bookService.queryBookById(id);
        //3 保存到图书到 Request 域中
        req.setAttribute("book",book);
        // 4 请求转发到。pages/manager/book_edit.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过service查询全部图书
        List<Book> books = bookService.queryBooks();
        //保存到request域中
        req.setAttribute("books",books);
        //请求转发到book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
        System.out.println(req.getContextPath());
        System.out.println(req.getRequestURL());
    }
}
