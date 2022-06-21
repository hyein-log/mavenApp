package com.kosta.mavenApp.section2;

import org.springframework.stereotype.Component;

public class Book {
	private String title;
	private int price;
	private String kind;
	public Book() {
		super();
	}
	public Book(String title, int price, String kind) {
		super();
		this.title = title;
		this.price = price;
		this.kind = kind;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + ", kind=" + kind + "]";
	}
	
}
