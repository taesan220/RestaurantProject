package DAODTO;

import java.sql.Date;

public class AnorderDTO {

	// Fields to hold order details
	int num; // Order number
	int tablecode; // Table code
	String kind; // Type of order (e.g., food or drink)
	String name; // Name of the item
	int amount; // Amount of the item ordered
	int anorder; // Additional order-related field (context-specific)
	Date time; // Date and time of the order
	int condition; // Status or condition of the order

	// Getter for order number
	public int getNum() {
		return num;
	}

	// Setter for order number
	public void setNum(int num) {
		this.num = num;
	}

	// Getter for table code
	public int getTablecode() {
		return tablecode;
	}

	// Setter for table code
	public void setTablecode(int tablecode) {
		this.tablecode = tablecode;
	}

	// Getter for order kind
	public String getKind() {
		return kind;
	}

	// Setter for order kind
	public void setKind(String kind) {
		this.kind = kind;
	}

	// Getter for item name
	public String getName() {
		return name;
	}

	// Setter for item name
	public void setName(String name) {
		this.name = name;
	}

	// Getter for amount ordered
	public int getAmount() {
		return amount;
	}

	// Setter for amount ordered
	public void setAmount(int amount) {
		this.amount = amount;
	}

	// Getter for additional order-related field
	public int getAnorder() {
		return anorder;
	}

	// Setter for additional order-related field
	public void setAnorder(int anorder) {
		this.anorder = anorder;
	}

	// Getter for order time
	public Date getTime() {
		return time;
	}

	// Setter for order time
	public void setTime(Date time) {
		this.time = time;
	}

	// Getter for order condition/status
	public int getCondition() {
		return condition;
	}

	// Setter for order condition/status
	public void setCondition(int condition) {
		this.condition = condition;
	}

}