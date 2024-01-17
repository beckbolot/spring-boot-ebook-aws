package com.beck.springbootebookaws.db.services;

import com.beck.springbootebookaws.api.payload.basket.BasketRequest;
import com.beck.springbootebookaws.api.payload.basket.BasketResponse;
import com.beck.springbootebookaws.db.entity.Basket;
import com.beck.springbootebookaws.db.entity.Book;
import com.beck.springbootebookaws.db.entity.HistoryOperation;
import com.beck.springbootebookaws.db.entity.User;
import com.beck.springbootebookaws.db.enums.PurchaseStatus;
import com.beck.springbootebookaws.exceptions.NoSuchElementException;
import com.beck.springbootebookaws.mapper.BasketEditMapper;
import com.beck.springbootebookaws.mapper.BasketViewMapper;
import com.beck.springbootebookaws.repository.BasketRepository;
import com.beck.springbootebookaws.repository.BookRepository;
import com.beck.springbootebookaws.repository.HistoryOperationRepository;
import com.beck.springbootebookaws.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketService {

    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BasketViewMapper viewMapper;
    private final BasketEditMapper editMapper;
    private HistoryOperationRepository historyOperationRepository;

    public BasketResponse addBasket(BasketRequest basketRequest, long clientId) {
        User client = userRepository.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException(User.class, clientId));
        Book book = bookRepository.findById(basketRequest.getBookId())
                .orElseThrow(() -> new NoSuchElementException(Book.class, basketRequest.getBookId()));

        double amountOfBook = book.getAmountOfBook() - (basketRequest.getQuantity());
        book.setAmountOfBook((int) amountOfBook);

        Basket basket = new Basket(basketRequest.getQuantity(), basketRequest.getPurchaseStatus(), book, client);
        HistoryOperation basketOperation = new HistoryOperation(client, basket);
        historyOperationRepository.save(basketOperation);

        client.getHistoryOperations().add(basketOperation);
        log.info("The client add books to the basket:{}", book.getId() + "- book id");

        return viewMapper.viewBasket(basketRepository.save(basket));
    }

    public BasketResponse updateBasket(BasketRequest basketRequest, long clientId) {
        log.info("Updating the contents of the shopping basket: ");
        return viewMapper.viewBasket(basketRepository.save(editMapper.updateBasket
                (basketRepository.findById(basketRequest.getBasketId())
                        .orElseThrow(() -> new NoSuchElementException(Basket.class, basketRequest.getBasketId())), basketRequest)));
    }

    public BasketResponse getBasketById(Long basketId) {
        log.info("Getting basket by id: {}", basketId + "- book id");
        return viewMapper.viewBasket(basketRepository.findById(basketId)
                .orElseThrow(() -> new NoSuchElementException(Basket.class, basketId)));
    }

    public void deleteBasket(Long basketId) {
        log.info("Delete basket by id: {}", basketId + "- basket id");
        Basket basket = basketRepository.findById(basketId).orElseThrow(() -> new NoSuchElementException(Basket.class, basketId));
        int addBackAmountOfBook = basket.getBook().getAmountOfBook() + basket.getQuantity();
        basket.getBook().setAmountOfBook(addBackAmountOfBook);
        basketRepository.deleteById(basket.getId());
    }

    public List<BasketResponse> getAllBaskets() {
        log.info("Getting all baskets: ");
        return viewMapper.viewAllBaskets(basketRepository.findAll());
    }

    public List<BasketResponse> getAllPurchasedBooks() {
        log.info("Getting all purchased books: ");
        return viewMapper.viewAllBaskets(basketRepository.findAllByPurchaseStatus(PurchaseStatus.FINISHED));
    }

    public BasketResponse promoCodeCalculation(long basketId, long bookId, String promoName) {
        Basket basket = basketRepository.findById(basketId).orElseThrow(() -> new NoSuchElementException(Basket.class, basketId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException(Book.class, bookId));

        if (promoName.matches("(.*)" + book.getPromoCode().getPromoName() + "(.*)")) {
            LocalDate currentTime = LocalDate.now();
            LocalDate expirationDate = book.getPromoCode().getFinishingDay();
            long day = currentTime.until(expirationDate, ChronoUnit.DAYS);
            if (day >= 0) {
                double percentage = (basket.getBasketPrice() * book.getPromoCode().getAmountOfPromo()) / 100;
                double discount = basket.getBasketPrice() - percentage;
                basket.setBasketPrice(discount);
                basket.setPurchaseStatus(PurchaseStatus.FINISHED);
                log.info("Promo Code successfully activated: " + basket.getBasketPrice());
            } else {
                basket.setBasketPrice(book.getPrice());
                log.info("Promo Code expired");
            }
        } else {
            basket.setBasketPrice(book.getPrice());
            log.info("Promo Code not valid");
        }
        return viewMapper.viewBasket(basketRepository.save(basket));
    }




}
