import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EWalletApp extends JFrame {
	private static final long serialVersionUID = 7170541099903686382L;
	
	//this is the app class, has the GUI and create one object of your expense calculator class. The expense calculator class is the implementation of the Expenser interface 
	private ArrayList<User> AllData;
	private Expenser expenser;
	
	// UI Components
	private JButton addExpenseButton;
	private JButton addIncomeButton;
	private JTextArea reportOutput;
	private JScrollPane reportScrollPane;
	
	EWalletApp() {
		// Setup program to close after the window closes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		expenser = new ExpenseCalculator(new User("testuser", "password"));
		
		setLayout(new GridLayout(3,1));  
		setSize(400,500);
		
		// create any UI here
		
		// add monthly expense button
		addExpenseButton = new JButton("Add New Monthly Expense");
		addIncomeButton = new JButton("Add New Monthly Income");
		
		reportOutput = new JTextArea("", 1, 1);
		reportOutput.setLineWrap(true);
		reportOutput.setEditable(false);
		reportOutput.setVisible(true);
	    
		reportScrollPane = new JScrollPane(reportOutput);
		reportScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		reportScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(addExpenseButton);
		add(addIncomeButton);
		add(reportScrollPane);
		
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
				
				reportOutput.append(expenser.PrintFullreport());
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
				
				reportOutput.append(expenser.PrintFullreport());
			}
		});
		
		setVisible(true);
	}
	
	public void CreateUser(String username, String password) {}

	public static void main(String[] args) {
		new EWalletApp();
	}
}
