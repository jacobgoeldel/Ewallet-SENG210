

import java.util.ArrayList;
import java.util.List;

public class DailyExpenses {
	private List<Item> expensesList;

	public DailyExpenses() {
		expensesList = new ArrayList<Item>();
	}
	
	public int numItems() {
		return expensesList.size();
	}
	
	public void addItem(String itemName, double itemPrice) {
		Item newItem = new Item(itemName, itemPrice);
		expensesList.add(newItem);
	}
	
	public String getItemName(int i) {
		return expensesList.get(i).getName();
	}
	
	public double getItemPrice(int i) {
		return expensesList.get(i).getPrice();
	}
	
	public double getTotalPrice() {
		double total = 0.0;
		for(Item item : expensesList) {
			total += item.getPrice();
		}
		return total;
	}

}
