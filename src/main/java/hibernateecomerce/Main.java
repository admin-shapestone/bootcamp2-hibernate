package hibernateecomerce;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		
        System.out.println("press 1 for create tables");
		System.out.println("press 2 for insert data");
		System.out.println("press 3 for Retrive data from database");
		System.out.println("press 4 for delete data from data base");
		System.out.println("press 5 for update data from data base");
		System.out.println("press 6 for all customes in assending order");
		System.out.println("press 7 for current week purchases");

		boolean continueLoop = true;
		do {

			Scanner scanner = new Scanner(System.in);

			ObjectMapper mapper = new ObjectMapper();

			ArrayList<CustomerVo> customers = mapper.readValue(new File("hibernatecustomer.json"),
					new TypeReference<ArrayList<CustomerVo>>() {
					});

			ArrayList<PurchaseVo> purchases = mapper.readValue(new File("hibernatepurchase.json"),
					new TypeReference<ArrayList<PurchaseVo>>() {
					});

			int option = scanner.nextInt();
			
			if (option == 1)
			 Connection.createTable();

			if (option == 2) {
				Connection.insertData(customers, purchases);
			}

			if (option == 3) {

				System.out.println("Enter customerId");
				int customerId = scanner.nextInt();

				Connection.retrive(customerId);
			}

			else if (option == 4) {
				System.out.println("Enter customerId");
				int customerId = scanner.nextInt();
				Connection.delete(customerId);
			}

			else if (option == 5) {
				System.out.println("Enter customerId");
				int customerId = scanner.nextInt();
				System.out.println("Enter name");
				String newName = scanner.next();
				Connection.update(customerId, newName);

            } 
			
			else if (option == 6) {
				Connection.allCustomersInAsscendingOrder();

			}

			else if (option == 7) {
				System.out.println("Enter your customerID");
				int customerId = scanner.nextInt();
				Connection.totalPurchases(customerId);
			}
			
			else if (option == 8) {
				Connection.currentWeekPurchases();
			}

			System.out.println("Do you want to continue? Press 'yes' to continue or any other key to exit.");
			String check = scanner.next();
			continueLoop = check.equalsIgnoreCase("yes");

		} while (continueLoop);

	}
}