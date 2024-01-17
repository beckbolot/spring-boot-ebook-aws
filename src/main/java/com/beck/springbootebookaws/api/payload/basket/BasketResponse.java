package com.beck.springbootebookaws.api.payload.basket;

import com.beck.springbootebookaws.db.entity.Book;
import com.beck.springbootebookaws.db.enums.PurchaseStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BasketResponse {

    private Long basketId;
    private LocalDate createdAt;
    private Book book;
    private Long clientId;
    private Integer quantity;
    private PurchaseStatus purchaseStatus;
    private Double basketPrice;
}
