package com.library.backend.repository;

import com.library.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Boot is so smart, if we just name the method "findByEmail",
    // it automatically writes the SQL query for us!
    User findByEmail(String email);
}