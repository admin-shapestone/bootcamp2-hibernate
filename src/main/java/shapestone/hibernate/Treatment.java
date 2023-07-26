package shapestone.hibernate;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "treatments")
public class Treatment {
	@Id
	private int treatmentId;
	/*
	 * @Column(name = "patientId") private int patientId;
	 */
	@Column(name = "diagnosis")
	private String diagnosis;
	@Column(name = "treatment")
	private String treatment;

	private Date dateOfTreatment;
	@Column(name = "cost")
	private double cost;

	@Override
	public String toString() {
		return "Treatment [treatmentId=" + treatmentId + ", diagnosis=" + diagnosis + ", treatment=" + treatment
				+ ", dateOfTreatment=" + dateOfTreatment + ", cost=" + cost + "]";
	}

	public int getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(int treatmentId) {
		this.treatmentId = treatmentId;
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

	public Treatment() {
		super();
	}

	public Treatment(int treatmentId, String diagnosis, String treatment, Date dateOfTreatment, double cost) {
		super();
		this.treatmentId = treatmentId;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.dateOfTreatment = dateOfTreatment;
		this.cost = cost;
	}

}
