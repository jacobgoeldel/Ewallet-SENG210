import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EWalletApp extends JFrame {
	private static final long serialVersionUID = 7170541099903686382L;

	// this is the app class, has the GUI and create one object of your expense
	// calculator class. The expense calculator class is the implementation of the
	// Expenser interface
	private ArrayList<User> AllData;
	private Expenser expenser;

	// UI Components
	private JButton addExpenseButton;
	private JButton addIncomeButton;
	private JButton itemButton;
	private JButton currencyButton;
	private JTextArea reportOutput;
	private JScrollPane reportScrollPane;

	EWalletApp() {
		// Setup program to close after the window closes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Expense Calculator");

		expenser = new ExpenseCalculator(new User("testuser", "password"));

		// Setup all of the layouts and window

		FlowLayout layout = new FlowLayout();
		setLayout(layout);

		JPanel buttonpanel = new JPanel();

		GridLayout buttonLayout = new GridLayout(0, 3);
		buttonLayout.setHgap(10);
		buttonLayout.setVgap(10);

		buttonpanel.setLayout(buttonLayout);

		setSize(600, 500);
		setResizable(false);

		// create any UI here \\

		// add monthly expense button
		addExpenseButton = new JButton("Add New Monthly Expense");
		addIncomeButton = new JButton("Add New Monthly Income");
		itemButton = new JButton("Upcoming Purchase");
		currencyButton = new JButton("Currency Conversion");

		// Create the report text area
		reportOutput = new JTextArea("", 1, 1);
		reportOutput.setLineWrap(true);
		reportOutput.setEditable(false);
		reportOutput.setVisible(true);

		reportScrollPane = new JScrollPane(reportOutput);
		reportScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		reportScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		reportScrollPane.setPreferredSize(new Dimension(500, 400));

		// add the buttons to the top panel

		buttonpanel.add(addExpenseButton);
		buttonpanel.add(addIncomeButton);
		buttonpanel.add(itemButton);
		buttonpanel.add(currencyButton);

		// add button panel and report to the window

		add(buttonpanel);
		add(reportScrollPane);

		// Setup events here \\

		addExpenseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JTextField expenseName = new JTextField();
				JTextField expenseAmount = new JTextField();
				JTextField expenseFrequency = new JTextField();

				Object[] newExpense = { "Expense Name: ", expenseName, "Amount: ", expenseAmount, "Yearly Frequency: ",
						expenseFrequency };

				int option = JOptionPane.showConfirmDialog(null, newExpense, "Add New Expense",
						JOptionPane.OK_CANCEL_OPTION);

				// exit out if they canceled
				if (option != JOptionPane.OK_OPTION)
					return;

				Expense expense = new Expense(expenseName.getText(), Double.parseDouble(expenseAmount.getText()),
						Integer.parseInt(expenseFrequency.getText()));
				expenser.addExpense(expense);

				reportOutput.setText(expenser.PrintFullreport());
			}
		});

		addIncomeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JTextField incomeName = new JTextField();
				JTextField incomeAmount = new JTextField();
				JTextField incomeMonth = new JTextField();

				Object[] newIncome = { "Income Name: ", incomeName, "Amount: ", incomeAmount, "Month: ", incomeMonth };

				int option = JOptionPane.showConfirmDialog(null, newIncome, "Add New Income",
						JOptionPane.OK_CANCEL_OPTION);

				// exit out if they canceled
				if (option != JOptionPane.OK_OPTION)
					return;

				Wage wage = new Wage(incomeName.getText(), Double.parseDouble(incomeAmount.getText()),
						incomeMonth.getText());
				expenser.addMonthlyIncome(wage);

				reportOutput.setText(expenser.PrintFullreport());
			}
		});

		itemButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField itemName = new JTextField();
				JTextField itemPrice = new JTextField();

				Object[] itemCheck = { "Product: ", itemName, "Price: ", itemPrice };

				int option = JOptionPane.showConfirmDialog(null, itemCheck, "Upcoming Purchase",
						JOptionPane.OK_CANCEL_OPTION);
				if (option != JOptionPane.OK_OPTION) {
					return;
				}

				int result = expenser.whenCanIBuy(itemName.getText(), Double.parseDouble(itemPrice.getText()));

				if (result == -1) {
					JOptionPane.showMessageDialog(null,
							"No monthly savings founds, please update income and expenses.");
				} else if (result == 0) {
					JOptionPane.showMessageDialog(null, "You could buy " + itemName.getText() + " right now!");
				} else if (result >= 12) {
					JOptionPane.showMessageDialog(null,
							"You'll have to save for a year or more to buy " + itemName.getText() + ".");
				} else {
					JOptionPane.showMessageDialog(null, "You'll have to save for " + result
							+ " months before you can buy " + itemName.getText() + ".");
				}
			}
		});
		
		currencyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Currency euro = new Currency(0.85, "Euro");
				Currency cDollar = new Currency(1.27, "Canadian Dollar");
				Currency peso = new Currency (20.14, "Peso");
				
				ButtonGroup choices = new ButtonGroup();
				JRadioButton eB = new JRadioButton("Euro");
				JRadioButton cB = new JRadioButton("Canadian Dollar");
				JRadioButton pB = new JRadioButton("Peso");
				choices.add(eB);
				choices.add(cB);
				choices.add(pB);
				
				Object[] conversion = {"Choose A Currency: ", eB, cB, pB};
				
				int option = JOptionPane.showConfirmDialog(null, conversion, "Currency Conversion", JOptionPane.OK_CANCEL_OPTION);
				
				if (option != JOptionPane.OK_OPTION) {
					return;
				}
				
				if(eB.isSelected()) {
					JOptionPane.showMessageDialog(null, expenser.convertForeignCurrency(euro));
				}
				else if(cB.isSelected()) {
					JOptionPane.showMessageDialog(null, expenser.convertForeignCurrency(cDollar));
				}
				else if (pB.isSelected()) {
					JOptionPane.showMessageDialog(null, expenser.convertForeignCurrency(peso));
				}
									
			}
		});

		setVisible(true);
	}

	public void CreateUser(String username, String password) {
	}

	public static void main(String[] args) {
		new EWalletApp();
	}
}
