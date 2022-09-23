package com.shravan.springboot.demo.controller;

import com.shravan.springboot.demo.dao.BookRepository;
import com.shravan.springboot.demo.entity.Book;
import com.shravan.springboot.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookControllerTest {

    @Autowired
    BookService bookService;

    @MockBean
    BookRepository bookRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Mock
    BindingResult bindingResult;

    @Autowired
    BookController bookController;

    private List<Book> getBooks(){
        List<Book> books=new ArrayList<>();

        Book book1=new Book(1,"bookone", "genreone",100);
        Book book2=new Book(2,"booktwo", "genretwo",200);
        Book book3=new Book(3,"bookthree", "genrethree",300);

        books.add(book1);
        books.add(book2);
        books.add(book3);

        return books;
    }

    @Test
    void listBooks() throws Exception {
        List<Book> books=getBooks();
        Mockito.when(bookRepository.findAll()).thenReturn(books);

        MockMvc mockMvc=
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/book/list")).andExpect(status().is(200));
    }


    @Test
    void aboutUs() {
        String expected="about-us";
        String actual= bookController.aboutUs();
        assertEquals(expected,actual);
    }

    @Test
    void contactUs() {
        String expected="contact-us";
        String actual= bookController.contactUs();
        assertEquals(expected,actual);
    }
}