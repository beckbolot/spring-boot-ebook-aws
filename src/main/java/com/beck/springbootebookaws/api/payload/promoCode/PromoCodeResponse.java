package com.beck.springbootebookaws.api.payload.promoCode;

import com.beck.springbootebookaws.db.entity.Book;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PromoCodeResponse {

    private Long id;
    private String promoName;
    private int amountOfPromo;
    private LocalDate startingDay;
    private LocalDate finishingDay;
    private List<Book> bookList;
}
