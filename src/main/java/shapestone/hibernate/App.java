package shapestone.hibernate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.beanvalidation.HibernateTraversableResolver;
import org.hibernate.query.Query;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import shapestone.hibernate.VO.CertificateVO;
import shapestone.hibernate.VO.StudentVO;
import shapestone.hibernate.converter.Transformer;
import shapestone.hibernate.model.Certificate;
import shapestone.hibernate.model.Student;
import shapestone.hibernate.util.HibernateCRUDUtil;
import shapestone.hibernate.util.HibernateUtil;

/**
 * Driver program for Hibernate CRUD operations using student and certification entities
 *
 */
public class App {
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		System.out.println("Welcom to the Hibernate programing...");
		Session session = HibernateUtil.dupmpingDataIntoTable();
		Scanner scanner = new Scanner(System.in);
		boolean wannaContinue = true;
		while (wannaContinue) {

			System.out.println("Select any option from below.");
			System.out.println("1).	Retrive");
			System.out.println("2).	Create");
			System.out.println("3).	Delete");
			System.out.println("4).	Update");
			int option = scanner.nextInt();
			if (option == 1) {
				HibernateCRUDUtil.retriveAndDisplay(scanner, session);

			} else if (option == 2) {
				HibernateCRUDUtil.addStudentInfo(scanner, session);
			} else if (option == 3) {
				HibernateCRUDUtil.deleteStudent(scanner, session);
			} else if (option == 4) {
				HibernateCRUDUtil.updateStudentInfo(scanner, session);
			} else {
				System.out.println("Option selection is wrong, please contact adminstrator/Nithis");
			}

			System.out.println("want to continue ....");
			String next = scanner.next();
			if (next.equalsIgnoreCase("yes") || next.equalsIgnoreCase("true")) {
				wannaContinue = true;
			} else {
				wannaContinue = false;
			}
		
		}

		System.out.println("Done with execution..");
	}

}
