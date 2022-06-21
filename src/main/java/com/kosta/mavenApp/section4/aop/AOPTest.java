package com.kosta.mavenApp.section4.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aop1.xml");
		Calculator cal = ctx.getBean("proxyCal", Calculator.class);
		cal.add();
		System.out.println("----------------------");
		cal.divide(10, 5);
	}

}
