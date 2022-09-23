package com.shravan.springboot.demo.controller;

import com.shravan.springboot.demo.entity.Book;
import com.shravan.springboot.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    //need to inject book service
    @Autowired
    private BookService bookService;


    @GetMapping("/list")
    public String listBooks(Model theModel){
        //get books from service
        List<Book> theBooks=bookService.findAll();

        //add the books to the model
        theModel.addAttribute("books", theBooks);

        return "list-books";
    }

    @GetMapping("/aboutUs")
    public String aboutUs(){
        return "about-us";
    }

    @GetMapping("/contactUs")
    public String contactUs(){
        return "contact-us";
    }

}
