package com.beck.springbootebookaws.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "history")
public class HistoryOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate createdAt;

    @ManyToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wishlist_id")
    private WishList wishList;

    public HistoryOperation(User user, Basket basket) {
        this.user = user;
        this.basket = basket;
        this.createdAt = LocalDate.now();
    }


    public HistoryOperation(User user, WishList wishList) {
        this.user = user;
        this.wishList = wishList;
        this.createdAt = LocalDate.now();
    }
}
