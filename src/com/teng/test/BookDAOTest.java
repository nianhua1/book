package com.teng.test;

import com.teng.bean.Book;
import com.teng.bean.Page;
import com.teng.dao.BookDAO;
import com.teng.dao.impl.BookDAOImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author
 * @create 2021-04-21 20:58
 */
public class BookDAOTest {
    private BookDAO dao = new BookDAOImpl();

    @Test
    public void addBook() {
        Book book = new Book(null,"周董","周杰伦",new BigDecimal(999),1000,0,null);
        dao.addBook(book);
        System.out.println("增加图书成功");

    }

    @Test
    public void deleteBookById() {
        dao.deleteBookById(4);
        System.out.println("删除成功");
    }

    @Test
    public void updateBook() {
        dao.updateBook(new Book(21,"周董","JAY",new BigDecimal(999999),1000,0,null));
        System.out.println("更新成功");
    }

    @Test
    public void queryBookById() {
        System.out.println(dao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book book : dao.queryBooks()) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(dao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        List<Book> books = dao.queryForPageItems(8, 4);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {

        System.out.println(dao.queryForPageTotalCountByPrice(10,100));
    }

    @Test
    public void queryForPageItemsByPrice() {
        List<Book> books = dao.queryForPageItemsByPrice(0,Page.PAGE_SIZE,10,50);
        for (Book book : books) {
            System.out.println(book);
        }
    }
}