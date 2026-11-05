package com.examples.books.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookSearchDTO {

    @NotBlank(message = "Please select book type")
    private String category;

    @Size(max = 50)
    private String bookCode;

    @Size(max = 200)
    private String title;
}