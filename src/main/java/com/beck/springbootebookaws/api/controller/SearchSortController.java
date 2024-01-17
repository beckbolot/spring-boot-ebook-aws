package com.beck.springbootebookaws.api.controller;

import com.beck.springbootebookaws.api.payload.book.BookResponseView;
import com.beck.springbootebookaws.db.entity.Book;
import com.beck.springbootebookaws.db.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/search-filter")
@Tag(name = "Search API", description = "The search endpoints")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class SearchSortController {

    private final BookService bookService;

    @Operation(summary = "Allows to search all books from the database")
    @GetMapping("search")
    public BookResponseView searchAndPagination(@RequestParam(name = "name", required = false) String name,
                                                @RequestParam int page) {
        log.info("Inside Book controller search and pagination book method");
        return bookService.searchAndPagination(name, page - 1);
    }

    @Operation(summary = "Allows to sort all books from the database")
    @GetMapping("sort/{pageNumber}/{pageSize}/{sortProperty}")
    public Page<Book> sortAndPagination(@PathVariable Integer pageNumber,
                                        @PathVariable Integer pageSize,
                                        @PathVariable String sortProperty) {
        log.info("Inside Book controller sort and pagination book method");
        return bookService.sortAndPagination(pageNumber, pageSize, sortProperty);
    }
}
