package com.kosta.mavenApp.section2;

//필수는 JavaBeans 조건: 변수는 접근지정자가 private, default 생성자 필수, getter/setter 필수
//VO(Value Object)
//DTO(Data Transfer Object)
//argument가 몇개 없을 경우 argument가 있는 생성자가 만드는 것이 나을 때도 있음
//하지만 argument가 있는 생성자를 만들 경우 default 생성자가 없어지기에 주의해야함
public class Car {
	private String model;
	private int price;
	private String color;
	
	public Car() {
		System.out.println("default생성자를 이용해서 car를 만듦");
		
	}
	public Car(String model, int price, String color) {
		super();
		this.model = model;
		this.price = price;
		this.color = color;
		System.out.println("argument 3개가 있는 생성자로 생성된다.");
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
		System.out.println("setmodel : "+model);
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
		System.out.println("price : "+price);
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
		System.out.println("color : "+color);
	}
	@Override
	public String toString() {
		return "Car [model=" + model + ", price=" + price + ", color=" + color + "]";
	}
	
	
}
