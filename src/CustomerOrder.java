import java.util.ArrayList;

/**
 * The customer's order
 * @author Lazaro Sanchez Campos
 * @version 3.0a
 */
public class CustomerOrder {
	private double totalCost;
	private int totalItems;
	private ArrayList<Item> customerList= new ArrayList<Item>();
	
	public CustomerOrder(double totalCost, int totalItems, ArrayList<Item> customerList) {
		super();
		this.totalCost = totalCost;
		this.totalItems = totalItems;
		this.customerList = customerList;
	}

	/**
	 * Getter total cost
	 * @return total cost
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * Setter total cost
	 * @param total cost
	 */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * Getter total items
	 * @return
	 */
	public int getTotalItems() {
		return totalItems;
	}
	
	/**
	 * Setter total items
	 * @param totalItems
	 */
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	/**
	 * Getter customer list
	 * @return customer list
	 */
	public ArrayList<Item> getCustomerList() {
		return customerList;
	}

	/**
	 * Setter customer list
	 * @param customerList
	 */
	public void setCustomerList(ArrayList<Item> customerList) {
		this.customerList = customerList;
	}
	
}