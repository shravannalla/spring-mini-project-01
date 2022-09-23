package com.shravan.springboot.demo.entity;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "genre")
    private String bookGenre;

    @Column(name = "price")
    private float price;

    public Book(String bookTitle, String bookGenre, float price) {
        this.bookTitle = bookTitle;
        this.bookGenre = bookGenre;
        this.price = price;
    }


}
