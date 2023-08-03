//package travelportalhibernate;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class Main {
//    public static void main(String[] args) {
//        boolean continueLoop = true;
//        try (Scanner sc = new Scanner(System.in)) {
//            ObjectMapper mapper = new ObjectMapper();
//            ArrayList<BookingsVo> bookingsList = mapper.readValue(new File("bookingshibernate.json"),
//                    new com.fasterxml.jackson.core.type.TypeReference<ArrayList<BookingsVo>>() {
//                    });
//            ArrayList<PassengersVo> passengersList = mapper.readValue(new File("passengershibernate.json"),
//                    new com.fasterxml.jackson.core.type.TypeReference<ArrayList<PassengersVo>>() {
//                    });
//
//            do {
//                System.out.println("1 for Retrieve data from database");
//                System.out.println("2 for Delete data from database");
//                System.out.println("3 for Update data from database");
//                System.out.println("4 for Passengers in Ascending order");
//                System.out.println("5 for to get TotalCost");
//                System.out.println("6 for Current week bookings");
//
//                int option = sc.nextInt();
//
//                if (option == 1) {
////                    System.out.println("Enter Passenger ID:");
////                    int passengerId = sc.nextInt();
//                    CrudOperations.retrieve(passengersList);
//                } else if (option == 2) {
//                    System.out.println("Enter Passenger ID:");
//                    int passengerId = sc.nextInt();
//                    CrudOperations.delete(passengerId);
//                } else if (option == 3) {
//                    System.out.println("Enter Passenger ID:");
//                    int passengerId = sc.nextInt();
//                    System.out.println("Enter new name:");
//                    String newName = sc.next();
//                    CrudOperations.update(passengerId, newName);
//                } else if (option == 4) {
//                    CrudOperations.allPassengersInAscendingOrder();
//                } else if (option == 5) {
//                    System.out.println("Enter Passenger ID:");
//                    int passengerId = sc.nextInt();
//                    CrudOperations.getTotalCost(passengerId);
//                } else if (option == 6) {
//                    CrudOperations.currentWeekBookings();
//                } else {
//                    System.out.println("Invalid option. Please try again.");
//                }
//
//                System.out.println("Do you want to continue? Press 'yes' to continue or any other key to exit.");
//                String check = sc.next();
//                continueLoop = check.equalsIgnoreCase("yes");
//            } while (continueLoop);
//        } catch (JsonParseException | JsonMappingException e) {
//            System.err.println("Error parsing JSON files: " + e.getMessage());
//        } catch (IOException e) {
//            System.err.println("IO Exception occurred: " + e.getMessage());
//        }
//    }
//}
package travelportalhibernate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	public static void main(String[] args) {
		boolean continueLoop = true;
		try (Scanner sc = new Scanner(System.in)) {
			ObjectMapper mapper = new ObjectMapper();
			ArrayList<BookingsVo> bookingsList = mapper.readValue(new File(
					"C:\\Users\\nitis\\git\\bootcamp2-hibernate\\src\\main\\java\\travelportalhibernate\\bookingshibernate.json"),
					new com.fasterxml.jackson.core.type.TypeReference<ArrayList<BookingsVo>>() {
					});
			ArrayList<PassengersVo> passengersList = mapper.readValue(new File(
					"C:\\Users\\nitis\\git\\bootcamp2-hibernate\\src\\main\\java\\travelportalhibernate\\passengershibernate.json"),
					new com.fasterxml.jackson.core.type.TypeReference<ArrayList<PassengersVo>>() {
					});

			do {
				System.out.println("0 for insert data from database");
				System.out.println("1 for Retrieve data from database");
				System.out.println("2 for Delete data from database");
				System.out.println("3 for Update data from database");
				System.out.println("4 for Passengers in Ascending order");
				System.out.println("5 for to get TotalCost");
				System.out.println("6 for Current week bookings");

				int option = sc.nextInt();
				if (option == 0) {
					CrudOperations.insertData(passengersList, bookingsList);
				} else if (option == 1) {
					System.out.println("Enter Passenger ID:");
					int passengerId = sc.nextInt();
					CrudOperations.retrieve(passengersList, passengerId);
				} else if (option == 2) {
					System.out.println("Enter Passenger ID:");
					int passengerId = sc.nextInt();
					CrudOperations.delete(passengerId);
				} else if (option == 3) {
					System.out.println("Enter Passenger ID:");
					int passengerId = sc.nextInt();
					System.out.println("Enter new name:");
					String newName = sc.next();
					CrudOperations.update(passengerId, newName);
				} else if (option == 4) {
					CrudOperations.allPassengersInAscendingOrder();
				} else if (option == 5) {
					System.out.println("Enter Passenger ID:");
					int passengerId = sc.nextInt();
					CrudOperations.getTotalCost(passengerId);
				} else if (option == 6) {
					CrudOperations.currentWeekBookings();
				} else {
					System.out.println("Invalid option. Please try again.");
				}

				System.out.println("Do you want to continue? Press 'yes' to continue or any other key to exit.");
				String check = sc.next();
				continueLoop = check.equalsIgnoreCase("yes");
			} while (continueLoop);
		} catch (JsonParseException | JsonMappingException e) {
			System.err.println("Error parsing JSON files: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IO Exception occurred: " + e.getMessage());
		}
	}
}
