package travelportalhibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTravelUtil {
	private static final SessionFactory sessionFactory;

	static {
		try {

			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			sessionFactory = configuration.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSessionFactorySession() {
		return sessionFactory.openSession();
	}
}
//File f = new File("D:\\fax\\hibernate.cfg.xml");
//SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();