package com.beck.springbootebookaws.db.services;

import com.beck.springbootebookaws.api.payload.book.BookRequest;
import com.beck.springbootebookaws.api.payload.promoCode.PromoCodeRequest;
import com.beck.springbootebookaws.api.payload.vendor.VendorRequest;
import com.beck.springbootebookaws.api.payload.vendor.VendorResponse;
import com.beck.springbootebookaws.db.entity.Book;
import com.beck.springbootebookaws.db.entity.PromoCode;
import com.beck.springbootebookaws.db.entity.User;
import com.beck.springbootebookaws.db.enums.RequestStatus;
import com.beck.springbootebookaws.exceptions.NoSuchElementException;
import com.beck.springbootebookaws.mapper.BookEditMapper;
import com.beck.springbootebookaws.mapper.PromoCodeEditMapper;
import com.beck.springbootebookaws.mapper.VendorEditMapper;
import com.beck.springbootebookaws.mapper.VendorViewMapper;
import com.beck.springbootebookaws.repository.BookRepository;
import com.beck.springbootebookaws.repository.PromoCodeRepository;
import com.beck.springbootebookaws.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VendorService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final VendorEditMapper editMapper;
    private final VendorViewMapper viewMapper;
    private final BookEditMapper bookEditMapper;
    private final BookRepository bookRepository;
    private final PromoCodeEditMapper promocodeEditMapper;
    private final PromoCodeRepository promocodeRepository;

    public VendorResponse register(VendorRequest request) {
        User vendor = editMapper.createVendor(request);
        vendor.setPassword(passwordEncoder.encode(request.getPassword()));
        vendor.isActive();
        repository.save(vendor);
        log.info("The vendor has successfully registered: {}", vendor.getFirstName());
        return viewMapper.viewVendor(vendor);
    }

    public VendorResponse update(Long id, VendorRequest request) {
        User vendor = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        editMapper.updateVendor(vendor, request);
        log.info("The vendor has successfully updated his data: {}", vendor.getFirstName());
        return viewMapper.viewVendor(repository.save(vendor));
    }

    public VendorResponse deleteById(Long id) {
        User vendor = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        repository.deleteById(id);
        log.info("The vendor was successfully removed by ID from the database: {}", vendor.getFirstName());
        return viewMapper.viewVendor(vendor);
    }

    public List<VendorResponse> getAllVendors() {
        log.info("Getting all vendors: ");
        return viewMapper.viewVendors();
    }

    public VendorResponse gitById(Long id) {
        User vendor = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        log.info("getting a vendor by id: {}", vendor.getFirstName());
        return viewMapper.viewVendorById(vendor);
    }

    public VendorResponse addBookToVendor(BookRequest request, Long id) {
        Book book = bookEditMapper.createBook(request);
        book.setStatus(RequestStatus.SUBMITTED);
        User user = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        book.setUser(user);
        bookRepository.save(book);
        repository.save(user);
        log.info("The vendor has successfully added his book to the database: {}", book.getTitle());
        return viewMapper.viewVendor(user);
    }

    public VendorResponse updateBookVendor(Long userId, Long bookId, BookRequest request) {
        User user = repository.findById(userId).orElseThrow(() ->
                new NoSuchElementException(User.class, userId));
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new NoSuchElementException(Book.class, bookId));
        bookEditMapper.updateBook(book, request);
        book.setUser(user);
        book.setStatus(RequestStatus.SUBMITTED);
        bookRepository.save(book);
        repository.save(user);
        log.info("The vendor has successfully updated the book data: {}", book.getTitle());
        return viewMapper.viewVendor(user);
    }

    public VendorResponse deleteBookVendor(Long userId, Long bookId) {
        User user = repository.findById(userId).orElseThrow(() ->
                new NoSuchElementException(User.class, userId));
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new NoSuchElementException(Book.class, bookId));
        bookRepository.deleteById(bookId);
        repository.save(user);
        log.info("The vendor has successfully deleted his book: {}", book.getTitle());
        return viewMapper.viewVendor(user);
    }

    public VendorResponse addPromoCode(PromoCodeRequest request, Long id) {
        PromoCode promocode = promocodeEditMapper.create(request);
        User user = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        List<Book> bookList = user.getBooks();
        for (Book book : bookList) {
            book.setPromoCode(promocode);
        }
        promocode.setUser(user);
        promocodeRepository.save(promocode);
        repository.save(user);
        log.info("The vendor has successfully added a promo code: {}", promocode.getPromoName());
        return viewMapper.viewVendor(user);
    }

    public VendorResponse updatePromoCode(PromoCodeRequest request, Long vendorId, Long promoCodeId) {
        User user = repository.findById(vendorId).orElseThrow(() ->
                new NoSuchElementException(User.class, vendorId));
        PromoCode promocode = promocodeRepository.findById(promoCodeId).orElseThrow(() ->
                new NoSuchElementException(PromoCode.class, promoCodeId));
        promocodeEditMapper.update(promocode, request);
        List<Book> bookList = user.getBooks();
        for (Book book : bookList) {
            book.setPromoCode(promocode);
        }
        promocode.setUser(user);
        promocodeRepository.save(promocode);
        repository.save(user);
        log.info("The vendor has successfully updated the promo code: {}", promocode.getPromoName());
        return viewMapper.viewVendor(user);
    }

    public VendorResponse deletePromoCode(Long vendorId, Long promoCodeId) {
        User user = repository.findById(vendorId).orElseThrow(() ->
                new NoSuchElementException(User.class, vendorId));
        PromoCode promocode = promocodeRepository.findById(promoCodeId).orElseThrow(() ->
                new NoSuchElementException(PromoCode.class, promoCodeId));
        promocodeRepository.delete(promocode);
        repository.save(user);
        log.info("The vendor has successfully deleted the promo code: {}", promocode.getPromoName());
        return viewMapper.viewVendor(user);
    }




}
