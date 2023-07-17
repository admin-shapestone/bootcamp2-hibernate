package shapestone.hibernate.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import shapestone.hibernate.VO.CertificateVO;
import shapestone.hibernate.VO.StudentVO;
import shapestone.hibernate.model.Certificate;
import shapestone.hibernate.model.Student;

public class HibernateUtil {
	
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/bc2_hibernate");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "shapestone");
                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Certificate.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    

	public static List<StudentVO> readJsonFile() throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		StudentVO[] students = mapper.readValue(new File("C:\\Users\\surya\\shapestone\\projects\\bootcamp2\\bootcamp2-hibernate\\src\\main\\resources\\student.json"), StudentVO[].class);

		return Arrays.asList(students);
	}
	
	public static List<CertificateVO> readJsonFileForCertificates() throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		CertificateVO[] students = mapper.readValue(new File("C:\\Users\\surya\\shapestone\\projects\\bootcamp2\\bootcamp2-hibernate\\src\\main\\resources\\certificates.json"), CertificateVO[].class);

		return Arrays.asList(students);
	}


	/**
	 * @return
	 * @throws StreamReadException
	 * @throws DatabindException
	 * @throws IOException
	 */
	public static Session dupmpingDataIntoTable() throws StreamReadException, DatabindException, IOException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        List<StudentVO> studentList = HibernateUtil.readJsonFile();
        List<CertificateVO> certificatesList = HibernateUtil.readJsonFileForCertificates();
        
        System.out.println("Insertion start...");
        Session session = sessionFactory.openSession();
        Transaction beginTransaction = session.beginTransaction();
        studentList.stream().forEach(studentVo -> {
        	Student student = new Student(studentVo.getId(), studentVo.getName(), studentVo.getLastName(), studentVo.getAge());
        	Set<CertificateVO> collect = certificatesList.stream().filter(cert -> cert.getStudentId() == studentVo.getId())
        	.collect(Collectors.toSet());
        	Set<Certificate> certs = trasnformAndGetCustomerEntites(collect, student);
        	student.setCertificates(certs);
        	session.persist(student);
        });
        beginTransaction.commit();
        System.out.println("Insertion end...");
		return session;
	}


	private static Set<Certificate> trasnformAndGetCustomerEntites(Set<CertificateVO> collect, Student student) {
		// TODO Auto-generated method stub
		Set<Certificate> set = new HashSet<>();
		collect.forEach(vo -> {
			Certificate cert = new Certificate();
			cert.setCertificateId(vo.getCertificateId());
			cert.setCost(vo.getCost());
			cert.setCourseName(vo.getCourseName());
			cert.setDateOfCertificationDone(vo.getDateOfCertificationDone());
			cert.setInstitutionName(vo.getInstitutionName());
			cert.setStudent(student);
			set.add(cert);
		});
		return set;
	}
}
