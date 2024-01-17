package com.beck.springbootebookaws.db.services;

import com.beck.springbootebookaws.api.payload.promoCode.PromoCodeResponse;
import com.beck.springbootebookaws.db.entity.Book;
import com.beck.springbootebookaws.db.entity.PromoCode;
import com.beck.springbootebookaws.db.enums.RequestStatus;
import com.beck.springbootebookaws.mapper.PromoCodeViewMapper;
import com.beck.springbootebookaws.repository.BookRepository;
import com.beck.springbootebookaws.repository.PromoCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromoService {

    private final PromoCodeRepository promoCodeRepository;
    private final BookRepository bookRepository;
    private final PromoCodeViewMapper promoCodeViewMapper;

    public PromoCodeResponse getPromoCodeByName(String name) {
        String text = name == null ? " " : name;
        PromoCode promocode = promoCodeRepository.findPromoCodeByPromoName(text.toLowerCase(Locale.ROOT));
        List<Book> book = bookRepository.findAllByRequestStatus(RequestStatus.APPROVED);
        List<Book> approvedBooks = new ArrayList<>(book);
        promocode.setBooks(approvedBooks);
        log.info("All approved books with related promo code:");
        return promoCodeViewMapper.viewPromoMapper(promocode);
    }

}
