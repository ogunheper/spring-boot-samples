package com.sahabt.controllers;

import com.google.common.collect.Lists;
import com.sahabt.models.Book;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/books", method = RequestMethod.GET)
public class BookController {

    @RequestMapping
    public Collection<Book> getBooks() {
        return Lists.newArrayList(
                Book.builder().id(1).isbn("xyz").title("Küçük Prens").author("Aunt...").build(),
                Book.builder().id(2).isbn("abc").title("Tutunamayanlar").author("Oğuz Atay").build()
        );
    }

    @RequestMapping(path = "/{id}/{isbn}")
    public Book getBooksById(
            @PathVariable(value = "id") String isbn,
            @PathVariable(value = "isbn") Integer id,
            @RequestParam(defaultValue = "Vaillant") String title,
            @RequestHeader(name = "x-access-token") String accessToken) {
        return
                Book.builder().id(id).isbn(isbn).title(title).author("Author").build();
    }
}
