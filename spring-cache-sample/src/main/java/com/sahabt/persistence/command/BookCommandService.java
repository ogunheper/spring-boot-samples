package com.sahabt.persistence.command;

import com.sahabt.models.Book;
import com.sahabt.persistence.entities.BookEntity;
import com.sahabt.persistence.repositories.AuthorRepository;
import com.sahabt.persistence.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class BookCommandService {

    private static final Random random = new Random();

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    private BookCommandSingleService bookCommandSingleService;

    public void createNewBooks(int count) {
        int createdBookCount = 0;

        for (int i = 0; i < count; ++i) {
            // final Object savepoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();

            try {
                bookCommandSingleService.createNewBook(
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        random.nextInt(5)
                );
                ++createdBookCount;
                // TransactionAspectSupport.currentTransactionStatus().releaseSavepoint(savepoint);

            } catch (BookCommandSingleService.NotAllowedAuthorException e) {
                log.warn("NotAllowedAuthorException");
                // TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savepoint);
            }
        }

        log.info("createdBookCount: {}", createdBookCount);
    }

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

    private class NotAllowedAuthorException extends RuntimeException {
    }
}
