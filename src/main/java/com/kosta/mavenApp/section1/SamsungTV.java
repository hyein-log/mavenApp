package com.kosta.mavenApp.section1;

public class SamsungTV implements TV{
	
	String model;
	int price;
	
	public SamsungTV(String model, int price) {
		super();
		this.model = model;
		this.price = price;
		System.out.println("SamsungTV 생성됨");
	}
	
	public void powerOn() {
		System.out.println(this.getClass().getSimpleName()+"킨다");
	}
	public void powerOff() {
		System.out.println(this.getClass().getSimpleName()+"끈다");
	}

	@Override
	public String toString() {
		return "SamsungTV [model=" + model + ", price=" + price + "]";
	}
	
}
