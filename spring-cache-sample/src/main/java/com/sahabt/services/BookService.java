package com.sahabt.services;

import com.sahabt.models.Book;
import com.sahabt.persistence.query.BookQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookQueryService bookQueryService;

    public Book getBookById(Integer id) {
        return Book.builder().id(id).isbn("ISBN").title("Title").author("Author").build();
    }

    public void init() {
        log.info("Initializing {}", this.getClass());
    }

    public void destroy() {
        log.info("Destroying {}", this.getClass());
    }
}
