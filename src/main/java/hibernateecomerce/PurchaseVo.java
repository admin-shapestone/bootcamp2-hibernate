package hibernateecomerce;

import java.util.Date;

public class PurchaseVo {

	private int customerId;
	private int purchaseId;
	private String itemPurchased;
	private int quantity;
	private double price;
	private Date dateOfPurchase;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getItemPurchased() {
		return itemPurchased;
	}
	public void setItemPurchased(String itemPurchased) {
		this.itemPurchased = itemPurchased;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	
	@Override
	public String toString() {
		return "PurchaseVo [customerId=" + customerId + ", purchaseId=" + purchaseId + ", itemPurchased="
				+ itemPurchased + ", quantity=" + quantity + ", price=" + price + ", dateOfPurchase=" + dateOfPurchase
				+ "]";
	}
	public PurchaseVo(int customerId, int purchaseId, String itemPurchased, int quantity, double price,
			Date dateOfPurchase) {
		super();
		this.customerId = customerId;
		this.purchaseId = purchaseId;
		this.itemPurchased = itemPurchased;
		this.quantity = quantity;
		this.price = price;
		this.dateOfPurchase = dateOfPurchase;
	}
	public PurchaseVo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
