package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import common.DailyExpenses;

import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.beans.PropertyChangeEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;

public class MainFram extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private JTextField txtItem;
	private JTextField txtPrice;
	private JLabel lblTotal;
	private JDateChooser dateChooser;
	private DailyExpenses expenses;
	private JButton btnAddItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFram frame = new MainFram();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFram() {
		initComponents();
		createEvents();

	}

	/**
	 * 
	 */
	private void initComponents() {
		setResizable(false);
		setTitle("Daily Expenses");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		Font font = new Font("Tahoma", Font.PLAIN, 16);

		{// header panel for selecting the date
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.NORTH);
			panel.setLayout(new GridLayout(1, 2, 10, 10));

			JLabel lblDate = new JLabel("Date: ");
			lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(lblDate);
			lblDate.setFont(font);

			dateChooser = new JDateChooser();
			
			panel.add(dateChooser);
			Date date = new Date();
			dateChooser.setDate(date);
			dateChooser.setFont(font);
		}

		{// expenses table
			Object[][] data = {};

			String[] columnHeaders = { "Item name", "Price" };

			model = new DefaultTableModel(data, columnHeaders);
			table = new JTable(model);
			table.setFont(new Font("Arial", Font.PLAIN, 14));
			table.getColumnModel().getColumn(0).setPreferredWidth(200);

			JScrollPane scrollPane = new JScrollPane(table);
			contentPane.add(scrollPane, BorderLayout.CENTER);

		}

		{// add item form
			JPanel panel1 = new JPanel();
			contentPane.add(panel1, BorderLayout.WEST);

			JLabel lblItem = new JLabel("Item Name:");
			lblItem.setFont(font);

			txtItem = new JTextField();
			txtItem.setFont(font);
			txtItem.setColumns(15);

			JLabel lblPrice = new JLabel("Item Price:");
			lblPrice.setFont(font);

			txtPrice = new JTextField();
			txtPrice.setFont(font);

			btnAddItem = new JButton("Add Item");
			btnAddItem.setFont(font);
			panel1.setLayout(new GridLayout(7, 1, 0, 5));
			panel1.add(lblItem);
			panel1.add(txtItem);
			panel1.add(lblPrice);
			panel1.add(txtPrice);
			panel1.add(btnAddItem);

		}

		{// footer panel to display total price

			lblTotal = new JLabel("Total: 0.0 $");
			lblTotal.setForeground(Color.MAGENTA);
			lblTotal.setFont(font);
			lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(lblTotal, BorderLayout.SOUTH);
		}

	}

	private void createEvents() {
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				expenses = new DailyExpenses();
				FileInputStream fileStream = null;
				Scanner inFS = null;
				model.setRowCount(0);
				try {
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					Date date = dateChooser.getDate();
					String dateString = formatter.format(date);

					fileStream = new FileInputStream("src\\" + dateString + ".txt");
					inFS = new Scanner(fileStream);

					while (inFS.hasNextLine()) {
						String itemName = inFS.nextLine();
						double itemPrice = Double.parseDouble(inFS.nextLine());
						expenses.addItem(itemName, itemPrice);
						model.addRow(new Object[] { itemName, itemPrice });
					}
					lblTotal.setText("Total: " + expenses.getTotalPrice() + " $");

				} catch (FileNotFoundException e1) {
					lblTotal.setText("Total: 0.0 $");
				}
			}
		});

		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtItem.getText().isEmpty() || txtPrice.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "The Item Name or Price is Empty.", "Daily Expenses",
							JOptionPane.ERROR_MESSAGE);
				else {
					FileOutputStream fileStream = null;
					PrintWriter outFS = null;
					try {
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
						Date date = dateChooser.getDate();
						String dateString = formatter.format(date);
						fileStream = new FileOutputStream("src\\" + dateString + ".txt",
								true);
						outFS = new PrintWriter(fileStream);
						outFS.println(txtItem.getText());
						outFS.println(txtPrice.getText());
						expenses.addItem(txtItem.getText(), Double.parseDouble(txtPrice.getText()));
						model.addRow(new Object[] { txtItem.getText(), Double.parseDouble(txtPrice.getText()) });
						txtItem.setText("");
						txtPrice.setText("");
						lblTotal.setText("Total: " + expenses.getTotalPrice() + " $");
						JOptionPane.showMessageDialog(null, "The Item has been saved.", "Daily Expenses",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						outFS.close();
					}
				}
			}
		});

	}
}
