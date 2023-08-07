package shapestone.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import hibernateecomerce.CustomerLaptop;
import hibernateecomerce.PurchaseLaptop;

public class HibernateLaptopUtill {
	
	public static Session getSession() {
		Configuration configurationWithXml = new Configuration().configure("hibernate.cfg.xml");
		configurationWithXml.addAnnotatedClass(CustomerLaptop.class);
		configurationWithXml.addAnnotatedClass(PurchaseLaptop.class);

		

		SessionFactory sessionFactory = configurationWithXml.buildSessionFactory();

		org.hibernate.Session session = sessionFactory.openSession();

		//Transaction  transaction = session.beginTransaction();

	
		return session;

	}


}
