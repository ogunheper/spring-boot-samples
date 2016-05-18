package com.sahabt.services;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class LibrarianService {

    private static final AtomicInteger counter = new AtomicInteger(0);
    private final Integer id;

    public LibrarianService() {
        this.id = counter.getAndIncrement();
    }

    public String whoAmI() {
        return "I'm librarian " + this.id + ".";
    }

    public void init() {
        log.info("Initializing {}", this.getClass());
    }

    public void destroy() {
        log.info("Destroying {}", this.getClass());
    }
}
