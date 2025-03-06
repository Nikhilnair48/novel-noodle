package com.novel.noodle.controllers;

import com.novel.noodle.models.Book;
import com.novel.noodle.models.Review;
import com.novel.noodle.repositories.BookRepository;
import com.novel.noodle.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@GetMapping
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		return bookRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		Book savedBook = bookRepository.save(book);
		return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	}
	
	@PostMapping("/{bookId}/reviews")
	public ResponseEntity<Review> addReview(@PathVariable Long bookId, @RequestBody Review review) {
		return bookRepository.findById(bookId)
				.map(book -> {
					review.setBook(book);
					Review savedReview = reviewRepository.save(review);
					return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
				})
				.orElse(ResponseEntity.notFound().build());
	}
}