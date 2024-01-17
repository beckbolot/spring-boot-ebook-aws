package com.beck.springbootebookaws.api.payload.book;

import com.beck.springbootebookaws.db.enums.BookLanguage;
import com.beck.springbootebookaws.db.enums.Genre;
import com.beck.springbootebookaws.db.enums.RequestStatus;
import com.beck.springbootebookaws.db.enums.TypeOfBook;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookRequest {

    private String image;
    private String title;
    private String author;
    private String publishingHouse;
    private String aboutTheBook;
    private String bookFragment;
    private BookLanguage bookLanguage;
    private int yearOfIssue;
    private int pageVolume;
    private double price;
    private int amountOfBooks;
    private int discount;
    private Boolean bestseller;
    private String audioBookFragment;
    private String eBookFragment;
    private String paperBookFragment;
    private RequestStatus status;
    private String comments;
    private Genre genreEnum;
    private TypeOfBook typeOfBook;
    private Double basketPrice;
}
