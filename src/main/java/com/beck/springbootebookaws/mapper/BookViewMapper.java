package com.beck.springbootebookaws.mapper;

import com.beck.springbootebookaws.api.payload.book.BookResponse;
import com.beck.springbootebookaws.db.entity.Book;
import com.beck.springbootebookaws.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookViewMapper {

    private final BookRepository bookRepository;

    public BookResponse viewBook(Book book){
        if (book == null){
            return null;
        }
        BookResponse response = new BookResponse();
        if (book.getId() !=null){
            response.setId(book.getId());
        }
        response.setImage(book.getImage());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setPublishingHouse(book.getPublishingHouse());
        response.setAboutTheBook(book.getAboutTheBook());
        response.setBookFragment(book.getBookFragment());
        response.setBookLanguage(book.getBookLanguage());
        response.setYearOfIssue(book.getYearsOfIssie());
        response.setPageVolume(book.getPageOfVolume());
        response.setPrice(book.getPrice());
        response.setAmountOfBooks(book.getAmountOfBook());
        response.setDiscount(book.getDiscount());
        response.setBestseller(book.getIsBestSeller());
        response.setPromocode(book.getPromoCode());
        response.setStatus(book.getStatus());
        response.setComments(book.getComments());
        response.setGenreEnum(book.getGenreEnum());
        response.setTypeOfBook(book.getTypeOfBook());

        return response;
    }

    public List<BookResponse> viewBooks(List<Book> books){
        List<BookResponse> responses = new ArrayList<>();
        for (Book book: books){
            responses.add(viewBook(book));
        }
        return responses;
    }

    public List<Book> searchBook(String name, Pageable pageable){
        String text = name == null ? "":name;
        return bookRepository.searchAndPagination(text.toUpperCase(),pageable);
    }


}
