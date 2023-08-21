package shapestone.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import shapestone.hibernate.model.Account;
import shapestone.hibernate.model.Payment;

public class HibernateBankUtill {

	public static Session getSession() {
		Configuration configurationWithXml = new Configuration().configure("hibernate.cfg.xml");
		configurationWithXml.addAnnotatedClass(Account.class);
		configurationWithXml.addAnnotatedClass(Payment.class);

		SessionFactory sessionFactory = configurationWithXml.buildSessionFactory();

		org.hibernate.Session session = sessionFactory.openSession();

		return session;

	}

}
