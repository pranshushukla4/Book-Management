package com.examples.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.examples.books.entity.Book;
import com.examples.books.entity.BookCategory;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	 @Query("SELECT b FROM Book b WHERE b.category = :category " +
	           "AND (:bookCode IS NULL OR b.bookCode = :bookCode) " +
	           "AND (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))")
	    List<Book> searchBooks(
	            @Param("category") BookCategory category,
	            @Param("bookCode") String bookCode,
	            @Param("title") String title
	    );
	 
	 Boolean existsByBookCode(String bookCode);
	 
}
