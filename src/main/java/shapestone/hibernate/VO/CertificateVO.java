package shapestone.hibernate.VO;

import java.util.Objects;

public class CertificateVO {
	private int certificateId;

	private int studentId;
	
	private String institutionName;
	
	private String courseName;
	
	private String dateOfCertificationDone;
	
	private double cost;
	
	

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(int certificateId) {
		this.certificateId = certificateId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
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

	@Override
	public String toString() {
		return "CertificateVO [certificateId=" + certificateId + ", studentId=" + studentId + ", institutionName="
				+ institutionName + ", courseName=" + courseName + ", dateOfCertificationDone="
				+ dateOfCertificationDone + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(certificateId, courseName, dateOfCertificationDone, institutionName, studentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CertificateVO other = (CertificateVO) obj;
		return certificateId == other.certificateId && Objects.equals(courseName, other.courseName)
				&& Objects.equals(dateOfCertificationDone, other.dateOfCertificationDone)
				&& Objects.equals(institutionName, other.institutionName) && studentId == other.studentId;
	}
	
	
	
}
