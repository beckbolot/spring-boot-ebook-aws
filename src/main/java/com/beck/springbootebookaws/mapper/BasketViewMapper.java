package com.beck.springbootebookaws.mapper;

import com.beck.springbootebookaws.api.payload.basket.BasketResponse;
import com.beck.springbootebookaws.db.entity.Basket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BasketViewMapper {

    public BasketResponse viewBasket(Basket basket){
        BasketResponse basketResponse = new BasketResponse();
        basketResponse.setBasketId(basket.getId());
        basketResponse.setCreatedAt(basket.getCreatedAt());
        basketResponse.setBook(basket.getBook());
        basketResponse.setClientId(basket.getClient().getId());
        basketResponse.setQuantity(basket.getQuantity());
        basketResponse.setPurchaseStatus(basket.getPurchaseStatus());
        basketResponse.setBasketPrice(basket.getBasketPrice());

        return basketResponse;
    }

    public List<BasketResponse> viewAllBaskets(List<Basket> baskets){
        List<BasketResponse> basketResponses = new ArrayList<>();
        for (Basket basket : baskets){
            basketResponses.add(viewBasket(basket));
        }
        return basketResponses;
    }
}
