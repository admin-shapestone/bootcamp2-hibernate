package shapestone.hibernate;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DriverProgram {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		String check = " ";
			Scanner sc = new Scanner(System.in);
			ObjectMapper mapper = new ObjectMapper();
			List<PatientVO> patientList = mapper.readValue(new File(
					"C:\\Users\\91901\\git\\bootcamp2-hibernate\\src\\main\\java\\shapestone\\hibernate\\patienthibernate.json"),
					new TypeReference<List<PatientVO>>() {
					});
			List<TreatmentVO> treatmentList = mapper.readValue(new File(
					"C:\\Users\\91901\\git\\bootcamp2-hibernate\\src\\main\\java\\shapestone\\hibernate\\treatmenthibernate.json"),
					new TypeReference<List<TreatmentVO>>() {
					});
			do {
			System.out.println("option 2 for read the data");
			System.out.println("option 3 for update the data");
			System.out.println("option 4 for delete the data");
			System.out.println("option 5 for print names in ascending order");
			System.out.println("option 6 for print today and yesterday joined people in the hospital");
			System.out.println("option 7 for total cost of each patient");
			Configuration con = new Configuration();
			con.configure("hibernate.cfg.xml");
			con.addAnnotatedClass(Patient.class);
			con.addAnnotatedClass(Treatment.class);
			SessionFactory sf = con.buildSessionFactory();
			Session session = sf.openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				Connections.createTable(session, patientList, treatmentList);
				tx.commit();
				int option = sc.nextInt();
				if (option == 2) {
					Connections.readData(session, sc);
				} else if (option == 3) {
					Connections.updateData(session, sc);
				} else if (option == 4) {
					Connections.deleteData(session, sc);
				} else if (option == 5) {
					Connections.namesInAsc(session);
				} else if (option == 6) {
					Connections.todayAndYesterdayJoined(session);
				} else if (option == 7) {
					Connections.calculateTotalCostOfPatients(session);
				}
				tx.commit();
			} catch (Exception e) {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
				e.printStackTrace();
			} finally {
				session.close();
				sf.close();
			}
			System.out.println("Do you want to continue? (type 'yes')");
			check = sc.next();
		} while (check.equalsIgnoreCase("yes"));
	}
}
