package shapestone.hibernate.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import shapestone.hibernate.model.Certificate;
import shapestone.hibernate.model.Student;

public class HibernateCRUDUtil {
	public static void retriveAndDisplay(Scanner scanner, Session session) {
		System.out.println("Retrive - Enter student id to fetch details of all/anyone...");
		int studentId = scanner.nextInt();
		StringBuffer sb = new StringBuffer("From Student");
		if (studentId > 0) {
			System.out.println("Fetching details for :: " + studentId);
			sb.append(" where id = " + studentId);

		} else {
			System.out.println("Received invalid studentId, fetching all details");
		}
		Query<Student> createNamedQuery = session.createQuery(sb.toString(), Student.class);
		Student load = session.load(Student.class, 101);
		Student student2 = session.get(Student.class, 101);
//		System.out.println(load.getAge());
		ArrayList<Student> resultList = (ArrayList<Student>) createNamedQuery.getResultList();
		
		HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		JpaCriteriaQuery<Student> createQuery = criteriaBuilder.createQuery(Student.class);
		JpaRoot<Student> from = createQuery.from(Student.class);
		createQuery.select(from)
			.where(criteriaBuilder.gt(from.get("age"), 30))
			.orderBy(criteriaBuilder.asc(from.get("name")));
		
		Query<Student> query = session.createQuery(createQuery);
		List<Student> list = query.getResultList();
		System.out.println(
				"--------------------------------------------------------------------------------------------------");
		System.out.printf("|%-30s|%-30s|%-30s|%n", "Student Number", "Student Name", "Certification Id's");
		
		resultList.stream().forEach(student -> {
			System.out.println(
					"--------------------------------------------------------------------------------------------------");
			List<Integer> certList = student.getCertificates() != null ? student.getCertificates().stream().map(Certificate::getCertificateId).collect(Collectors.toList()) : null;
			System.out.printf("|%-30s|%-30s|%-30s|%n", student.getId(), student.getName(), certList);
		});
		
	}

	public static void addStudentInfo(Scanner scanner, Session session) {
		System.out.println("Enter sudent details for adding a row into DB");
		System.out.println("Enter details in same  format with comma separated 'id', 'name' , 'lastName', 'age'");
		Transaction beginTransaction = session.beginTransaction();
		String[] details = scanner.next().split("\\,");
		Student student = new Student(Integer.valueOf(details[0]), details[1], details[2], Integer.valueOf(details[3]));
		student.setCertificates(new HashSet<>());
		session.persist(student);
		beginTransaction.commit();
		
	}

	public static void deleteStudent(Scanner scanner, Session session) {
		System.out.println("Enter sudent id for delete");
		int studentId = scanner.nextInt();
		Transaction beginTransaction = session.beginTransaction();
		Student student = session.getReference(Student.class, studentId); // u can use session.get() / sesssion.load()
		session.remove(student); // session.delete
		beginTransaction.commit();
		
	}

	public static void updateStudentInfo(Scanner scanner, Session session) {
		System.out.println("Enter sudent id for update");
		int studentId = scanner.nextInt();
		Transaction beginTransaction = session.beginTransaction();
		Student student = session.getReference(Student.class, studentId); // u can use session.get() / sesssion.load()
		System.out.println("Enter certification details to update..");
		boolean addMoreCerts = true;
		while(addMoreCerts ) {
			System.out.println("Enter details in same  format with comma separated 'certificateId', 'institutionName' , 'courseName', 'dateOfCertificationDone', 'cost'");
			String[] details = scanner.next().split("\\,");
			Certificate cert = new Certificate(Integer.valueOf(details[0]),
						details[1], details[2], details[3], Double.valueOf(details[4]));
			System.out.println("Want to add more certificates");
			String next = scanner.next();
			if (next.equalsIgnoreCase("yes") || next.equalsIgnoreCase("true")) {
				addMoreCerts = true;
			}
			else {
				addMoreCerts = false;
			}
			cert.setStudent(student);
			student.getCertificates().add(cert);
		}
		session.createNativeQuery(" ") merge(student); // session.update
		beginTransaction.commit();
		
	}
}
