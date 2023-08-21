package shapestone.hibernate.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts_Table")
public class Account {
	@Id
	// @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "accountId")
	private long accountId;
	@Column(name = "name")
	private String name;
	@Column(name = "age")
	private int age;
	@Column(name = "gender")
	private String gender;
	@Column(name = "dateOfJoining")
	private String dateOfJoining;
	@Column(name = "openingBalance")
	private double openingBalance;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Account_Id", referencedColumnName = "accountId")
	private List<Payment> payments;

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
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
		return "Account [accountId=" + accountId + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", dateOfJoining=" + dateOfJoining + ", openingBalance=" + openingBalance + "]";
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(long accountId, String name, int age, String gender, String dateOfJoining, double openingBalance,
			List<Payment> payments) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.dateOfJoining = dateOfJoining;
		this.openingBalance = openingBalance;
		this.payments = payments;
	}

	
}
