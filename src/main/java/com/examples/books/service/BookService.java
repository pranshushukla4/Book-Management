package com.examples.books.service;

import java.util.List;

import com.examples.books.dto.BookCopyUpdateDTO;
import com.examples.books.dto.BookRequestDTO;
import com.examples.books.dto.BookResponseDTO;
import com.examples.books.dto.BookSearchDTO;

interface BookService {
		
		BookResponseDTO createBook(BookRequestDTO dto);

	    List<BookResponseDTO> searchBooks(BookSearchDTO dto);

	    BookResponseDTO buyBook(Long id);

	    BookResponseDTO addCopies(Long id, BookCopyUpdateDTO dto);
	    
	    void deleteBook(Long id);
}
