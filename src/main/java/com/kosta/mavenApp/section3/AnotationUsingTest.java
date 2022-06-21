package com.kosta.mavenApp.section3;

import org.kosta.mavenApp.section4.Book;
import org.kosta.mavenApp.section4.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnotationUsingTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("di4.xml");
		Car car = ctx.getBean("car",Car.class);
		License li = ctx.getBean("license",License.class);
		Book book = ctx.getBean("book",Book.class);
		Person p = ctx.getBean("person",Person.class);
		System.out.println(car);
		System.out.println(li);
		System.out.println(book);
		System.out.println(p);
	}

}
