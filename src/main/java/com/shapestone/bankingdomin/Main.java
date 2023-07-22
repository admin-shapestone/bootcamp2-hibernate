package com.shapestone.bankingdomin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.Session;
import com.shapestone.bankingdomin.vo.AccountVO;
import com.shapestone.bankingdomin.vo.PaymentVO;

import shapestone.hibernate.util.HibernateBankUtill;

public class Main {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {

		boolean continueLoop = true;
		do {
			System.out.println("1 for Retrive data from database");
			System.out.println("2 for delete data from data base");
			System.out.println("3 for update data from data base");
			System.out.println("4 for get All account details in asscending order");
			System.out.println("5 for Check your AccountBalance");
			System.out.println("6 for current weeek Account openings");

			Scanner sc = new Scanner(System.in);

			ObjectMapper mapper = new ObjectMapper();

			ArrayList<AccountVO> accountList = mapper.readValue(new File("Accounts.json"),
					new TypeReference<ArrayList<AccountVO>>() {
					});

			ArrayList<PaymentVO> paymentList = mapper.readValue(new File("Payments.json"),
					new TypeReference<ArrayList<PaymentVO>>() {
					});

			// CrudOperations.insertData(accountList, paymentList);

			int option = sc.nextInt();
			if (option == 1) {

				System.out.println("Enter  account id");
				long accountId = sc.nextLong();

				CrudOperations.retrive(accountId);
			}

			else if (option == 2) {
				System.out.println("Enter  account id");
				long accountId = sc.nextLong();
				CrudOperations.delete(accountId);
			}

			else if (option == 3) {
				System.out.println("Enter  account id");
				long accountId = sc.nextLong();
				System.out.println("Enter name");
				String newName = sc.next();
				CrudOperations.update(accountId, newName);

			}

			else if (option == 4) {
				CrudOperations.allAccountsInAsscendingOrder();
			}

			else if (option == 5) {
				System.out.println("Enter your Account ID");
				long accountNUM = sc.nextLong();
				CrudOperations.getTotalBalance(accountNUM);

			}

			else if (option == 6) {
				CrudOperations.currentWeekAccounts();
			}

			System.out.println("Do you want to continue? Press 'yes' to continue or any other key to exit.");
			String check = sc.next();
			continueLoop = check.equalsIgnoreCase("yes");

		} while (continueLoop);

	}

}
