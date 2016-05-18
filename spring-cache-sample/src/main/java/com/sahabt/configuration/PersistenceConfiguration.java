package com.sahabt.configuration;

import com.sahabt.persistence.query.BookQueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {

    @Bean
    public BookQueryService bookQueryService() {
        return new BookQueryService();
    }
}
