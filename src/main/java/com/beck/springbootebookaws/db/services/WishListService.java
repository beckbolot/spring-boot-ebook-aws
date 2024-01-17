package com.beck.springbootebookaws.db.services;

import com.beck.springbootebookaws.api.payload.wishlist.WishListRequest;
import com.beck.springbootebookaws.api.payload.wishlist.WishListResponse;
import com.beck.springbootebookaws.db.entity.Book;
import com.beck.springbootebookaws.db.entity.HistoryOperation;
import com.beck.springbootebookaws.db.entity.User;
import com.beck.springbootebookaws.db.entity.WishList;
import com.beck.springbootebookaws.exceptions.NoSuchElementException;
import com.beck.springbootebookaws.mapper.WishListViewMapper;
import com.beck.springbootebookaws.repository.BookRepository;
import com.beck.springbootebookaws.repository.HistoryOperationRepository;
import com.beck.springbootebookaws.repository.UserRepository;
import com.beck.springbootebookaws.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishListService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final WishListViewMapper viewMapper;
    private final WishListRepository wishListRepository;
    private final HistoryOperationRepository historyOperationRepo;

    public WishListResponse addWishList(WishListRequest request, Long clientId) {
        User client = userRepository.findById(clientId).orElseThrow(() ->
                new NoSuchElementException(User.class, clientId));
        Book book = bookRepository.findById(request.getBookId()).orElseThrow(() ->
                new NoSuchElementException(Book.class, request.getBookId()));
        WishList wishList = new WishList(client, book);
        HistoryOperation wishListOperation = new HistoryOperation(client,wishList);
        historyOperationRepo.save(wishListOperation);
        client.getHistoryOperations().add(wishListOperation);
        log.info("The client adds books to wishlist: {}", book.getTitle());
        return viewMapper.viewWishList(wishListRepository.save(wishList));
    }

    public WishListResponse getWishListById(Long id) {
        log.info("Getting wishlist by id: {}", id);
        return viewMapper.viewWishList(wishListRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(WishList.class, id)));
    }

    public void deleteWishList(Long id) {
        log.info("Deleted wishlist by id: {}", id);
        wishListRepository.delete(wishListRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(WishList.class, id)));
    }

    public List<WishListResponse> getAllWishLists() {
        log.info("Getting all wishlists");
        return viewMapper.viewAllWishLists(wishListRepository.findAll());
    }




}
