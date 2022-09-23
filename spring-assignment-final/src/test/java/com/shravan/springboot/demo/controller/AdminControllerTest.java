package com.shravan.springboot.demo.controller;

import com.shravan.springboot.demo.dao.BookRepository;
import com.shravan.springboot.demo.dto.BookDto;
import com.shravan.springboot.demo.entity.Book;
import com.shravan.springboot.demo.service.BookService;
import org.junit.Assert;
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
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdminControllerTest {

    @Autowired
    BookService bookService;

    @MockBean
    BookRepository bookRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Mock
    BindingResult bindingResult;

    @Autowired
    AdminController adminController;


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
    void showFormForAdd() throws Exception{

        MockMvc mockMvc =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/admin/showFormForAdd")).andExpect(status().is(200));

    }

    @Test
    void showFormForUpdate() throws Exception {

        List<Book> books = getBooks();

        Mockito.when(bookRepository.findById(2)).thenReturn(Optional.of(books.get(2)));

        MockMvc mockMvc =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(get("/admin/showFormForUpdate").queryParam("bookId", "2")).andExpect(status().is(200));

    }

    @Test
    void saveBook() throws Exception {
        BookDto bookDto=new BookDto(6,"bookfour", "genrefour", 400);
        MockMvc mockMvc =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockMvc.perform(post("/admin/save").flashAttr("book", bookDto))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/admin/list"));
    }

    @Test
    void delete() {
        String expected="redirect:/admin/list";
        String actual=adminController.delete(1);
        Assert.assertEquals(expected,actual);
    }
}