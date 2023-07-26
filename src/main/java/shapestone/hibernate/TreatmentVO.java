package shapestone.hibernate;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class TreatmentVO {
	private int treatmentId;
	private int patientId;
	private String diagnosis;
	private String treatment;
	private Date dateOfTreatment;
	private double cost;

	@Override
	public String toString() {
		return "TreatmentVO [treatmentId=" + treatmentId + ", patientId=" + patientId + ", diagnosis=" + diagnosis
				+ ", treatment=" + treatment + ", dateOfTreatment=" + dateOfTreatment + ", cost=" + cost + "]";
	}

	public int getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(int treatmentId) {
		this.treatmentId = treatmentId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public java.util.Date getDateOfTreatment() {
		return dateOfTreatment;
	}

	public void setDateOfTreatment(Date dateOfTreatment) {
		this.dateOfTreatment = dateOfTreatment;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public TreatmentVO() {
		super();
	}

	public TreatmentVO(int treatmentId, int patientId, String diagnosis, String treatment, Date dateOfTreatment,
			double cost) {
		super();
		this.treatmentId = treatmentId;
		this.patientId = patientId;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.dateOfTreatment = dateOfTreatment;
		this.cost = cost;
	}

}
