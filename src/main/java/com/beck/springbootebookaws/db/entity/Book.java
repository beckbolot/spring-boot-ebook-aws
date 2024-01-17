package com.beck.springbootebookaws.db.entity;

import com.beck.springbootebookaws.db.enums.BookLanguage;
import com.beck.springbootebookaws.db.enums.Genre;
import com.beck.springbootebookaws.db.enums.RequestStatus;
import com.beck.springbootebookaws.db.enums.TypeOfBook;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_gen")
    @SequenceGenerator(name = "book_gen", sequenceName = "book_seq", allocationSize = 1)
    private Long id;

    private String image;

    private String title;

    private String author;

    private String publishingHouse;

    private String aboutTheBook;

    private String bookFragment;

    @Enumerated(EnumType.STRING)
    private BookLanguage bookLanguage;

    private int yearsOfIssie;

    private int pageOfVolume;

    private Double price;

    private int amountOfBook;

    private int discount;

    private Boolean isBestSeller;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private String comments;

    @Enumerated(EnumType.STRING)
    private TypeOfBook typeOfBook;

    private Genre genreEnum;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "promo_code_id")
    private PromoCode promoCode;










}
