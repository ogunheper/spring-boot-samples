package com.sahabt.models.request;

import lombok.Data;

@Data
public class CreateBookRequest {

    private String isbn;
    private String title;
    private String author;
}
