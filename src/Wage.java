
public class Wage {
	String source;
	double amount;
	int yearlyfrequency; // 1 for 1 time or once a year, 12 for monthly or or 24 for biweekly
	
	//should add contructor(s)
	public Wage(String source, double amount, int yearlyfrequency) {
		this.source = source;
		this.amount = amount;
		this.yearlyfrequency = yearlyfrequency;
	}

	@Override
	public String toString() {
		return String.format("%s: $%,.2f %d time(s) a year", source, amount, yearlyfrequency);
	}
	
}
