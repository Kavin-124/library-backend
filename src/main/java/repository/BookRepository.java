package com.library.backend.repository;

import com.library.backend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // That's it! Spring Boot automatically gives us save(), findAll(), deleteById(), etc.
}