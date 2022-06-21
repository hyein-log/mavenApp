package com.kosta.mavenApp.section4.aop2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest2 {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aop2.xml");
		Calculator cal = ctx.getBean("proxyCal", Calculator.class);
		cal.divide(100, 200);
		//cal.add(10);
	}

}
