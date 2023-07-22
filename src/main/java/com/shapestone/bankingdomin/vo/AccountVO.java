package com.shapestone.bankingdomin.vo;

public class AccountVO {
	private long accountId;
	private String name;
	private int age;
	private String gender;
	private String dateOfJoining;
	private double openingBalance;

	
	public AccountVO() {
		super();
	}

	public AccountVO(long accountId, String name, int age, String gender, String dateOfJoining,
			double openingBalance) {
		this.accountId = accountId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.dateOfJoining = dateOfJoining;
		this.openingBalance = openingBalance;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
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

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	@Override
	public String toString() {
		return "AccountVO [accountId=" + accountId + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", dateOfJoining=" + dateOfJoining + ", openingBalance=" + openingBalance + "]";
	}
	
	
}
