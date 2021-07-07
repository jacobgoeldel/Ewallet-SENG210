
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
	public void PrintFullreport() {
		// TODO Auto-generated method stub

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
