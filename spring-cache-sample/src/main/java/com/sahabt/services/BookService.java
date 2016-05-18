package com.sahabt.services;

import com.google.common.base.Optional;
import com.sahabt.models.Book;
import com.sahabt.models.request.CreateBookRequest;
import com.sahabt.persistence.command.BookCommandService;
import com.sahabt.persistence.query.BookQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookQueryService bookQueryService;
    private final BookCommandService bookCommandService;

    @Cacheable(cacheNames = "bookCache", key = "#id", unless = "!#result.isPresent()")
    public Optional<Book> getBookById(Integer id) {
        final Optional<Book> bookOptional = bookQueryService.findBookById(id);
        return bookOptional;
    }

    @CacheEvict(cacheNames = "bookCache", beforeInvocation = true)
    public void deleteBookById(Integer id) {
        throw new RuntimeException("an exception occurred");
    }

    @CacheEvict(cacheNames = "bookCache", key = "#id", beforeInvocation = true)
    public void updateBookTitleById(Integer id, String title) {
    }

    @CacheEvict(cacheNames = "bookCache", key = "#id", beforeInvocation = true)
    @CachePut(cacheNames = "bookCache", key = "#id")
    public Optional<Book> updateBookTitleByIdAndReturn(Integer id, String title) {
        log.info("running update");
        throw new RuntimeException("an exception occurred");
        /*
        return Optional.of(
                Book.builder().id(id).isbn("ISBN").title(title).authorOld("Author").build()
        );
        */
    }

    @CacheEvict(cacheNames = "bookCache", beforeInvocation = true, allEntries = true)
    public void clearBookCache() {
    }

    public void createNewBook(CreateBookRequest createBookRequest) {
        bookCommandService.createNewBook(createBookRequest.getIsbn(), createBookRequest.getTitle(), 0);
    }

    public void createNewBooks(int count) {
        bookCommandService.createNewBooks(count);
    }

    public void init() {
        log.info("Initializing {}", this.getClass());
    }

    public void destroy() {
        log.info("Destroying {}", this.getClass());
    }
}
