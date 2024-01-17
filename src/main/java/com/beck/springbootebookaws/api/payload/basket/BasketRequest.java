package com.beck.springbootebookaws.api.payload.basket;

import com.beck.springbootebookaws.db.enums.PurchaseStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BasketRequest {

    private Long basketId;
    private Long bookId;
    private Integer quantity;
    private PurchaseStatus purchaseStatus;


}
