package com.sahabt.persistence.query;

import com.google.common.base.Optional;
import com.sahabt.models.Book;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookQueryService {

    public Optional<Book> findBookById(Integer id) {

        log.info("Finding book with id {}", id);

        if (id == 1) {
            return Optional.absent();
        }

        return Optional.of(
                Book.builder().id(id).isbn("ISBN").title("Title").author("Author").build()
        );
    }
}
