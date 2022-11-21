package com.company.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "book_id")
	private Long bookId;

	@Column(name = "book_name")
	private String bookName;

	@Column(name = "page_count")
	private Long pageCount;

	@Column(name = "price")
	private Double price;

	@Column(name = "stock")
	private Long stock;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_author", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "author_id") })
	private List<Author> authors = new ArrayList<>();

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	private List<OrderDetails> orderDetails = new ArrayList<>();

	public Book(String bookName, Long pageCount, Double price, Long stock) {
		super();
		this.bookName = bookName;
		this.pageCount = pageCount;
		this.price = price;
		this.stock = stock;
	}

}
