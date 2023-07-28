package com.shapestone.bankingdomin.vo;

public class PaymentVO {
	private long accountId;
	private long paymentId;
	private String purposeOfPayment;
	private double amountPaid;
	private double amountRecived;
	private String dateOfPayment;

	public PaymentVO() {
		super();
	}

	public PaymentVO(long accountId, long paymentId, String purposeOfPayment, double amountPaid, double amountRecived,
			String dateOfPayment) {
		super();
		this.accountId = accountId;
		this.paymentId = paymentId;
		this.purposeOfPayment = purposeOfPayment;
		this.amountPaid = amountPaid;
		this.amountRecived = amountRecived;
		this.dateOfPayment = dateOfPayment;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

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

	@Override
	public String toString() {
		return "PaymentsVo [accountId=" + accountId + ", paymentId=" + paymentId + ", purposeOfPayment="
				+ purposeOfPayment + ", amountPaid=" + amountPaid + ", amountRecived=" + amountRecived
				+ ", dateOfPayment=" + dateOfPayment + "]";
	}
}
