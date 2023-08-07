package hibernateecomerce;

public class CustomerVo {

	private int customerId;
	private String name;
	private int age;
	private String gender;
	private String address;

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
		return "CustomerVo [customerId=" + customerId + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", address=" + address + "]";
	}

	public CustomerVo(int customerId, String name, int age, String gender, String address) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
	}

	public CustomerVo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
