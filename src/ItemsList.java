import java.util.ArrayList;

/**
 * List with all the items obtained from costs and sales file.
 * @author Lazaro Sanchez Campos
 * @version 3.0a
 */
public class ItemsList {
	static ArrayList<Item> itemsList= new ArrayList<Item>();
	
	/**
	 * Add items from the sales file to itemsList.
	 * @param List
	 */
	public static void addSalesToList(ArrayList<String> List){
		for (int i=0; i<List.size(); i++){
			String[] tokens = List.get(i).split("\\|");
				if (tokens[1].contains("DISCOUNT")){
					itemsList.add(new Discount(tokens[0], null, i, 0, Integer.parseInt(tokens[2])));
				}
				else if (tokens[1].contains("BUYXGET1FREE")){
					itemsList.add(new BuyXGetOneFree(tokens[0], null, i, 0, Integer.parseInt(tokens[2])));
				}
				if (tokens[1].contains("BUYXFORY")){
					itemsList.add(new BuyXForTheCostOfY(tokens[0], null, i, 0, Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])));
				}
			}
	}
	
	/**
	 * Add items from the costs file to itemsList; if the item already exists in the list, this method only adds the item's missing parameters (description and unit cost).
	 * @param List
	 */
	public static void addCostsToList(ArrayList<String> List){
		for (int i=0; i<List.size(); i++){
			String[] tokens = List.get(i).split("\\|");
			for (int j=0; j<itemsList.size(); j++)
				if (tokens[0].matches(itemsList.get(j).getSku())){
					itemsList.get(i).setDescription(tokens[1]);
					itemsList.get(i).setUnitCost(Integer.parseInt(tokens[2]));
				} else {
					itemsList.add(new Item(tokens[0], tokens[1], Integer.parseInt(tokens[2]), 0));
			}
		}
	}
	
	/**
	 * Search item using its sku.
	 * @param sku
	 * @return the item if it is in the list.
	 */
	public static Item searchSkuInList(String sku){
		for (int i=0; i<itemsList.size(); i++){
			if (sku.matches(itemsList.get(i).getSku())){
				return itemsList.get(i);	
			}
		}
		return null;
	}
	
	/**
	 * Set the quantity of the items to 0.
	 */
	public static void otherCostumer(){
		for (int i=0; i<itemsList.size(); i++){
			itemsList.get(i).setQuantity(0);
		}
	}
	
}
