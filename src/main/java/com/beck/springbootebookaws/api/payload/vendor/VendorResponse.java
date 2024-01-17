package com.beck.springbootebookaws.api.payload.vendor;

import com.beck.springbootebookaws.db.entity.Book;
import com.beck.springbootebookaws.db.entity.PromoCode;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VendorResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private LocalDateTime createdAt;
    private Boolean isActive;
    private List<Book> bookList;
    private List<PromoCode> promoCodes;
}
