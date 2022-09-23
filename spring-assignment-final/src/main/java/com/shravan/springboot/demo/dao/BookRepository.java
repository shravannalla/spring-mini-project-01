package com.shravan.springboot.demo.dao;

import com.shravan.springboot.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    //that's it .. no need for any code

}
