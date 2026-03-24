package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import login_page.Customer;
import travel.Travel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainFrameCustomSummary2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameCustomSummary2 frame = new MainFrameCustomSummary2();
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
	public MainFrameCustomSummary2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Summary");
		lblNewLabel_1.setBounds(10, 10, 95, 22);
		lblNewLabel_1.setFont(new Font("Inter", Font.BOLD, 17));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Total payment for the services you will purchase:");
		lblNewLabel_2.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 47, 292, 13);
		contentPane.add(lblNewLabel_2);
		
		
		String[] columns = {"Total flight payments", "Total hotel payments", "Total taxi payments", " Total payment"};
		Object[][] data = new Object[1][4];
		
		if (AdminMainFrame.creatingNewTravel) {
			data[0] = new Object[]{MainFrame.chosenPrices[0], 0, MainFrame.chosenPrices[2], String.format("%.2f",   MainFrame.chosenPrices[0] + MainFrame.chosenPrices[2])};
		}
		
		else {
			data[0] = new Object[]{MainFrame.chosenPrices[0], MainFrame.chosenPrices[1], MainFrame.chosenPrices[2], String.format("%.2f",   MainFrame.chosenPrices[0] + MainFrame.chosenPrices[1] + MainFrame.chosenPrices[2])};
		}
		
		DefaultTableModel model = new DefaultTableModel(data, columns);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 445, 42);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JButton btnConfirmThePayment = new JButton("Confirm the payment and order");
		
		if (LoginFrame.adminLogged) {
			btnConfirmThePayment.setText("Create the travel package");
		}
		
		btnConfirmThePayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Travel theTravel = new Travel("Example", MainFrame.chosenFlights, MainFrame.chosenHotels, MainFrame.chosenTaxis);
				
				MainFrame.chosenFlights = new ArrayList<>();
				MainFrame.chosenHotels = new ArrayList<>();
				MainFrame.chosenTaxis = new ArrayList<>();
				
				theTravel.setTotalFlightPrice(MainFrame.chosenPrices[0]);
				
				if (AdminMainFrame.creatingNewTravel) {
					theTravel.setTotalHotelPrice(0);
				}
				else {
					theTravel.setTotalHotelPrice(MainFrame.chosenPrices[1]);
				}
				
				theTravel.setTotalTaxiPrice(MainFrame.chosenPrices[2]);
				
				theTravel.setHowManyNights(MainFrame.howManyNights);
				MainFrame.howManyNights = new ArrayList<>();
				
				if (AdminMainFrame.creatingNewTravel) {
					Travel.getTravelList().add(theTravel);
				}
				
				else {
					if (LoginFrame.adminLogged) {
						AdminMainFrameShowSpecificReservations.customer.updateTravelHistory(theTravel);
						AdminMainFrameShowSpecificReservations.customer.addTravelReservation(theTravel);
						AdminMainFrameShowSpecificReservations.customer.updateAllTravelTypes();
					}
					else {
						LoginFrame.loggedCustomer.updateTravelHistory(theTravel);
						LoginFrame.loggedCustomer.addTravelReservation(theTravel);
					}
					
				}
				
				
				
				if (AdminMainFrame.creatingNewTravel) {
					AdminMainFrame.creatingNewTravel = false;
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								AdminMainFrame newFrame = new AdminMainFrame();
								newFrame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
				
				else {
					if (LoginFrame.adminLogged) {
						setVisible(false);
					}
					
					else {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									MainFrame newFrame = new MainFrame();
									newFrame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
					
				}
				
				
				setVisible(false);
			}
		});
		btnConfirmThePayment.setBounds(10, 181, 251, 52);
		contentPane.add(btnConfirmThePayment);
		
		JLabel lblNewLabel_2_1 = new JLabel("*Total hotel payments depend on the number of days the user will stay.");
		lblNewLabel_2_1.setForeground(Color.RED);
		lblNewLabel_2_1.setFont(new Font("Inter", Font.PLAIN, 9));
		lblNewLabel_2_1.setBounds(10, 142, 319, 13);
		
		if (AdminMainFrame.creatingNewTravel) {
			contentPane.add(lblNewLabel_2_1);
		}
		
	}
}
