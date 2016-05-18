package com.sahabt.configuration;

import com.sahabt.persistence.command.BookCommandService;
import com.sahabt.persistence.query.BookQueryService;
import com.sahabt.services.BookService;
import com.sahabt.services.LibrarianService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class ServiceConfiguration {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BookService bookService(BookQueryService bookQueryService, BookCommandService bookCommandService) {
        return new BookService(bookQueryService, bookCommandService);
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public LibrarianService librarianService() {
        return new LibrarianService();
    }
}
