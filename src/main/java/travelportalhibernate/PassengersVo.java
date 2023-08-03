package travelportalhibernate;

import java.sql.Date;

public class PassengersVo {

	private int passengerId;
	private String name;
	private int age;
	private String gender;
	private String address;

	public PassengersVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PassengersVo [passengerId=" + passengerId + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", address=" + address + "]";
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PassengersVo(int passengerId, String name, int age, String gender, String address) {
		super();
		this.passengerId = passengerId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
	}
}