package com.examples.books.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examples.books.dto.BookCopyUpdateDTO;
import com.examples.books.dto.BookRequestDTO;
import com.examples.books.dto.BookResponseDTO;
import com.examples.books.dto.BookSearchDTO;
import com.examples.books.entity.Book;
import com.examples.books.entity.BookCategory;
import com.examples.books.exception.DuplicateResourceException;
import com.examples.books.exception.InvalidBookOperationException;
import com.examples.books.exception.ResourceNotFoundException;
import com.examples.books.mapper.BookMapper;
import com.examples.books.repository.BookRepository;

@Service 
public class BookServiceImpl {

	private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    
    
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
		super();
		this.bookRepository = bookRepository;
		this.bookMapper = bookMapper;
	}

	
    
    //  Create Book
    public BookResponseDTO createBook(BookRequestDTO dto) {

        if (bookRepository.existsByBookCode(dto.getBookCode())) {
            throw new DuplicateResourceException(
                    "Book ID already exists: " + dto.getBookCode());
        }

        Book book = bookMapper.toEntity(dto);

        Book savedBook = bookRepository.save(book);

        return bookMapper.toResponseDTO(savedBook);
    }

    
    //  Search Books
    public List<BookResponseDTO> searchBooks(BookSearchDTO dto) {

        BookCategory category =
                BookCategory.valueOf(dto.getCategory());

        String bookCode = dto.getBookCode();
        String title = dto.getTitle();

        if (bookCode != null && bookCode.trim().isEmpty()) {
            bookCode = null;
        }

        if (title != null && title.trim().isEmpty()) {
            title = null;
        }

        List<Book> books = bookRepository.searchBooks(
                category, bookCode, title);

        return books.stream()
                .map(bookMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    

    //  Delete Book
    public void deleteBook(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Book not found with ID: " + id));

        bookRepository.delete(book);
    }

    //  Add More Copies
    public BookResponseDTO addCopies(
            Long id, BookCopyUpdateDTO dto) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Book not found with ID: " + id));

        book.setCopies(book.getCopies() + dto.getCopies());

        Book updatedBook = bookRepository.save(book);

        return bookMapper.toResponseDTO(updatedBook);
    }

    
    //  Buy Book (Decrease copies by one)
    @Transactional
    public BookResponseDTO buyBook(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Book not found with ID: " + id));

        if (book.getCopies() == null || book.getCopies() <= 0) {
            throw new InvalidBookOperationException(
                    "Book is out of stock");
        }

        if (book.getPrice() == null) {
            throw new InvalidBookOperationException(
                    "Book price is not available");
        }

        book.setCopies(book.getCopies() - 1);

        Book updatedBook = bookRepository.save(book);

        return bookMapper.toResponseDTO(updatedBook);
    }
}
