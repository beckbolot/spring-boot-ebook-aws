package com.beck.springbootebookaws.api.payload.wishlist;

import com.beck.springbootebookaws.db.entity.Book;
import com.beck.springbootebookaws.db.entity.PromoCode;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WishListResponse {

    private Long id;
    private LocalDate createdAt;
    private Book book;
    private Long clientId;
}
