package com.examples.books.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCopyUpdateDTO {

    @NotNull(message = "Number of copies is required")
    @Min(value = 1, message = "At least one copy must be added")
    private Integer copies;
}