package com.beck.springbootebookaws.db.services;

import com.beck.springbootebookaws.api.payload.book.BookRequest;
import com.beck.springbootebookaws.api.payload.book.BookResponse;
import com.beck.springbootebookaws.api.payload.book.BookResponseView;
import com.beck.springbootebookaws.db.entity.Basket;
import com.beck.springbootebookaws.db.entity.Book;
import com.beck.springbootebookaws.db.entity.User;
import com.beck.springbootebookaws.db.enums.Genre;
import com.beck.springbootebookaws.db.enums.RequestStatus;
import com.beck.springbootebookaws.db.enums.TypeOfBook;
import com.beck.springbootebookaws.exceptions.NoSuchElementException;
import com.beck.springbootebookaws.mapper.BookViewMapper;
import com.beck.springbootebookaws.repository.BookRepository;
import com.beck.springbootebookaws.repository.UserRepository;
import com.beck.springbootebookaws.repository.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository vendorRepository;
    private final BookViewMapper viewMapper;

    public BookResponse updateRequestStatus(long bookId, BookRequest request){
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException(Basket.class, bookId));
        book.setStatus(request.getStatus());
        book.setComments(request.getComments());
        log.info("Successfully updated requested book status to: {}",book.getStatus());

        return viewMapper.viewBook(bookRepository.save(book));
    }

    public BookResponse getBookById(long id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException(Basket.class, id));
        log.info("Getting book by id: {}",id + "-book id");
        return viewMapper.viewBook(book);
    }

    public List<BookResponse> getAllBooks() {
        log.info("Getting all books: ");
        return viewMapper.viewBooks(bookRepository.findAll());
    }

    public String countBooks(Long vendorId) {
        User vendor = vendorRepository.findById(vendorId).orElseThrow(() ->
                new NoSuchElementException(User.class, vendorId));
        List<Book> booksOfVendor = new ArrayList<>(vendor.getBooks());
        log.info("Vendor's book quantities: {}", booksOfVendor.size() + ": count books");
        return vendor + " book quantity: " + booksOfVendor.size();
    }

    public List<BookResponse> getAllVendorBooks(long vendorId){
        List<BookResponse> responses = new ArrayList<>();
        User vendor = vendorRepository.findById(vendorId).orElseThrow(() -> new NoSuchElementException(User.class, vendorId));
        for (Book book: vendor.getBooks()){
            responses.add(viewMapper.viewBook(book));
        }
        log.info("Getting all vendor`s books: {}",responses);
        return responses;
    }

    public List<BookResponse> getAllSubmittedBooks(int page) {
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        log.info("Getting all the books from the submitted application: ");
        return viewMapper.viewBooks(bookRepository.findAllByRequestStatus(RequestStatus.SUBMITTED, pageable));
    }

    public List<BookResponse> getAllApprovedBooks(int page) {
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        log.info("Getting books by type: ");
        return viewMapper.viewBooks(bookRepository.findAllByRequestStatus(RequestStatus.APPROVED, pageable));
    }

    public List<BookResponse> getAllApprovedBookByGenreAndType(Genre genre, TypeOfBook type, int page) {
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Specification<Book> filter = BookSpecification.getByStatusAndTypeOfBook(genre,type,RequestStatus.APPROVED);
        log.info("Method for filtering all books by genre and type: ");
        return viewMapper.viewBooks(bookRepository.findAll(filter, pageable));
    }

    public BookResponseView searchAndPagination(String name, Integer page) {
        int size = 10;
        BookResponseView responseView = new BookResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setBookResponses((viewMapper.viewBooks(viewMapper.searchBook(name, pageable))));
        log.info("Book search: ");
        return responseView;
    }

    public Page<Book> sortAndPagination(Integer pageNumber, Integer pageSize, String sortProperty) {
        Pageable pageable;
        pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, Objects.requireNonNullElse(sortProperty, "sortProperty"));
        log.info("Book sort: ");
        return bookRepository.findAll(pageable);
    }

    public List<BookResponse> filterByGenreAndTypeOfBooks(Genre genre, TypeOfBook type, int page) {
        int size = 10;
        Specification<Book> filter = BookSpecification.getFilter(genre, type);
        Pageable pageable = PageRequest.of(page, size);
        log.info("Sorting by genre and type: ");
        return viewMapper.viewBooks(bookRepository.findAll(filter, pageable));
    }







}
