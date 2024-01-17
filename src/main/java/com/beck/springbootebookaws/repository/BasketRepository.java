package com.beck.springbootebookaws.repository;

import com.beck.springbootebookaws.db.entity.Basket;
import com.beck.springbootebookaws.db.enums.PurchaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket,Long> {

    List<Basket> findAllByPurchaseStatus(PurchaseStatus purchaseStatus);
}
