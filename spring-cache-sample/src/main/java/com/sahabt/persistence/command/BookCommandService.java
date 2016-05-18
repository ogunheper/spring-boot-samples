package com.sahabt.persistence.command;

import com.sahabt.models.Book;
import com.sahabt.persistence.entities.BookEntity;
import com.sahabt.persistence.repositories.AuthorRepository;
import com.sahabt.persistence.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class BookCommandService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Book createNewBook(String isbn, String title, String author) {
        final BookEntity newBookEntity = BookEntity.builder()
                .isbn(isbn)
                .title(title)
                .author(authorRepository.getOne(0))
                .build();
        bookRepository.save(newBookEntity);

        newBookEntity.setTitle("Title is modified");
        bookRepository.save(newBookEntity);

        return null;
    }
}
