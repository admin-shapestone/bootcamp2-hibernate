package travelportalhibernate;

import java.sql.Date;

public class BookingsVo {

	private int bookingId;
	private int passengerId;
	private String originFrom;
	private String destinationTo;
	private long distance;
	private String modeOfTransport;
	private float pricePerKm;
	private Date dateOfJourney;

	public BookingsVo() {
		super();
		
	}

	@Override
	public String toString() {
		return "BookingsVo [bookingId=" + bookingId + ", passengerId=" + passengerId + ", originFrom=" + originFrom
				+ ", destinationTo=" + destinationTo + ", distance=" + distance + ", modeOfTransport=" + modeOfTransport
				+ ", pricePerKm=" + pricePerKm + ", dateOfJourney=" + dateOfJourney + "]";
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
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

	public BookingsVo(int bookingId, int passengerId, String originFrom, String destinationTo, long distance,
			String modeOfTransport, float pricePerKm, Date dateOfJourney) {
		super();
		this.bookingId = bookingId;
		this.passengerId = passengerId;
		this.originFrom = originFrom;
		this.destinationTo = destinationTo;
		this.distance = distance;
		this.modeOfTransport = modeOfTransport;
		this.pricePerKm = pricePerKm;
		this.dateOfJourney = dateOfJourney;
	}
}