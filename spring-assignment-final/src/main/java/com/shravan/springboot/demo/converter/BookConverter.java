package com.shravan.springboot.demo.converter;

import com.shravan.springboot.demo.dto.BookDto;
import com.shravan.springboot.demo.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookConverter {

    public BookDto entityToDto(Book book){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(book, BookDto.class);
    }

    public List<BookDto> entityToDto(List<Book> book){
        return book.stream().map(this::entityToDto).collect(Collectors.toList());

    }

    public Book dtoToEntity(BookDto dto){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(dto, Book.class);
    }

    public List<Book> dtoToEntity(List<BookDto> dto){
        return dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
