import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.Desktop;
import javax.swing.JOptionPane;

/**
 * Main controller of the application
 * @author Lazaro Sanchez Campos
 * @version 3.0a
 */
public class PointOfSale implements ActionListener {
	static MainView mView;
	static CustomerOrder customerOrder = new CustomerOrder(0, 0, new ArrayList<Item>());
	static String costsPath;
	static String salesPath;
	static String registerTypePath;
	static ArrayList<String> costsFile = new ArrayList<String>();
	static ArrayList<String> salesFile = new ArrayList<String>();
	static ArrayList<String> registerTape = new ArrayList<String>();
	
	public PointOfSale(MainView mView){
		PointOfSale.mView = mView;
	}
	
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					readArguments(args);
					readInitialSalesFile(salesPath);
					readInitialCostsFile(costsPath);
					mView = new MainView();
					mView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * It reads the arguments from the command line
	 * @param args
	 */
	public static void readArguments(String[] args){
		String path = "";
		for(String s: args){
			path = path + s + " ";
		}
		String[] paths= path.split(" ");
		costsPath = paths[0];
		salesPath = paths[1];
		registerTypePath = paths[2];
	}

	/**
	 * This method handles the actions of the view.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().matches("About")){
			JOptionPane.showMessageDialog(mView, "Lazaro Sanchez Campos \n Student ID: A20362245",
            "About", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getActionCommand().matches("Exit")){
			System.exit(0);
		}
		else if(e.getActionCommand().matches("Add Item")){
			registerTape.clear();
			if(ItemsList.searchSkuInList(mView.skuTextField.getText()) != null){
				updateRegisterTape();
				updateFields();
			} else {
				JOptionPane.showMessageDialog(mView, "Invalid SKU",
			            "Error message", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getActionCommand().matches("Pay")){
			DecimalFormat df = new DecimalFormat("#0.00");
			String orderCompleted = "Total Items: " +customerOrder.getTotalItems()+ "\nTotal Cost: $"+ df.format(customerOrder.getTotalCost())+ "\n+-------------------------------------------------------+";
			registerTape.add(orderCompleted);
			try {
				writeFile();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(mView, "Register tape file not found",
			            "Error message", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			resetAll();
			updateFields();
		}
		else if(e.getActionCommand().matches("Void")){
			resetAll();
			updateFields();
		}
	}
	
	/**
	 * This method updates the register tape
	 */
	public void updateRegisterTape() {
		DecimalFormat df = new DecimalFormat("#0.00");
		customerOrder.setTotalItems(0);
		if (customerOrder.getCustomerList().size()==0){
			customerOrder.getCustomerList().add(ItemsList.searchSkuInList(mView.skuTextField.getText()));
			customerOrder.getCustomerList().get(0).setQuantity(1);
			customerOrder.setTotalCost(customerOrder.getCustomerList().get(0).getUnitCost());
		}
		else {
			int index = -1;
			for (int i=0; i<customerOrder.getCustomerList().size(); i++){
				if(customerOrder.getCustomerList().get(i).getSku().contains(mView.skuTextField.getText())){
					index=i;
				}
			}
			if(index!= -1){
				customerOrder.getCustomerList().get(index).setQuantity(customerOrder.getCustomerList().get(index).getQuantity()+1);
				customerOrder.setTotalCost(customerOrder.getTotalCost()+customerOrder.getCustomerList().get(index).getUnitCost());
			} else{
				customerOrder.getCustomerList().add(ItemsList.searchSkuInList(mView.skuTextField.getText()));
				customerOrder.getCustomerList().get(customerOrder.getCustomerList().size()-1).setQuantity(customerOrder.getCustomerList().get(customerOrder.getCustomerList().size()-1).getQuantity()+1);
				customerOrder.setTotalCost(customerOrder.getTotalCost()+customerOrder.getCustomerList().get(customerOrder.getCustomerList().size()-1).getUnitCost());
			}
		}
		for (int i=0; i<customerOrder.getCustomerList().size(); i++){
			customerOrder.setTotalItems(customerOrder.getTotalItems()+customerOrder.getCustomerList().get(i).getQuantity());
			registerTape.add(customerOrder.getCustomerList().get(i).toString());
		}
		customerOrder.setTotalCost(Double.parseDouble(df.format(customerOrder.getTotalCost())));
	}
	
	/**
	 * It reads the initial sales file and calls the method addSalesToList of the ItemList class
	 * @param salesPath
	 * @throws IOException
	 */
	public static void readInitialSalesFile(String salesPath) throws IOException{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(salesPath));
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		String line;
		CharSequence c;
		while ((line = br.readLine()) != null ){
			salesFile.add(line);
			for (int i=0; i < salesFile.size()-1; i++){
				c = line.substring(0,line.indexOf("|")).subSequence(0, line.indexOf("|"));
				if (salesFile.get(i).contains(c)){
					JOptionPane.showMessageDialog(mView, "There are SKUs duplicated in the Sales file",
				            "Error message", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
					return;
				}
			}
		}
		ItemsList.addSalesToList(salesFile);
	}
	
	/**
	 * It reads the costs file and calls the method addCostsToList of ItemList class.
	 * @param costsPath
	 * @throws IOException
	 */
	public static void readInitialCostsFile(String costsPath) throws IOException{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(costsPath));
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		String line;
		CharSequence c;
		while ((line = br.readLine()) != null ){
			costsFile.add(line);
			for (int i=0; i < costsFile.size()-1; i++){
				c = line.substring(0,line.indexOf("|")).subSequence(0, line.indexOf("|"));
				if (costsFile.get(i).contains(c)){
					JOptionPane.showMessageDialog(mView, "There are SKUs duplicated in the Costs file",
				            "Error message", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
					return;
				}
			}
		}
		ItemsList.addCostsToList(costsFile);
	}
	
	/**
	 * This methods updates the fields of the view.
	 */
	public static void updateFields(){
		DecimalFormat df = new DecimalFormat("#0.00");
		mView.textArea.setText("");
		for (int i=0; i<registerTape.size(); i++){
			mView.textArea.append(registerTape.get(i)+"\n");	
		}	
		mView.totalCostTextField.setText("$"+ df.format(customerOrder.getTotalCost()));
		mView.totalItemsTextField.setText(""+ customerOrder.getTotalItems());
	}
	
	/**
	 * This method resets all the lists of the program
	 */
	public void resetAll(){
		customerOrder = new CustomerOrder(0, 0, new ArrayList<Item>());
		registerTape.clear();
		ItemsList.otherCostumer();
	}
	
	/**
	 * This method writes the register tape file and opens it
	 * @throws IOException 
	 */
	public static void writeFile() throws IOException{
		BufferedWriter bw = null;
		File registerTapeFile = new File (registerTypePath);
		try {
			FileWriter fw = new FileWriter(registerTapeFile,true);
			bw = new BufferedWriter(fw);
			for (int i = 0; i < registerTape.size(); i++) {
				bw.write(registerTape.get(i)+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			bw.flush();
			Desktop desktop = Desktop.getDesktop();
			desktop.open(registerTapeFile);
		}
	}
}
