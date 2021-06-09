package com.teng.service;

import com.teng.bean.Book;
import com.teng.bean.Page;

import java.util.List;

/**
 * @author
 * @create 2021-04-21 21:10
 */
public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
