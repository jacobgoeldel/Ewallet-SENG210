import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class EWalletApp extends JFrame {
	private static final long serialVersionUID = 7170541099903686382L;
	
	//this is the app class, has the GUI and create one object of your expense calculator class. The expense calculator class is the implementation of the Expenser interface 
	private ArrayList<User> AllData;
	private Expenser expenser;
	
	// UI Components
	private JButton addExpenseButton;
	private JButton addIncomeButton;
	private JButton itemButton;
	
	EWalletApp() {
		expenser = new ExpenseCalculator(new User("testuser", "password"));
		
		setLayout(new GridLayout(3,1));  
		setSize(400,500);
		
		// create any UI here
		
		// add monthly expense button
		addExpenseButton = new JButton("Add New Monthly Expense");
		addIncomeButton = new JButton("Add New Monthly Income");
		itemButton = new JButton("Upcoming Purchase");
		add(addExpenseButton);
		add(addIncomeButton);
		add(itemButton);
		
		addExpenseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JTextField expenseName = new JTextField();
				JTextField expenseAmount = new JTextField();
				JTextField expenseFrequency = new JTextField();
				
				Object[] newExpense = {
					    "Expense Name: ", expenseName,
					    "Amount: ", expenseAmount,
					    "Yearly Frequency: ", expenseFrequency
					};

				
				int option = JOptionPane.showConfirmDialog(null, newExpense, "Add New Expense", JOptionPane.OK_CANCEL_OPTION);
				
				// exit out if they canceled
				if(option != JOptionPane.OK_OPTION)
					return;
				
				Expense expense = new Expense(expenseName.getText(), Double.parseDouble(expenseAmount.getText()), Integer.parseInt(expenseFrequency.getText()));
				expenser.addExpense(expense);
			}
		});
		
		addIncomeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JTextField incomeName = new JTextField();
				JTextField incomeAmount = new JTextField();
				JTextField incomeMonth = new JTextField();
				
				Object[] newIncome = {
					    "Income Name: ", incomeName,
					    "Amount: ", incomeAmount,
					    "Month: ", incomeMonth
					};

				
				int option = JOptionPane.showConfirmDialog(null, newIncome, "Add New Income", JOptionPane.OK_CANCEL_OPTION);
				
				// exit out if they canceled
				if(option != JOptionPane.OK_OPTION)
					return;
				
				Wage wage = new Wage(incomeName.getText(), Double.parseDouble(incomeAmount.getText()), incomeMonth.getText());
				expenser.addMonthlyIncome(wage);
			}
		});
		
		itemButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField itemName = new JTextField();
				JTextField itemPrice = new JTextField();
				
				Object[] itemCheck = {
						"Product: ", itemName,
						"Price: ", itemPrice
				};
				
				int option = JOptionPane.showConfirmDialog(null, itemCheck, "Upcoming Purchase", JOptionPane.OK_CANCEL_OPTION);
				if(option != JOptionPane.OK_OPTION) {
					return;
				}
				
				int result = expenser.whenCanIBuy(itemName.getText(), Double.parseDouble(itemPrice.getText()));
				
				if(result == -1) {
					JOptionPane.showMessageDialog(null, "No monthly savings founds, please update income and expenses.");
				}
				else if(result == 0) {
					JOptionPane.showMessageDialog(null, "You could buy " + itemName.getText() + " right now!");
				}
				else if (result >= 12) {
					JOptionPane.showMessageDialog(null, "You'll have to save for a year or more to buy " + itemName.getText() + ".");
				}
				else {
					JOptionPane.showMessageDialog(null, "You'll have to save for " + result + " months before you can buy " + itemName.getText() + ".");
				}
			}
		}); 
		
		setVisible(true);
	}
	
	public void CreateUser(String username, String password) {}

	public static void main(String[] args) {
		new EWalletApp();
	}
}
