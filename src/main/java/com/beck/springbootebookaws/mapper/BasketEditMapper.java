package com.beck.springbootebookaws.mapper;

import com.beck.springbootebookaws.api.payload.basket.BasketRequest;
import com.beck.springbootebookaws.db.entity.Basket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasketEditMapper {

    public Basket updateBasket(Basket basket, BasketRequest basketRequest){
        basket.setQuantity(basketRequest.getQuantity());
        basket.setPurchaseStatus(basketRequest.getPurchaseStatus());
        return basket;
    }
}
