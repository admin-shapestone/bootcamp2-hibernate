package shapestone.hibernate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class Connections {

	public static void createTable(Session session, List<PatientVO> patientList, List<TreatmentVO> treatmentList) {
		for (PatientVO patientVO : patientList) {
			Patient patient = new Patient(patientVO.getPatientId(), patientVO.getName(), patientVO.getAge(),
					patientVO.getGender(), patientVO.getAddress());
			ArrayList<Treatment> treatmentsForPatient = getAllTreatmentsForPatient(patientVO, treatmentList);
			patient.setTreatment(treatmentsForPatient);
			session.persist(patient);
		}
	}

	private static   ArrayList<Treatment> getAllTreatmentsForPatient(PatientVO patientVO,
			List<TreatmentVO> treatmentList) {
		List<TreatmentVO> treatmentListForPatient = treatmentList.stream()
				.filter(treatmentVO -> treatmentVO.getPatientId() == patientVO.getPatientId())
				.collect(Collectors.toList());
		ArrayList<Treatment> treatmentEntities = new ArrayList<>();
		treatmentListForPatient.forEach(treatmentVO -> {
			Treatment treatment = new Treatment(treatmentVO.getTreatmentId(), treatmentVO.getDiagnosis(),
					treatmentVO.getTreatment(), new Date(treatmentVO.getDateOfTreatment().getTime()),
					treatmentVO.getCost());
			treatmentEntities.add(treatment);
		});
		return treatmentEntities;
	}

	public static void readData(Session session, Scanner sc) {
		System.out.println("Enter the patientid to get details:");
		int patientId = sc.nextInt();
		sc.nextLine();

		// Read and display details of a specific patient
		Patient patientToRead = session.get(Patient.class, patientId);
		if (patientToRead != null) {
			System.out.println("Patient Details:");
			System.out.println("+------------+--------------+------+--------+--------------+");
			System.out.println("| Patient ID |     Name     | Age  | Gender |    Address   |");
			System.out.println("+------------+--------------+------+--------+--------------+");
			System.out.printf("| %-10d | %-12s | %-4d | %-6s | %-12s |\n", patientToRead.getPatientId(),
					patientToRead.getName(), patientToRead.getAge(), patientToRead.getGender(),
					patientToRead.getAddress());
			System.out.println("+------------+--------------+------+--------+--------------+");
		} else {
			System.out.println("Patient with ID " + patientId + " not found.");
		}

		// Read and display all patients
		List<Patient> patients = session.createQuery("FROM Patient", Patient.class).list();
		System.out.println("All Patients:");
		System.out.println("+------------+--------------+------+--------+--------------+");
		System.out.println("| Patient ID |     Name     | Age  | Gender |    Address   |");
		System.out.println("+------------+--------------+------+--------+--------------+");
		for (Patient patient : patients) {
			System.out.printf("| %-10d | %-12s | %-4d | %-6s | %-12s |\n", patient.getPatientId(), patient.getName(),
					patient.getAge(), patient.getGender(), patient.getAddress());
		}
		System.out.println("+------------+--------------+------+--------+--------------+");

		// Read and display all treatments
		List<Treatment> treatments = session.createQuery("FROM Treatment", Treatment.class).list();
		System.out.println("All Treatments:");
		System.out.println("+---------------+------------+-------------+---------------+--------------+");
		System.out.println("| Treatment ID  | Diagnosis  |   Treatment   | Date Of Trmt |    Cost      |");
		System.out.println("+---------------+------------+-------------+---------------+--------------+");
		for (Treatment treatment : treatments) {
			System.out.printf("| %-13d | %-10s | %-11s | %-13s | %-12.2f |\n", treatment.getTreatmentId(),
					treatment.getDiagnosis(), treatment.getTreatment(), treatment.getDateOfTreatment(),
					treatment.getCost());
		}
		System.out.println("+---------------+------------+-------------+---------------+--------------+");
	}

	public static void deleteData(Session session, Scanner sc) {
		System.out.println("Enter the patientId of the patient to delete:");
		int patientIdToDelete = sc.nextInt();
		Patient patientToDelete = session.get(Patient.class, patientIdToDelete);
		if (patientToDelete != null) {
			session.delete(patientToDelete);
			System.out.println("Patient deleted successfully.");
		} else {
			System.out.println("Patient not found.");
		}
	}

	public static void updateData(Session session, Scanner sc) {
		List<Patient> patients = session.createQuery("FROM Patient", Patient.class).list();
		System.out.println("All Patients:");
		for (Patient patient : patients) {
			System.out.println(patient);
		}

		System.out.println("Enter the patientId of the patient to update:");
		int patientIdToUpdate = sc.nextInt();
		sc.nextLine(); // Consume the newline character

		Patient patientToUpdate = session.get(Patient.class, patientIdToUpdate);
		if (patientToUpdate != null) {
			System.out.println("Enter the updated name:");
			String updatedName = sc.nextLine();
			patientToUpdate.setName(updatedName);
			session.update(patientToUpdate);
			System.out.println("Patient updated successfully.");
		} else {
			System.out.println("Patient not found.");
		}
	}

	public static void namesInAsc(Session session) {
		Query<String> query = session.createQuery("SELECT name FROM Patient ORDER BY name ASC", String.class);
		List<String> names = query.getResultList();
		System.out.println("Names in ascending order:");
		for (String name : names) {
			System.out.println(name);
		}
	}

	public static void todayAndYesterdayJoined(Session session) {
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);

		Query<Treatment> query = session.createQuery(
				"FROM Treatment WHERE DATE(dateOfTreatment) = :today OR DATE(dateOfTreatment) = :yesterday",
				Treatment.class);
		query.setParameter("today", Date.valueOf(today));
		query.setParameter("yesterday", Date.valueOf(yesterday));
		List<Treatment> treatments = query.getResultList();

		System.out.println("Patients who joined today or yesterday:");
		System.out.println("+------------+--------------+-----------------+--------------+");
		System.out.println("| TreatmentID| Diagnosis    | DateOfTreatment |    Cost      |");
		System.out.println("+------------+--------------+-----------------+--------------+");
		for (Treatment treatment : treatments) {
			System.out.printf("| %-11d | %-12s | %-14tF | %-12.2f |\n", treatment.getTreatmentId(),
					treatment.getDiagnosis(), treatment.getDateOfTreatment(), treatment.getCost());
		}
		System.out.println("+------------+--------------+-----------------+--------------+");
	}

	public static void calculateTotalCostOfPatients(Session session) {
		Query<Object[]> query = session.createNativeQuery(
				"SELECT treatmentvo.patient_id, SUM(cost) FROM treatments treatmentvo GROUP BY treatmentvo.patient_id;",
				Object[].class);
		List<Object[]> resultList = query.getResultList();

		System.out.println("Total cost of each patient:");
		System.out.println("+------------+--------------+");
		System.out.println("| Patient ID | Total Cost   |");
		System.out.println("+------------+--------------+");
		for (Object[] result : resultList) {
			int patientId = (int) result[0];
			double totalCost = (double) result[1];
			System.out.printf("| %-10d | %-12.2f |\n", patientId, totalCost);
		}
		System.out.println("+------------+--------------+");

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the patient ID to get the total cost: ");
		int patientId = scanner.nextInt();

		Query<Double> costQuery = session.createQuery(
				"SELECT SUM(cost) FROM TreatmentVo treatmentvo WHERE treatmentvo.patientId = :patientId", Double.class);
		costQuery.setParameter("patientId", patientId);
		Double totalCostForPatient = costQuery.getSingleResult();

		if (totalCostForPatient != null) {
			System.out.println("Total cost for patient " + patientId + ": " + totalCostForPatient);
		} else {
			System.out.println("No records found for patient " + patientId);
		}

		scanner.close();
	}
}
