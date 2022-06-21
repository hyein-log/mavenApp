package com.kosta.mavenApp.section2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanUsingTest2 {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("di3.xml");
		
		Person p1 = ctx.getBean("person", Person.class);
		Person p2 = ctx.getBean("person2", Person.class);
		Person p5 = ctx.getBean("person2", Person.class);
		Person p3 = ctx.getBean("person3", Person.class);
		Person p4 = ctx.getBean("person3", Person.class);
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p3==p4);
		System.out.println(p2==p5);
	}

}
