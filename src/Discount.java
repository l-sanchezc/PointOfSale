import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Sale: Discount
 * @author Lazaro Sanchez Campos
 * @version 3.0a
 */
public class Discount extends Item{
	private int percent;
	
	public Discount(String sku, String description, double unitCost, int quantity, int percent) {
		super(sku, description, unitCost, quantity);
		this.percent = percent;
	}
	
	/**
	 * Getter of percent of discount.
	 * @return percent
	 */
	public double getPercent() {
		return percent;
	}
	
	/**
	 * Setter of percent of discount.
	 * @param percent
	 */
	public void setPercent(int percent) {
		this.percent = percent;
	}
	
	/**
	 * Getter of discounted unit cost.
	 * @return discounted unit cost
	 */
	public double getDiscountedUnitCost(){
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.DOWN);
		double discountedUnitCost = (unitCost * (100-percent))/10000;
		discountedUnitCost = Double.parseDouble(df.format(discountedUnitCost));
		return discountedUnitCost;
	}
	
	/**
	 * It overrides the method getUnitCost of Item class. It gets the unit cost.
	 */
	public double getUnitCost(){
		return getDiscountedUnitCost();
	}
	
	/**
	 * It overrides the method toString.
	 */
	public String toString() {
	    return getSku() + " "+ getDescription() + " " + percent + "% Off " + getQuantity() + "@" +getDiscountedUnitCost() + " = " + getExtendedCost(quantity, getDiscountedUnitCost());
	}
}
