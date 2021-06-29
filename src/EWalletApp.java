import java.util.ArrayList;

import javax.swing.JFrame;

public class EWalletApp extends JFrame {
	private static final long serialVersionUID = 7170541099903686382L;
	
	//this is the app class, has the GUI and create one object of your expense calculator class. The expense calculator class is the implementation of the Expenser interface 
	private ArrayList<User> AllData;
	private Expenser expenser;
	
	EWalletApp() {
		expenser = new ExpenseCalculator();
		
		// create any UI here
		
		setSize(400,500);  
		setLayout(null);  
		setVisible(true);  
	}
	
	public void CreateUser(String username, String password) {}

	public static void main(String[] args) {
		new EWalletApp();
	}
}
