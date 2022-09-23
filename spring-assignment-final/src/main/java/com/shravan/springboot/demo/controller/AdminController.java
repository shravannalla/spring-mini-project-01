package com.shravan.springboot.demo.controller;

import com.shravan.springboot.demo.converter.BookConverter;
import com.shravan.springboot.demo.dto.BookDto;
import com.shravan.springboot.demo.entity.Book;
import com.shravan.springboot.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String BOOK_FORM="admin/book-form";
    private static final String REDIRECT_TO_LIST="redirect:/admin/list";

    //add initbinder to remove spaces
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    private BookService bookService;

    @Autowired
    private BookConverter converter;

    @GetMapping("/list")
    public String listBooks(Model theModel){
        //get books from service
        List<Book> theBooks=bookService.findAll();

        //add the books to the model
        theModel.addAttribute("books", theBooks);

        return "list-and-edit";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        //create model attribute to bind form data
        Book theBook=new Book();
        theModel.addAttribute("book", theBook);

        return BOOK_FORM;

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theId,
                                    Model theModel){
        //get book from service
        Book theBook=bookService.findById(theId);

        //set book as a model attribute to pre-populate the form
        theModel.addAttribute("book", theBook);

        //send over to our form
        return BOOK_FORM;
    }

    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute("book") BookDto dto, BindingResult bindingResult){

        System.out.println("Calling or not");

        Book book=converter.dtoToEntity(dto);

        //save the book
        if(bindingResult.hasErrors()){
            return BOOK_FORM;
        }

        bookService.save(book);

        //use redirect to prevent duplicate submissions
        return REDIRECT_TO_LIST;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int theId){
        //delete the book
        bookService.deleteById(theId);

        return REDIRECT_TO_LIST;
        //redirect to /admin/list
    }

}
