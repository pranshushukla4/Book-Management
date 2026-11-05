package com.examples.books.dto;
import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class BookRequestDTO { 

    @NotBlank(message = "Book code is required")
    @Size(max = 50, message = "Book code cannot exceed 50 characters")
    private String bookCode;

    @NotBlank(message = "Book title is required")
    @Size(max = 200, message = "Book title cannot exceed 200 characters")
    private String title;

    @NotBlank(message = "Book category is required")
    private String category;

    @NotNull(message = "Book price is required")
    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    private BigDecimal price;

    @Size(max = 500, message = "Comments cannot exceed 500 characters")
    private String comments;

    @NotNull(message = "Number of copies is required")
    @Min(value = 0, message = "Copies cannot be negative")
    private Integer copies;
}


