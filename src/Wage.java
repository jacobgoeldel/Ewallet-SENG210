
public class Wage {
	String source;
	double amount;
	String month;
	
	//should add contructor(s)
	public Wage(String source, double amount, String month) {
		this.source = source;
		this.amount = amount;
		this.month = month;
	}

	@Override
	public String toString() {
		return String.format("%s: %f every %s", source, amount, month);
	}
	
}
