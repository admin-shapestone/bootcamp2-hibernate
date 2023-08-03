package travelportalhibernate;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class BookingsTravel {
    @Id
    @Column(name = "bookingId")
    private int bookingId;
    
    @ManyToOne
    @JoinColumn(name = "passengerId")
    private PassengersTravel passengerId;

    @Column(name = "originFrom")
    private String originFrom;

    @Column(name = "destinationTo")
    private String destinationTo;

    @Column(name = "distance")
    private long distance;

    @Column(name = "modeOfTransport")
    private String modeOfTransport;

    @Column(name = "pricePerKm")
    private float pricePerKm;

    @Column(name = "dateOfJourney")
    private Date dateOfJourney;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public PassengersTravel getPassenger() {
		return passengerId;
	}

	public void setPassenger(PassengersTravel passenger) {
		this.passengerId = passenger;
	}

	public String getOriginFrom() {
		return originFrom;
	}

	public void setOriginFrom(String originFrom) {
		this.originFrom = originFrom;
	}

	public String getDestinationTo() {
		return destinationTo;
	}

	public void setDestinationTo(String destinationTo) {
		this.destinationTo = destinationTo;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public String getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public float getPricePerKm() {
		return pricePerKm;
	}

	public void setPricePerKm(float pricePerKm) {
		this.pricePerKm = pricePerKm;
	}

	public Date getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(Date dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}

	@Override
	public String toString() {
		return "BookingsTravel [bookingId=" + bookingId + ", passengerId=" + passengerId + ", originFrom=" + originFrom
				+ ", destinationTo=" + destinationTo + ", distance=" + distance + ", modeOfTransport=" + modeOfTransport
				+ ", pricePerKm=" + pricePerKm + ", dateOfJourney=" + dateOfJourney + "]";
	}
}