package com.sahabt.configuration;

import com.sahabt.controllers.BookController;
import com.sahabt.services.BookService;
import com.sahabt.services.LibrarianService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {

    @Bean
    public BookController bookController(BookService bookService, LibrarianService librarianService) {
        return new BookController(bookService, librarianService);
    }
}
