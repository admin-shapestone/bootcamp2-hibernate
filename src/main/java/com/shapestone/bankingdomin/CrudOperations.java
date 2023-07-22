package com.shapestone.bankingdomin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.shapestone.bankingdomin.vo.AccountVO;
import com.shapestone.bankingdomin.vo.PaymentVO;

import shapestone.hibernate.model.Account;
import shapestone.hibernate.model.Payment;
import shapestone.hibernate.util.HibernateBankUtill;

public class CrudOperations {

	public static void retrive(long accountId) {
		Session session = HibernateBankUtill.getSession();
		Account account = session.load(Account.class, accountId);
		if (account != null) {
			System.out.printf("| %-15s | %-15s |%-15s|%-15s |%-15s| %n", "Account Id", "Name", "Age", "Date Of Joining",
					"Opening balance");
			System.out.println(

					"------------------------------------------------------------------------------------------");

			System.out.printf("| %-15s | %-15s |%-15s|%-15s |%-15s| %n", account.getAccountId(), account.getName(),
					account.getAge(), account.getDateOfJoining(), account.getOpeningBalance());
			System.out.println(

					"-------------------------------------------------------------------------------------------");

		}

		else {
			System.out.println("Enter a valid account long Account id");
		}

	}

	public static void delete(long accountId) {
		Session session = HibernateBankUtill.getSession();
		Account account = session.load(Account.class, accountId);
		Transaction transaction = session.getTransaction();
		if (account != null) {
			session.remove(account);
		} else {
			System.out.println("Enter a valid account Id");
		}
		transaction.commit();
		session.close();

	}

	public static void update(long accountId, String newName) {
		Session session = HibernateBankUtill.getSession();

		Account account = session.load(Account.class, accountId);
		Transaction transaction = session.getTransaction();
		if (account != null) {
			account.setName(newName);
			session.update(account);
			System.out.println("account updated sucessfully");
		} else {
			System.out.println("Account not found");
		}
		transaction.commit();
		session.close();

	}

	public static void allAccountsInAsscendingOrder() {
		Session session = HibernateBankUtill.getSession();

		String query = "select * from accounts_table order by name;";
		NativeQuery<Account> createSQLQuery = session.createSQLQuery(query);
		createSQLQuery.addEntity(Account.class);
		List<Account> list = createSQLQuery.list();

		System.out.printf("| %-15s | %-15s |%-15s|%-15s |%-15s| %n", "Account Id", "Name", "Age", "Date Of Joining",
				"Opening balance");
		for (Account account : list) {
			System.out.println("------------------------------------------------------------------------------------");
			System.out.printf("| %-15s | %-15s |%-15s|%-15s |%-15s| %n", account.getAccountId(), account.getName(),
					account.getAge(), account.getGender(), account.getDateOfJoining(), account.getOpeningBalance());
		}

	}

	public static void insertData(ArrayList<AccountVO> accountList, ArrayList<PaymentVO> paymentList) {
		Session session = HibernateBankUtill.getSession();
		Transaction beginTransaction = session.beginTransaction();
		accountList.forEach(accountVO -> {
			ArrayList<Payment> payments = getPaymentListBasedOnAccountId(accountVO.getAccountId(), paymentList);

			Account account = new Account(accountVO.getAccountId(), accountVO.getName(), accountVO.getAge(),
					accountVO.getGender(), accountVO.getDateOfJoining(), accountVO.getOpeningBalance(), payments);
			account.setPayments(payments);
			session.persist(account);

		});
		beginTransaction.commit();

	}

	private static ArrayList<Payment> getPaymentListBasedOnAccountId(long accountId, ArrayList<PaymentVO> paymentList) {
		ArrayList<Payment> payments = new ArrayList<>();
		paymentList.stream().filter(paymentVO -> paymentVO.getAccountId() == accountId).collect(Collectors.toList())
				.forEach(paymentVO -> {
					Payment payment = new Payment(paymentVO.getPaymentId(), paymentVO.getPurposeOfPayment(),
							paymentVO.getAmountPaid(), paymentVO.getAmountRecived(), paymentVO.getDateOfPayment());
					payments.add(payment);
				});
		return payments;
	}

	public static void getTotalBalance(long accountNUM) {
		Session session = HibernateBankUtill.getSession();

		String debitsQuery = "select sum(amountPaid) as totalDebits from payments_table where Account_Id = ?";
		String balanceQuery = "select openingbalance  from accounts_table where accountId = ?";
		NativeQuery createSQLQuery = session.createSQLQuery(debitsQuery);
		createSQLQuery.setParameter(1, accountNUM);
		NativeQuery balanceSQLQuery = session.createSQLQuery(balanceQuery);
		balanceSQLQuery.setParameter(1, accountNUM);
		double totalDebits = (double) createSQLQuery.uniqueResult();

		double avaiableBalance = (double) balanceSQLQuery.uniqueResult();

		System.out.println("Total Avaiable balance In your Account  ::" + (avaiableBalance - totalDebits));

	}

	public static void currentWeekAccounts() {
		Session session = HibernateBankUtill.getSession();

		String hqlQuery = "FROM Account WHERE WEEK(dateOfJoining) = WEEK(CURDATE())";

		org.hibernate.query.Query<Account> hibernateQuery = session.createQuery(hqlQuery, Account.class);
		List<Account> accountList = hibernateQuery.list();

		System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n", "Account Id", "Name", "Age", "Gender",
				"Date of Joining", "Opening Balance");
		for (Account account : accountList) {
			System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n", account.getAccountId(), account.getName(),
					account.getAge(), account.getGender(), account.getDateOfJoining(), account.getOpeningBalance());
		}
	}

}
