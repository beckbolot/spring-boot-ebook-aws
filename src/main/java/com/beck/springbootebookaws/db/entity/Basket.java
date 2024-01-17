package com.beck.springbootebookaws.db.entity;

import com.beck.springbootebookaws.db.enums.PurchaseStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "baskets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;

    private Double basketPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User client;

    public Basket(Integer quantity, PurchaseStatus purchaseStatus, Book book, User client) {
        this.quantity = quantity;
        this.purchaseStatus = purchaseStatus;
        this.basketPrice = book.getPrice();
        this.book = book;
        this.client = client;
    }
}
