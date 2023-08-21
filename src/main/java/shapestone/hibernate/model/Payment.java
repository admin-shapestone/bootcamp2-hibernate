package shapestone.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Payments_Table")
public class Payment {
	@Id
	@Column(name = "paymentId")
	private long paymentId;

	@Column(name = "purposeOfPayment")
	private String purposeOfPayment;
	@Column(name = "amountPaid")
	private double amountPaid;
	@Column(name = "amountRecived")
	private double amountRecived;
	@Column(name = "dateOfPayment")
	private String dateOfPayment;

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public String getPurposeOfPayment() {
		return purposeOfPayment;
	}

	public void setPurposeOfPayment(String purposeOfPayment) {
		this.purposeOfPayment = purposeOfPayment;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public double getAmountRecived() {
		return amountRecived;
	}

	public void setAmountRecived(double amountRecived) {
		this.amountRecived = amountRecived;
	}

	public String getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(String dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public Payment(long paymentId, String purposeOfPayment, double amountPaid, double amountRecived,
			String dateOfPayment) {
		super();
		this.paymentId = paymentId;
		this.purposeOfPayment = purposeOfPayment;
		this.amountPaid = amountPaid;
		this.amountRecived = amountRecived;
		this.dateOfPayment = dateOfPayment;
	}

}
