package org.kosta.mavenApp.section4;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kosta.mavenApp.section3.Car;
import com.kosta.mavenApp.section3.License;

@Component
public class Person {
	
	@Value("김혜인")
	private String name;
	@Value("010-1234-1234")
	private	String phone;
	
	@Autowired //type이 같으면 자동 injection Car(타입) car(이름)
	private	Car car;
	
	private List<String> major;
	private List<License> 자격증들;
	private Map<String, Book> bookMap;
	private Set<String> friends;
	
	private Properties myProfile;
	
	public Person() {
		System.out.println("default생성자를 이용해서 Person를 만듦");
	}
	
	public Person(String name, String phone, Car car) {
		super();
		this.name = name;
		this.phone = phone;
		this.car = car;
		System.out.println("argument 3개가 있는 생성자로 Person 생성된다.");
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		System.out.println("setName : "+name);
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
		System.out.println("setPhone : "+phone);
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
		System.out.println("setCar : "+ car);
	}

	public List<String> getMajor() {
		return major;
	}

	public void setMajor(List<String> major) {
		this.major = major;
	}

	public List<License> get자격증들() {
		return 자격증들;
	}

	public void set자격증들(List<License> 자격증들) {
		this.자격증들 = 자격증들;
	}

	public Map<String, Book> getBookMap() {
		return bookMap;
	}

	public void setBookMap(Map<String, Book> bookMap) {
		this.bookMap = bookMap;
	}

	public Set<String> getFriends() {
		return friends;
	}

	public void setFriends(Set<String> friends) {
		this.friends = friends;
	}

	public Properties getMyProfile() {
		return myProfile;
	}

	public void setMyProfile(Properties myProfile) {
		this.myProfile = myProfile;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", phone=" + phone + ", car=" + car + ", major=" + major + ", 자격증들=" + 자격증들
				+ ", bookMap=" + bookMap + ", friends=" + friends + ", myProfile=" + myProfile + "]";
	}

	
	
	
}
