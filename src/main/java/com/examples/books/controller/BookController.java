package com.examples.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.examples.books.dto.BookCopyUpdateDTO;
import com.examples.books.dto.BookRequestDTO;
import com.examples.books.dto.BookSearchDTO;
import com.examples.books.service.BookServiceImpl;

import jakarta.validation.Valid;

@Controller
public class BookController {

    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        super();
        this.bookService = bookService;
    }

    
    // Open Search Page and display default SOCIAL books
    @GetMapping("/books")
    public String showBooks(
            @ModelAttribute BookSearchDTO search,
            Model model) {

        if (search.getCategory() == null ||
                search.getCategory().isBlank()) {
            search.setCategory("BIOLOGY");
        }

        model.addAttribute("search", search);
        model.addAttribute("books", bookService.searchBooks(search));

        return "book";
    }

    
    // Open Create Book Page
    @GetMapping("/books/create")
    public String showCreatePage(Model model) {
        model.addAttribute("book", new BookRequestDTO());
        return "bookdetails";
    }

    // Create Book
    @PostMapping("/books/create")
    public String createBook(
            @Valid @ModelAttribute("book") BookRequestDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("errorMessage",
                    result.getAllErrors().get(0).getDefaultMessage());
            return "bookdetails";
        }

        try {
            bookService.createBook(dto);

            redirectAttributes.addFlashAttribute(
                    "successMessage", "Book created successfully!");

            return "redirect:/books?category=" + dto.getCategory();

        } catch (Exception e) {

            String message = e.getMessage();

            if (message != null &&
                    message.toLowerCase().contains("already exists")) {
                model.addAttribute("errorMessage", message);
            } else {
                model.addAttribute("errorMessage",
                        "Unable to create book. Please try again.");
            }

            return "bookdetails";
        }
    }


    
 // Buy Book
    @PostMapping("/books/{id}/buy")
    public String buyBook(
            @PathVariable Long id,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String bookCode,
            @RequestParam(required = false) String title,
            RedirectAttributes redirectAttributes) {

        try {
            bookService.buyBook(id);
            redirectAttributes.addFlashAttribute(
                    "successMessage", "Book purchased successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "Unable to buy book.");
        }

        redirectAttributes.addAttribute("category", category);
        redirectAttributes.addAttribute("bookCode", bookCode);
        redirectAttributes.addAttribute("title", title);

        return "redirect:/books";
    }

    // Add One More Copy
    @PostMapping("/books/{id}/add-copy")
    public String addCopy(
            @PathVariable Long id,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String bookCode,
            @RequestParam(required = false) String title,
            RedirectAttributes redirectAttributes) {

        try {
            BookCopyUpdateDTO dto = new BookCopyUpdateDTO();
            dto.setCopies(1);

            bookService.addCopies(id, dto);
            redirectAttributes.addFlashAttribute(
                    "successMessage", "One copy added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "Unable to add copy.");
        }

        redirectAttributes.addAttribute("category", category);
        redirectAttributes.addAttribute("bookCode", bookCode);
        redirectAttributes.addAttribute("title", title);

        return "redirect:/books";
    }

    // Delete Book
    @PostMapping("/books/{id}/delete")
    public String deleteBook(
            @PathVariable Long id,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String bookCode,
            @RequestParam(required = false) String title,
            RedirectAttributes redirectAttributes) {

        try {
            bookService.deleteBook(id);
            redirectAttributes.addFlashAttribute(
                    "successMessage", "Book deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage", "Unable to delete book.");
        }

        redirectAttributes.addAttribute("category", category);
        redirectAttributes.addAttribute("bookCode", bookCode);
        redirectAttributes.addAttribute("title", title);

        return "redirect:/books";
    }
}