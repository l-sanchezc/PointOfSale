import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * Mainview
 * @author Lazaro Sanchez Campos
 * @verison 3.0a
 */
public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField totalItemsTextField;
	public JTextField totalCostTextField;
	public JTextField skuTextField;
	public JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public MainView() {
		super("Point of Sale register");
		setResizable(false);
		PointOfSale pointOfSale = new  PointOfSale(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 911, 344);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu registerMenu = new JMenu("Register");
		menuBar.add(registerMenu);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		registerMenu.add(exitMenuItem);
		exitMenuItem.addActionListener(pointOfSale);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem aboutMenuItem = new JMenuItem("About");
		helpMenu.add(aboutMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aboutMenuItem.addActionListener(pointOfSale);
		
		JButton payButton = new JButton("Pay");
		payButton.setBounds(398, 262, 78, 29);
		payButton.addActionListener(pointOfSale);
		contentPane.add(payButton);
		
		JButton voidButton = new JButton("Void");
		voidButton.setBounds(496, 262, 75, 29);
		voidButton.addActionListener(pointOfSale);
		contentPane.add(voidButton);
		
		totalItemsTextField = new JTextField();
		totalItemsTextField.setEditable(false);
		totalItemsTextField.setBounds(344, 194, 78, 21);
		contentPane.add(totalItemsTextField);
		totalItemsTextField.setColumns(10);
		
		totalCostTextField = new JTextField();
		totalCostTextField.setEditable(false);
		totalCostTextField.setColumns(10);
		totalCostTextField.setBounds(524, 194, 95, 21);
		contentPane.add(totalCostTextField);
		
		JLabel totalItemsLabel = new JLabel("Total Items:");
		totalItemsLabel.setBounds(254, 191, 78, 29);
		contentPane.add(totalItemsLabel);
		
		JLabel totalCostLabel = new JLabel("Total Cost:");
		totalCostLabel.setBounds(449, 191, 78, 29);
		contentPane.add(totalCostLabel);
		
		JLabel skuLabel = new JLabel("SKU:");
		skuLabel.setBounds(340, 226, 35, 29);
		contentPane.add(skuLabel);
		
		skuTextField = new JTextField();
		skuTextField.setColumns(10);
		skuTextField.setBounds(370, 229, 97, 21);
		contentPane.add(skuTextField);
		
		JButton addItemButton = new JButton("Add Item");
		addItemButton.setBounds(502, 228, 117, 29);
		addItemButton.addActionListener(pointOfSale);
		contentPane.add(addItemButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 6, 899, 181);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setRows(12);
		textArea.setColumns(80);
	}
}
