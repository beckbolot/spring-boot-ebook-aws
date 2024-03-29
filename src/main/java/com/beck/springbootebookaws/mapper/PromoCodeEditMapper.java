package com.beck.springbootebookaws.mapper;

import com.beck.springbootebookaws.api.payload.promoCode.PromoCodeRequest;
import com.beck.springbootebookaws.db.entity.PromoCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromoCodeEditMapper {

    public PromoCode create(PromoCodeRequest promocodeRequest) {
        PromoCode promocode = new PromoCode();
        promocode.setPromoName(promocodeRequest.getPromoName());
        promocode.setAmountOfPromo(promocodeRequest.getAmountOfPromo());
        promocode.setStartingDay(promocodeRequest.getStartingDay());
        promocode.setFinishingDay(promocodeRequest.getFinishingDay());
        return promocode;
    }

    public PromoCode update(PromoCode promocode, PromoCodeRequest promocodeRequest) {
        promocode.setPromoName(promocodeRequest.getPromoName());
        promocode.setAmountOfPromo(promocodeRequest.getAmountOfPromo());
        promocode.setStartingDay(promocodeRequest.getStartingDay());
        promocode.setFinishingDay(promocodeRequest.getFinishingDay());
        return promocode;
    }
}
