package com.shravan.springboot.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private int id;

    @NotEmpty(message = "Book title can't be empty")
    private String bookTitle;

    private String bookGenre;

    @Min(value = 0 , message = "must be greater than or equal to 0")
    @Max(value = 2000 , message = "must be less than or equal to 2000")
    private float price;

}
