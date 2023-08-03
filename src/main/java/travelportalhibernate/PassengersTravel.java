package travelportalhibernate;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "passengers")
public class PassengersTravel {
    @Id
    @Column(name = "passengerId")
    private int passengerId;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    // Define relationships with BookingsTravel
    @OneToMany(mappedBy = "passengerId")
    private List<BookingsTravel> bookingsList;

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

	public List<BookingsTravel> getBookingsList() {
		return bookingsList;
	}

	public void setBookingsList(List<BookingsTravel> bookingsList) {
		this.bookingsList = bookingsList;
	}

	@Override
	public String toString() {
		return "PassengersTravel [passengerId=" + passengerId + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", address=" + address + ", bookingsList=" + bookingsList + "]";
	}

}
