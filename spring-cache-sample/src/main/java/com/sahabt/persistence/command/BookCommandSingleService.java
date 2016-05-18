package com.sahabt.persistence.command;

import com.sahabt.models.Book;
import com.sahabt.persistence.entities.BookEntity;
import com.sahabt.persistence.repositories.AuthorRepository;
import com.sahabt.persistence.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookCommandSingleService {

    private static final Random random = new Random();

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Book createNewBook(String isbn, String title, Integer authorId) {
        final BookEntity newBookEntity = BookEntity.builder()
                .isbn(isbn)
                .title(title)
                .author(authorRepository.getOne(0))
                .build();
        bookRepository.save(newBookEntity);

        if (authorId == 3) {
            throw new NotAllowedAuthorException();
        }

        final BookEntity anotherBookEntity = BookEntity.builder()
                .isbn(isbn)
                .title(title)
                .author(authorRepository.getOne(0))
                .build();
        bookRepository.save(anotherBookEntity);

        return null;
    }

    public class NotAllowedAuthorException extends RuntimeException {
    }
}
