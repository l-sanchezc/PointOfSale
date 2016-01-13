/**
 * Sale: Buy X get one free
 * @author Lazaro Sanchez Campos
 * @version 3.0a
 */
public class BuyXGetOneFree extends Item{
	private int x;
	
	public BuyXGetOneFree(String sku, String description, double unitCost, int quantity, int x) {
		super(sku, description, unitCost, quantity);
		this.x = x;
	}
	
	/**
	 * This method gets the X parameter of the sale.
	 * @return
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * This method sets the X parameter of the sale.
	 * @param x
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * It gets how many times the discount has to be applied: quantity/(x+1). It is x+1 as the (x+1)'th item is free.
	 * @return
	 */
	public int getTimesAppliedDiscount(){
		return quantity / (x+1);
	}
	
	/**
	 * It gets the remainder of whole division quantity/(x+1). It is x+1 as the (x+1)'th item is free.
	 * @return
	 */
	public int getRemainder() {
		return quantity % (x+1);
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
				print = print  + getSku() + " "+ getDescription() + " " + getX() + "@" + unitCost/100 + " = " + getExtendedCost(x, unitCost/100)
						+ "\n" + getSku() + " "+ getDescription() + " 1@0.00 = 0.00";
			} else{
				print = print  + "\n" + getSku() + " "+ getDescription() + " " + getX() + "@" + unitCost/100 + " = " + getExtendedCost(x, unitCost/100)
					+ "\n" + getSku() + " "+ getDescription() + " 1@0.00 = 0.00";
			}
			i++;
		}
		if (getTimesAppliedDiscount()==0 && getRemainder()>0){
		    print = print + getSku() + " "+ getDescription() + " " + getRemainder() + "@" + unitCost/100 + " = " + getExtendedCost(getRemainder(), unitCost/100) + "\n";
		}
		if (getTimesAppliedDiscount() !=0 && getRemainder()>0){
		    print = print + "\n" + getSku() + " "+ getDescription() + " " + getRemainder() + "@" + unitCost/100 + " = " + getExtendedCost(getRemainder(), unitCost/100);
		}
		return print;
	}
}
