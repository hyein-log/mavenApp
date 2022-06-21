package com.kosta.mavenApp.section1;

public class LGTV implements TV {
	
	
	
	public void TurnOn() {
		System.out.println(this.getClass().getSimpleName()+"킨다");
	}
	public void TurnOff() {
		System.out.println(this.getClass().getSimpleName()+"끈다");
	}
	@Override
	public void powerOn() {
		System.out.println(this.getClass().getSimpleName()+"킨다");
	}
	@Override
	public void powerOff() {
		System.out.println(this.getClass().getSimpleName()+"끈다");		
	}
}
