package hibernateecomerce;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PurchaseTable")
public class PurchaseLaptop {

	@Id
	@Column(name = "purchaseId")
	private int purchaseId;
	@Column(name = "itemPurchased")
	private String itemPurchased;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "price")
	private double price;
	@Column(name = "dateOfPurchase")
	private Date dateOfPurchase;
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
		return "PurchaseLaptop [purchaseId=" + purchaseId + ", itemPurchased=" + itemPurchased + ", quantity="
				+ quantity + ", price=" + price + ", dateOfPurchase=" + dateOfPurchase + "]";
	}
	public PurchaseLaptop(int purchaseId, String itemPurchased, int quantity, double price, Date dateOfPurchase) {
		super();
		this.purchaseId = purchaseId;
		this.itemPurchased = itemPurchased;
		this.quantity = quantity;
		this.price = price;
		this.dateOfPurchase = dateOfPurchase;
	}
	public PurchaseLaptop() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
