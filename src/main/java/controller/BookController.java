package com.library.backend.controller;

import com.library.backend.model.Book;
import com.library.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // 1. GET ALL BOOKS
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 2. CREATE A NEW BOOK
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // 3. DELETE A BOOK
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

    // 4. UPDATE A BOOK
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        updatedBook.setId(id);
        return bookRepository.save(updatedBook);
    }

    // 5. BORROW A BOOK (NEW!)
    @PutMapping("/{id}/borrow")
    public Book borrowBook(@PathVariable Long id, @RequestParam String userEmail) {
        Book book = bookRepository.findById(id).orElseThrow();
        if (book.isAvailable()) {
            book.setAvailable(false);
            book.setBorrowedBy(userEmail);
            return bookRepository.save(book);
        }
        throw new RuntimeException("Book is already checked out!");
    }

    // 6. RETURN A BOOK (NEW!)
    @PutMapping("/{id}/return")
    public Book returnBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setAvailable(true);
        book.setBorrowedBy(null); // Clear the user's name when returned
        return bookRepository.save(book);
    }
}