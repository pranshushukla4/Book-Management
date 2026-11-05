package com.examples.books.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponseDTO {

    private Long id;

    private String bookCode;

    private String title;

    private String category;

    private BigDecimal price;

    private String comments;

    private Integer copies;
}