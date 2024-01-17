package com.beck.springbootebookaws.mapper;

import com.beck.springbootebookaws.api.payload.book.BookRequest;
import com.beck.springbootebookaws.db.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookEditMapper {

    public Book createBook(BookRequest bookRequest) {
        if (bookRequest == null) {
            return null;
        }
        Book book = new Book();
        book.setImage(bookRequest.getImage());
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublishingHouse(bookRequest.getPublishingHouse());
        book.setAboutTheBook(bookRequest.getAboutTheBook());
        book.setBookFragment(bookRequest.getBookFragment());
        book.setBookLanguage(bookRequest.getBookLanguage());
        book.setYearsOfIssie(bookRequest.getYearOfIssue());
        book.setPageOfVolume(bookRequest.getPageVolume());
        book.setPrice(bookRequest.getPrice());
        book.setAmountOfBook(bookRequest.getAmountOfBooks());
        book.setDiscount(bookRequest.getDiscount());
        book.setIsBestSeller(bookRequest.getBestseller());
        book.setGenreEnum(bookRequest.getGenreEnum());
        book.setTypeOfBook(bookRequest.getTypeOfBook());
        return book;
    }

    public Book updateBook(Book book, BookRequest bookRequest) {
        book.setImage(bookRequest.getImage());
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublishingHouse(bookRequest.getPublishingHouse());
        book.setAboutTheBook(bookRequest.getAboutTheBook());
        book.setBookFragment(bookRequest.getBookFragment());
        book.setBookLanguage(bookRequest.getBookLanguage());
        book.setYearsOfIssie(bookRequest.getYearOfIssue());
        book.setPageOfVolume(bookRequest.getPageVolume());
        book.setPrice(bookRequest.getPrice());
        book.setAmountOfBook(bookRequest.getAmountOfBooks());
        book.setDiscount(bookRequest.getDiscount());
        book.setIsBestSeller(bookRequest.getBestseller());
        book.setStatus(bookRequest.getStatus());
        book.setComments(bookRequest.getComments());
        book.setGenreEnum(bookRequest.getGenreEnum());
        book.setTypeOfBook(bookRequest.getTypeOfBook());
        return book;
    }




}
