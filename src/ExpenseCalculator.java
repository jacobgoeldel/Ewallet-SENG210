
public class ExpenseCalculator implements Expenser {
	
	public User currentUser;
	
	public ExpenseCalculator(User user) {
		currentUser = user;
	}
	
	@Override
	public void addExpense(Expense Ex) {
		currentUser.AddExpense(Ex);
	}

	@Override
	public void addMonthlyIncome(Wage W) {
		currentUser.AddIncome(W);
	}

	@Override
	public String PrintFullreport() {
		String output = "Report:\n\n";
		
		double incomeTotal = TotalIncome();
		double expenseTotal = TotalExpenses();
		double savingsTotal = incomeTotal - expenseTotal;
		
		if(savingsTotal < 0)
			output += String.format("Total Debt Yearly: $%,.2f\n", savingsTotal * -1);
		else
			output += String.format("total Savings Yearly: $%,.2f\n", savingsTotal);
		
		output += String.format("Yearly Income: $%,.2f\n", incomeTotal);
		output += String.format("Yearly Expenses: $%,.2f\n\n", expenseTotal);
		
		output += PrintExpensereport();
		output += PrintIncomereport();
		
		return output;
	}
	
	private double TotalIncome() {
		double total = 0;
		
		for (Wage wg : currentUser.GetIncomes()) {
			total += wg.amount;
		}
		
		return total;
	}
	
	private double TotalExpenses() {
		double total = 0;
		
		for (Expense ex : currentUser.GetExpenses()) {
			total += ex.amount * ex.yearlyfrequency;
		}
		
		return total;
	}

	@Override
	public String PrintExpensereport() {
		String output = "User's Expenses:\n";
		
		for (Expense ex : currentUser.GetExpenses()) {
			output += ex.toString() + "\n";
		}
		
		return output;
	}

	@Override
	public String PrintIncomereport() {
		String output = "User's Income:\n";
		
		for (Wage wg : currentUser.GetIncomes()) {
			output += wg.toString() + "\n";
		}
		
		return output;
	}

	@Override
	public void PrintIncomereportbyTpe() {
		// TODO Auto-generated method stub

	}

	@Override
	public void PrintExpensebyType() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exportReport(String reportTitle) {
		// TODO Auto-generated method stub

	}

	@Override
	public Currency convertForeignCurrency(Currency C, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean loadExpenseFile(String filePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loadIncomeFile(String filePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int whenCanIBuy(String itemname, double price) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateMonthlySavings() {
		// TODO Auto-generated method stub

	}

}
