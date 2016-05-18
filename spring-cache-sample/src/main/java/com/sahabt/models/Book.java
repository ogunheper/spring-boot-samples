package com.sahabt.models;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@ToString
public class Book implements Serializable {

    private final Integer id;
    private final String isbn;
    private final String title;
    private final String author;
}
