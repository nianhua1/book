package com.teng.test;

import com.teng.bean.Book;
import com.teng.service.BookService;
import com.teng.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author
 * @create 2021-04-21 21:18
 */
public class BookServiceTest {
    private BookService service = new BookServiceImpl();

    @Test
    public void addBook() {
        service.addBook(new Book(null,"七里香","周杰伦",new BigDecimal(999),10000000,0,null));
    }

    @Test
    public void deleteBookById() {
        service.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        service.updateBook(new Book(21,"晴天","周杰伦",new BigDecimal(999),999999999,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(service.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        for (Book book : service.queryBooks()) {
            System.out.println(book);
        }
    }

    @Test
    public void page(){
        System.out.println(service.page(1,4));
    }

    @Test
    public void pageByPrice(){
        System.out.println(service.pageByPrice(0,4,10,50));
    }
}