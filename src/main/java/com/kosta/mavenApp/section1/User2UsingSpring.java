package com.kosta.mavenApp.section1;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class User2UsingSpring {
	public static void main(String[] args) {
		Resource resource = new ClassPathResource("di1.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		TV tv = (TV)factory.getBean("samsungtv"); //무조건 object이기에 형변환 필수 // bean factory는 getbean(사용)해야지 생성됨
//		System.out.println(tv);
//		tv.powerOn();
//		tv.powerOff();
		System.out.println("----------------");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("di1.xml"); //이렇게만 써도 객체 생성됨 //설정파일로드시 생성됨
//		TV tv = (TV)context.getBean("samsungtv");
//		System.out.println(tv);
//		tv.powerOn();
//		tv.powerOff();
	}
}
