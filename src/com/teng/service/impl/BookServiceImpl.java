package com.teng.service.impl;

import com.teng.bean.Book;
import com.teng.bean.Page;
import com.teng.dao.BookDAO;
import com.teng.dao.impl.BookDAOImpl;
import com.teng.service.BookService;

import java.util.List;

/**
 * @author
 * @create 2021-04-21 21:15
 */
public class BookServiceImpl implements BookService {
    private BookDAO bookDAO = new BookDAOImpl();

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();
        //设置每页显示数量
        page.setPageSize(pageSize);
        //总记录数
        Integer pageTotalCount = bookDAO.queryForPageTotalCountByPrice(min,max);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize >0){
            pageTotal++;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);

        //求当前页数据的索引
        int begin = (page.getPageNo()-1)*pageSize;
        //求当前页数据
        List<Book> items = bookDAO.queryForPageItemsByPrice(begin,pageSize,min,max);
        //设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();
        //设置每页显示数量
        page.setPageSize(pageSize);
        //总记录数
        Integer pageTotalCount = bookDAO.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize >0){
            pageTotal++;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);

        //求当前页数据的索引
        int begin = (page.getPageNo()-1)*pageSize;
        //求当前页数据
        List<Book> items = bookDAO.queryForPageItems(begin,pageSize);
        //设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDAO.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDAO.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDAO.queryBooks();
    }
}
