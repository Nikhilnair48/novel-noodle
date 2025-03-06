package com.novel.noodle.repositories;

import com.novel.noodle.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}