package com.kosta.mavenApp.section3;

import org.springframework.stereotype.Component;

@Component
public class License {
	private String title;
	private int year;
	
	public License() {
		super();
	}

	public License(String title, int year) {
		super();
		this.title = title;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "License [title=" + title + ", year=" + year + "]";
	}
	
}
