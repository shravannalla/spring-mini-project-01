package com.shravan.springboot.demo.service;

import com.shravan.springboot.demo.entity.Book;

import java.util.List;

public interface BookService {

    public List<Book> findAll();

    public void save(Book theBook);

    public Book findById(int theId);

    public void deleteById(int theId);

}
