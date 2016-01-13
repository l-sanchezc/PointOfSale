import java.text.DecimalFormat;

/**
 * Item
 * @author Lazaro Sanchez Campos
 * @version 3.0a
 */
public class Item {
	private String sku;
	private String description;
	protected double unitCost;
	protected int quantity;
	
	public Item(String sku, String description, double unitCost, int quantity) {
		super();
		this.sku = sku;
		this.description = description;
		this.unitCost = unitCost;
		this.quantity = quantity;
	}

	/**
	 * Getter sku
	 * @return sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * Setter sku
	 * @param sku
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * Getter description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter unit cost
	 * @return unitCost
	 */
	public double getUnitCost() {
		return unitCost/100;
	}

	/**
	 * Setter unit cost
	 * @param unitCost
	 */
	public void setUnitCost(int unitCost) {
		this.unitCost = unitCost;
	}
	
	/**
	 * Getter quantity of item
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Setter quantity of item
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Getter of extended cost
	 * @param quantity
	 * @param unitCost
	 * @return extended cost
	 */
	public String getExtendedCost(int quantity, double unitCost) {
		DecimalFormat df = new DecimalFormat("#0.00");
		double extendedCost = quantity * unitCost;
		return df.format(extendedCost);
	}
	
	/**
	 * It overrides the method toString.
	 */
	public String toString() {
	    return getSku() + " "+ getDescription() + " " + getQuantity() + "@" + getUnitCost() + " = " + getExtendedCost(quantity, getUnitCost());
	}
}
