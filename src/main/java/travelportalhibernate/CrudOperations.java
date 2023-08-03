package travelportalhibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

public class CrudOperations {
    // Insert data into the database
	 public static void insertData(ArrayList<PassengersVo> passengersList, ArrayList<BookingsVo> bookingsList) {
	        try (Session session = HibernateTravelUtil.getSessionFactorySession()) {
	            org.hibernate.Transaction transaction = session.beginTransaction();

	            for (PassengersVo passengerVo : passengersList) {
	                PassengersTravel passenger = new PassengersTravel();
	                passenger.setPassengerId(passengerVo.getPassengerId());
	                passenger.setName(passengerVo.getName());
	                passenger.setAge(passengerVo.getAge());
	                passenger.setGender(passengerVo.getGender());
	                passenger.setAddress(passengerVo.getAddress());
	                session.persist(passenger);
	            }

	            for (BookingsVo bookingsVo : bookingsList) {
	                BookingsTravel booking = new BookingsTravel();
	                booking.setBookingId(bookingsVo.getBookingId());
	                booking.setPassenger(findPassengerById(session, bookingsVo.getPassengerId()));
	                booking.setOriginFrom(bookingsVo.getOriginFrom());
	                booking.setDestinationTo(bookingsVo.getDestinationTo());
	                booking.setDistance(bookingsVo.getDistance());
	                booking.setDateOfJourney(bookingsVo.getDateOfJourney());
	                session.persist(booking);
	            }
	            transaction.commit();
	        }
	    }

	    // Retrieve a passenger by ID
	    public static void retrieve(ArrayList<PassengersVo> passengersList, int passengerId) {
	        try (Session session = HibernateTravelUtil.getSessionFactorySession()) {
	            PassengersTravel passenger = session.get(PassengersTravel.class, passengerId);
	            if (passenger != null) {
	                System.out.printf("| %-15s | %-15s |%-15s|%-15s |%-15s| %n", "Passenger Id", "Name", "Age", "Gender",
	                        "Address");
	                System.out.println("------------------------------------------------------------");
	                System.out.printf("| %-15s | %-15s |%-15s|%-15s |%-15s| %n", passenger.getPassengerId(),
	                        passenger.getName(), passenger.getAge(), passenger.getGender(), passenger.getAddress());
	                System.out.println("------------------------------------------------------------");
	            } else {
	                System.out.println("Passenger with ID " + passengerId + " not found.");
	            }
	        }
	    }
	    private static PassengersTravel findPassengerById(Session session, int passengerId) {
	        return session.get(PassengersTravel.class, passengerId);
	    }
	
    // Delete a passenger by ID
    public static void delete(int passengerId) {
        try (Session session = HibernateTravelUtil.getSessionFactorySession()) {
            PassengersTravel passenger = session.get(PassengersTravel.class, passengerId);
            if (passenger != null) {
                session.remove(passenger);
                System.out.println("Passenger with ID " + passengerId + " has been deleted.");
            } else {
                System.out.println("Passenger with ID " + passengerId + " not found.");
            }
        }
    }

    // Update passenger name by ID
    public static void update(int passengerId, String newName) {
		try (Session session = HibernateTravelUtil.getSessionFactorySession()) {
            PassengersTravel passenger = session.get(PassengersTravel.class, passengerId);
            if (passenger != null) {
                passenger.setName(newName);
                System.out.println("Passenger name updated successfully.");
            } else {
                System.out.println("Passenger with ID " + passengerId + " not found.");
            }
        }
    }

    public static void allPassengersInAscendingOrder() {
        try (Session session = HibernateTravelUtil.getSessionFactorySession()) {
            Query<PassengersTravel> query = session.createQuery("FROM PassengersTravel ORDER BY name",
                    PassengersTravel.class);
            List<PassengersTravel> passengersList = query.getResultList();
            System.out.printf("| %-15s | %-15s |%-15s|%-15s |%-15s| %n", "Passenger Id", "Name", "Age", "Gender",
                    "Address");
            System.out.println("------------------------------------------------------------");
            for (PassengersTravel passenger : passengersList) {
                System.out.printf("| %-15s | %-15s |%-15s|%-15s |%-15s| %n", passenger.getPassengerId(),
                        passenger.getName(), passenger.getAge(), passenger.getGender(), passenger.getAddress());
            }
        }
    }


	// Calculate the total cost for a passenger
	public static void getTotalCost(int passengerID) {
		try (Session session = HibernateTravelUtil.getSessionFactorySession()) {
			Query<Double> query = session.createQuery(
				    "SELECT SUM(bt.pricePerKm * bt.distance) FROM BookingsTravel bt WHERE bt.passenger.passengerId = :passengerId",
				    Double.class);
				query.setParameter("passengerId", passengerID);

			query.setParameter("passengerId", passengerID);
			Double totalCost = query.getSingleResult();
			System.out.println("Total cost for passenger with ID " + passengerID + ": " + totalCost);
		}
	}

	// Fetch bookings for the current week
	public static void currentWeekBookings() {
		try (Session session = HibernateTravelUtil.getSessionFactorySession()) {
			Query<BookingsTravel> query = session.createQuery(
					"FROM BookingsTravel WHERE WEEK(dateOfJourney) = WEEK(CURRENT_DATE)", BookingsTravel.class);
			List<BookingsTravel> bookingsList = query.getResultList();
			System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n", "Booking Id", "Passenger Id", "Origin From",
					"Destination To", "Distance", "Date of Journey");
			for (BookingsTravel booking : bookingsList) {
				System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n", booking.getBookingId(),
						booking.getPassenger(), booking.getOriginFrom(), booking.getDestinationTo(),
						booking.getDistance(), booking.getDateOfJourney());
			}
		}
	}

}
