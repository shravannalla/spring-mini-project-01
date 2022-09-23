package com.shravan.springboot.demo;

import com.shravan.springboot.demo.dao.BookRepository;
import com.shravan.springboot.demo.entity.Book;
import com.shravan.springboot.demo.service.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class BookServiceTests {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    void getBooksTest(){
        Book book=new Book("newbook","newgenre", 200.00F);
        Mockito.when(bookRepository.findAll()).thenReturn(Stream.of(book).collect(Collectors.toList()));
        Assert.assertEquals(1,bookService.findAll().size());
    }

    @Test
    void getBookTest(){
        Book book1=new Book("newpage","newgen",300);
        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(book1));
        Assert.assertEquals("newpage",bookRepository.findById(1).get().getBookTitle());
    }


    @Test
    void saveBookTest(){
        Book book2=new Book("newbookk","newgenree",400);
        bookService.save(book2);
        Mockito.verify(bookRepository, Mockito.times(1)).save(book2);
    }

    @Test
    void deleteTest(){
        int id=2;
        bookService.deleteById(id);
        Mockito.verify(bookRepository,Mockito.times(1)).deleteById(id);
    }

}
