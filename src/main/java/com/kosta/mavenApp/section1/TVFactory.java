package com.kosta.mavenApp.section1;

public class TVFactory {
	
	public static TV makeTV(String company ) {
		TV tv = null;
		if(company.equals("Samsung")) {
			tv = new SamsungTV("abc모델", 1000);
		}else if(company.equals("LG")) {
			tv = new LGTV();
		}else {
			
		}
		return tv;
	}
}
