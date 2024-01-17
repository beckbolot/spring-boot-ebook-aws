package com.beck.springbootebookaws.repository;

import com.beck.springbootebookaws.db.entity.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoCodeRepository extends JpaRepository<PromoCode,Long> {

    PromoCode findPromoCodeByPromoName(String promoName);
}
