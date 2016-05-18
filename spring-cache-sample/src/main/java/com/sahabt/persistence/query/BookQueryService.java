package com.sahabt.persistence.query;

import com.google.common.base.Optional;
import com.sahabt.models.Book;
import com.sahabt.persistence.entities.AuthorEntity;
import com.sahabt.persistence.entities.BookEntity;
import com.sahabt.persistence.repositories.AuthorRepository;
import com.sahabt.persistence.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BookQueryService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Optional<Book> findBookById(Integer id) {
        log.info("Finding book with id {}", id);

        final AuthorEntity authorEntity = authorRepository.findOne(0);
        authorEntity.setSecondName("Veli");
        log.info("TEST");

        authorEntity.getBooks().forEach(bookEntity -> log.info("{}", bookEntity));

        final Optional<BookEntity> bookEntityOptional = bookRepository.findById(id);

        if (bookEntityOptional.isPresent()) {
            // bookEntityOptional.get().setAuthorOld("Ogün Heper");s
            return Optional.of(
                    Book.builder()
                            .id(bookEntityOptional.get().getId())
                            .isbn(bookEntityOptional.get().getIsbn())
                            .title(bookEntityOptional.get().getTitle())
                            .author(bookEntityOptional.get().getAuthor().getFirstName() + " " + bookEntityOptional.get().getAuthor().getLastName())
                            .build()
            );
        }

        return Optional.absent();
    }
}
