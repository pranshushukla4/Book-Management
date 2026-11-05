package com.examples.books.mapper;

import org.springframework.stereotype.Component;

import com.examples.books.dto.BookRequestDTO;
import com.examples.books.dto.BookResponseDTO;
import com.examples.books.entity.Book;
import com.examples.books.entity.BookCategory;

@Component
public class BookMapper {

    // Convert Request DTO to Entity
    public Book toEntity(BookRequestDTO dto) {

        if (dto == null) {
            return null;
        } 

        Book book = new Book();

        book.setBookCode(dto.getBookCode());
        book.setTitle(dto.getTitle());
        book.setCategory(BookCategory.valueOf(dto.getCategory()));
        book.setPrice(dto.getPrice());
        book.setComments(dto.getComments());
        book.setCopies(dto.getCopies());

        return book;
    }

    
    
    // Convert Entity to Response DTO
    public BookResponseDTO toResponseDTO(Book book) {

        if (book == null) {
            return null;
        }

        BookResponseDTO dto = new BookResponseDTO();

        dto.setId(book.getId());
        dto.setBookCode(book.getBookCode());
        dto.setTitle(book.getTitle());
        dto.setCategory(book.getCategory().name());
        dto.setPrice(book.getPrice());
        dto.setComments(book.getComments());
        dto.setCopies(book.getCopies());
        

        return dto;
    }

    // Update existing Entity using Update DTO
//    public void updateEntity(BookUpdateDTO dto, Book book) {
//
//        if (dto == null || book == null) {
//            return;
//        }
//
//        book.setTitle(dto.getTitle());
//        book.setCategory(BookCategory.valueOf(dto.getCategory()));
//        book.setPrice(dto.getPrice());
//        book.setComments(dto.getComments());
//        book.setCopies(dto.getCopies());
//    }
}
