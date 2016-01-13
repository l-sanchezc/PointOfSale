/**
 * Sale: Buy X for the cost of Y
 * @author Lazaro Sanchez Campos
 * @version 3.0a
 */
public class BuyXForTheCostOfY extends Item{
	private int x;
	private int y;
	
	public BuyXForTheCostOfY(String sku, String description, double unitCost, int quantity, int x, int y) {
		super(sku, description, unitCost, quantity);
		this.x = x;
		this.y= y;
	}
	
	/**
	 * This method gets the X parameter of the sale.
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * This method sets the X parameter of the sale.
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * This method gets the Y parameter of the sale.
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * This method sets the Y parameter of the sale.
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * It gets how many times the discount has to be applied.
	 * @return
	 */
	public int getTimesAppliedDiscount(){
		return quantity/x;
	}
	
	/**
	 * It gets the remainder of whole division quantity/x.
	 * @return
	 */
	public int getRemainder() {
		return quantity % x;
	}
	
	/**
	 * It overrides the method getUnitCost of Item class. It gets the unit cost.
	 */
	public double getUnitCost(){
		if(getRemainder() != 0){
			return unitCost/100;
		} else {
			return 0;
		}
	}
	
	/**
	 * It overrides the method toString.
	 */
	public String toString() {
		String print="";
		int i = 0;
		while( i < getTimesAppliedDiscount()){
			if(i==0){
				print = print + getSku() + " "+ getDescription() + " "+ getX() + " for "+ getY() + "@"+ unitCost/100+ " = " + getExtendedCost(y, unitCost/100);
			} else {
				print = print + "\n" + getSku() + " "+ getDescription() + " "+ getX() + " for "+ getY() + "@"+ unitCost/100+ " = " + getExtendedCost(y, unitCost/100);
			}
			i++;
		}
		if (getTimesAppliedDiscount()==0 && getRemainder()>0){
		    print = print + getSku() + " "+ getDescription() + " " + getRemainder() + "@" + unitCost/100 + " = " + getExtendedCost(getRemainder(), unitCost/100)  + "\n";
		}
		if (getTimesAppliedDiscount() != 0 && getRemainder()>0){
		    print = print + "\n" + getSku() + " "+ getDescription() + " " + getRemainder() + "@" + unitCost/100 + " = " + getExtendedCost(getRemainder(), unitCost/100);
		}
		return print;
	} 
}