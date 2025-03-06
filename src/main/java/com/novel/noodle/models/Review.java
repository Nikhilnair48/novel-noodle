package com.novel.noodle.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String reviewerName;
	private String reviewText;
	private int rating;
	
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	
	public Review(String reviewerName, String reviewText, int rating, Book book) {
		this.reviewerName = reviewerName;
		this.reviewText = reviewText;
		this.rating = rating;
		this.book = book;
	}
}
