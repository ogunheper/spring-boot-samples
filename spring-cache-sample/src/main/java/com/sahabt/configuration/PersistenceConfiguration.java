package com.sahabt.configuration;

import com.sahabt.persistence.command.BookCommandService;
import com.sahabt.persistence.query.BookQueryService;
import com.sahabt.persistence.repositories.AuthorRepository;
import com.sahabt.persistence.repositories.BookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {

    @Bean
    public BookQueryService bookQueryService(BookRepository bookRepository, AuthorRepository authorRepository) {
        return new BookQueryService(bookRepository, authorRepository);
    }

    @Bean
    public BookCommandService bookCommandService(BookRepository bookRepository, AuthorRepository authorRepository) {
        return new BookCommandService(bookRepository, authorRepository);
    }
}
