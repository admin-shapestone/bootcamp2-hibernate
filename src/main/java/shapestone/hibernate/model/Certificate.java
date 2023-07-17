package shapestone.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "certificates")
public class Certificate {
	@Id
	@Column(name = "certificateId")
	private int certificateId;
	
	private String institutionName;
	
	private String courseName;
	
	private String dateOfCertificationDone;
	
	private double cost;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_Id")
	private Student student;
	


	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(int certificateId) {
		this.certificateId = certificateId;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDateOfCertificationDone() {
		return dateOfCertificationDone;
	}

	public void setDateOfCertificationDone(String dateOfCertificationDone) {
		this.dateOfCertificationDone = dateOfCertificationDone;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Certificate [certificateId=" + certificateId + ", institutionName="
				+ institutionName + ", courseName=" + courseName + ", dateOfCertificationDone="
				+ dateOfCertificationDone + ", cost=" + cost + "]";
	}

	public Certificate(int certificateId, String institutionName, String courseName,
			String dateOfCertificationDone, double cost) {
		super();
		this.certificateId = certificateId;
		this.institutionName = institutionName;
		this.courseName = courseName;
		this.dateOfCertificationDone = dateOfCertificationDone;
		this.cost = cost;
	}

	public Certificate() {
		super();
	}
	
	
	

}
