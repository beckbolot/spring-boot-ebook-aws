package com.beck.springbootebookaws.mapper;

import com.beck.springbootebookaws.api.payload.promoCode.PromoCodeResponse;
import com.beck.springbootebookaws.db.entity.PromoCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromoCodeViewMapper {

    public PromoCodeResponse viewPromoMapper(PromoCode promocode) {
        PromoCodeResponse response = new PromoCodeResponse();
        response.setId(promocode.getId());
        response.setPromoName(promocode.getPromoName());
        response.setStartingDay(promocode.getStartingDay());
        response.setFinishingDay(promocode.getFinishingDay());
        response.setAmountOfPromo(promocode.getAmountOfPromo());
        response.setBookList(promocode.getBooks());
        return response;
    }


}
