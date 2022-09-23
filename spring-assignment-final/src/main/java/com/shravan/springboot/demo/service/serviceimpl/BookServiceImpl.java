package com.shravan.springboot.demo.service.serviceimpl;

import com.shravan.springboot.demo.exception.BookNotFoundException;
import com.shravan.springboot.demo.dao.BookRepository;
import com.shravan.springboot.demo.entity.Book;
import com.shravan.springboot.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    //need to inject book repository
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void save(Book theBook) {
        bookRepository.save(theBook);
    }

    @Override
    public Book findById(int theId) {
        Optional<Book> result=bookRepository.findById(theId);

        Book theBook=null;
        if(result.isPresent()){
            theBook=result.get();
        }
        else {
            //we didn't find the book
            throw new BookNotFoundException("Did not find the book id - "+theId);
        }
        return theBook;
    }

    @Override
    public void deleteById(int theId) {
        bookRepository.deleteById(theId);
    }
}
