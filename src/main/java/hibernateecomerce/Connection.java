package hibernateecomerce;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import jakarta.transaction.Transaction;
import shapestone.hibernate.util.HibernateLaptopUtill;

public class Connection {

	public static void createTable() {

		Session session = HibernateLaptopUtill.getSession();
	}

	public static void retrive(int customerId) {
		Session session = HibernateLaptopUtill.getSession();
		CustomerLaptop customerLaptop = session.load(CustomerLaptop.class, customerId);
		if (customerLaptop != null) {
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.printf("| %-15s | %-15s |%-15s|%-15s |%-15s| %n", "customerId", "Name", "Age", "Gender",
					"Address");
			System.out.println("--------------------------------------------------------------------------------------");

			System.out.printf("| %-15s | %-15s |%-15s|%-15s |%-15s| %n", customerLaptop.getCustomerId(),
					customerLaptop.getName(), customerLaptop.getAge(), customerLaptop.getGender(),
					customerLaptop.getAddress());
			System.out.println("--------------------------------------------------------------------------------------");

		}

		else {
			System.out.println("Enter a valid customerid");
		}

	}

	public static void delete(int customerId) {
		Session session = HibernateLaptopUtill.getSession();
		CustomerLaptop customerLaptop = session.load(CustomerLaptop.class, customerId);
		org.hibernate.Transaction transaction = session.getTransaction();
		if (customerLaptop != null) {
			session.remove(customerLaptop);
		} else {
			System.out.println("Enter a valid customerId");
		}
		transaction.commit();
		session.close();

	}

	public static void update(int customerId, String newName) {
		Session session = HibernateLaptopUtill.getSession();

		CustomerLaptop customerLaptop = session.load(CustomerLaptop.class, customerId);
		org.hibernate.Transaction transaction = session.getTransaction();
		if (customerLaptop != null) {
			customerLaptop.setName(newName);
			session.update(customerLaptop);
			System.out.println("Name updated sucessfully");
		} else {
			System.out.println("customerId not found");
		}
		transaction.commit();
		session.close();

	}

	public static void insertData(ArrayList<CustomerVo> customers, ArrayList<PurchaseVo> purchases) {
		Session session = HibernateLaptopUtill.getSession();
		org.hibernate.Transaction transaction = session.beginTransaction();

		customers.forEach(customersVo -> {
			ArrayList<PurchaseLaptop> purchaseLaptop = getPurchaseListBasedOnCustomerId(customersVo.getCustomerId(),
					purchases);

			CustomerLaptop customerLaptop = new CustomerLaptop(customersVo.getCustomerId(), customersVo.getName(),
					customersVo.getAge(), customersVo.getGender(), customersVo.getAddress(), purchaseLaptop);
			customerLaptop.setPurchaseLaptop(purchaseLaptop);
			session.persist(customerLaptop);

		});
		transaction.commit();

	}

	private static ArrayList<PurchaseLaptop> getPurchaseListBasedOnCustomerId(int customerId,
			ArrayList<PurchaseVo> purchases) {
		ArrayList<PurchaseLaptop> purchaseLaptop1 = new ArrayList<>();
		purchases.stream().filter(purchasesVo -> purchasesVo.getCustomerId() == customerId).collect(Collectors.toList())
				.forEach(purchasesVo -> {
					PurchaseLaptop purchaseLaptop = new PurchaseLaptop(purchasesVo.getPurchaseId(),
							purchasesVo.getItemPurchased(), purchasesVo.getQuantity(), purchasesVo.getPrice(),
							purchasesVo.getDateOfPurchase());
					purchaseLaptop1.add(purchaseLaptop);
				});
		return purchaseLaptop1;
	}

	public static void allCustomersInAsscendingOrder() {
		Session session = HibernateLaptopUtill.getSession();

		String query = "select * from CustomerTable order by name;";
		NativeQuery<CustomerLaptop> nativeQuery = session.createNativeQuery(query);
		nativeQuery.addEntity(CustomerLaptop.class);
		List<CustomerLaptop> list = nativeQuery.list();
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.printf("| %-15s | %-15s |%-15s|%-15s |%-15s| %n", "CustomerId", "Name", "Age", "Gender", "Address");
		for (CustomerLaptop customerLaptop : list) {
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.printf("| %-15s | %-15s |%-15s|%-15s |%-15s| %n", customerLaptop.getCustomerId(),
					customerLaptop.getName(), customerLaptop.getAge(), customerLaptop.getGender(),
					customerLaptop.getAddress());
			
		}
	}

	public static void totalPurchases(int customerId) {
		Session session2 = HibernateLaptopUtill.getSession();

		String query1 = "SELECT SUM(price) AS total_customerpurchase FROM purchasetable  WHERE customerid = ?";
		NativeQuery createSQLQuery = session2.createNativeQuery(query1);
		createSQLQuery.setParameter(1, customerId);

		double totalDebits = (double) createSQLQuery.uniqueResult();

		System.out.println("Total purchases of customer  ::");

	}

	public static void currentWeekPurchases() {
		Session session = HibernateLaptopUtill.getSession();

		String hqlQuery = "FROM Customertable WHERE WEEK(dateOfPurchase) = WEEK(CURDATE())";

		org.hibernate.query.Query<PurchaseVo> hibernateQuery = session.createQuery(hqlQuery, PurchaseVo.class);
		List<PurchaseVo> purchases = hibernateQuery.list();

		System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%n", "CustomerId", "PurchaseId", "Quantity",
				"ItemPurchased", "Price", "DateOfPurchase");
		for (PurchaseVo weeklyPurchases : purchases) {
			System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%n", weeklyPurchases.getCustomerId(),
					weeklyPurchases.getPurchaseId(), weeklyPurchases.getQuantity(), weeklyPurchases.getItemPurchased(),
					weeklyPurchases.getPrice(), weeklyPurchases.getDateOfPurchase());
		}

	}
}
