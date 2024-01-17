package com.beck.springbootebookaws.api.payload.promoCode;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PromoCodeRequest {

    private String promoName;
    private int amountOfPromo;
    private LocalDate startingDay;
    private LocalDate finishingDay;
}
