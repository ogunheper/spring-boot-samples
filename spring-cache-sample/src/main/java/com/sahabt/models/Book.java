package com.sahabt.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

    private final Integer id;
    private final String isbn;
    private final String title;
    private final String author;

    // private final ImmutableList<Integer> integerList;
}
