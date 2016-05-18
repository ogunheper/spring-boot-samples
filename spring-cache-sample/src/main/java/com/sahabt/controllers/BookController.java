package com.sahabt.controllers;

import com.google.common.collect.Lists;
import com.sahabt.models.Book;
import com.sahabt.services.BookService;
import com.sahabt.services.LibrarianService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books", method = RequestMethod.GET)
@Slf4j
public class BookController {

    private final BookService bookService;
    private final LibrarianService librarianService;

    @RequestMapping
    public Collection<Book> getBooks() {
        return Lists.newArrayList(
                Book.builder().id(1).isbn("xyz").title("Küçük Prens").author("Aunt...").build(),
                Book.builder().id(2).isbn("abc").title("Tutunamayanlar").author("Oğuz Atay").build()
        );
    }

    @RequestMapping(path = "/{id}/{isbn}")
    public Book getBooksByIdSample(
            @PathVariable(value = "id") String isbn,
            @PathVariable(value = "isbn") Integer id,
            @RequestParam(defaultValue = "Vaillant") String title,
            @RequestHeader(name = "x-access-token") String accessToken) {
        return Book.builder().id(id).isbn(isbn).title(title).author("Author").build();
    }

    @RequestMapping(path = "/{id}")
    public Book getBooksById(@PathVariable Integer id) {

        log.info(librarianService.whoAmI());
        return bookService.getBookById(id);
    }
}
