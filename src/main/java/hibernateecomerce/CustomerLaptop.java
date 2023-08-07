package hibernateecomerce;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CustomerTable")
public class CustomerLaptop {
	@Id

	@Column(name = "customerId")
	private int customerId;
	@Column(name = "name")
	private String name;
	@Column(name = "age")
	private int age;
	@Column(name = "gender")
	private String gender;
	@Column(name = "address")
	private String address;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "CustomerId", referencedColumnName = "customerId")
	private List<PurchaseLaptop>purchaseLaptop;
	
	

	public List<PurchaseLaptop> getPurchaseLaptop() {
		return purchaseLaptop;
	}

	public void setPurchaseLaptop(List<PurchaseLaptop> purchaseLaptop) {
		this.purchaseLaptop = purchaseLaptop;
		
	}
	

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	@Override
	public String toString() {
		return "CustomerLaptop [customerId=" + customerId + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", address=" + address + ", purchaseLaptop=" + purchaseLaptop + "]";
	}

	public CustomerLaptop(int customerId, String name, int age, String gender, String address,
			List<PurchaseLaptop> purchaseLaptop) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.purchaseLaptop = purchaseLaptop;
	}

	public CustomerLaptop() {
		super();
		// TODO Auto-generated constructor stub
	}

}
