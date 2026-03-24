package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import login_page.Customer;
import travel.Flight;
import travel.Travel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMainFrameShowSpecificReservations extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static Customer customer;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainFrameShowSpecificReservations frame = new AdminMainFrameShowSpecificReservations();
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
	public AdminMainFrameShowSpecificReservations() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReservations = new JLabel("Reservations");
		lblReservations.setFont(new Font("Inter", Font.BOLD, 17));
		lblReservations.setBounds(10, 10, 156, 22);
		contentPane.add(lblReservations);
		
		JLabel lblNewLabel_2 = new JLabel( String.format("The reservations made by %s:", customer.getUsername())   );
		lblNewLabel_2.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 42, 405, 13);
		contentPane.add(lblNewLabel_2);
		
		String[] columns = {"Username", "Travel ID", "Status", "Travel Locations"};
		Object[][] data = new Object[customer.getTravelHistory().size()][4];
		
		for (int i = 0; i < customer.getTravelHistory().size(); i++) {
			String travelLocations = customer.getTravelHistory().get(i).getFlights().get(0).getDepartureCity() + " > ";
			
			for (Flight flight : customer.getTravelHistory().get(i).getFlights()) {
				if (flight.getArrivalCity() != null) {
					if ( customer.getTravelHistory().get(i).getFlights().indexOf(flight) + 1  >= customer.getTravelHistory().get(i).getFlights().size()) {
						travelLocations += flight.getArrivalCity();
					}
					
					else {
						travelLocations += flight.getArrivalCity() + " > ";
					}
					
				}
				
				else {
					if (customer.getTravelHistory().get(i).getFlights().indexOf(flight) + 1 >= customer.getTravelHistory().get(i).getFlights().size()) {
						travelLocations += flight.getFinalArrivalCity();
					}
					
					else {
						travelLocations += flight.getFinalArrivalCity() + " > ";
					}
					
				}
			}
			
			data[i] = new Object[]{customer.getUsername(),customer.getTravelHistory().get(i).getTravelID(),customer.checkTravelReservation(customer.getTravelHistory().get(i)),travelLocations};
		}
		
		DefaultTableModel model = new DefaultTableModel(data, columns);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 716, 194);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JButton btnClose = new JButton("Show info");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(contentPane, "Choose a row to show info about a reservation.");
				}
				else {
					int chosenTravelRow = table.getSelectedRow();
					
					AdminMainFrameShowReservationsInfo.yourTravel = Travel.findTravelFromID( (int) data[chosenTravelRow][1] );
					AdminMainFrameShowReservationsInfo.username = String.valueOf(data[chosenTravelRow][0]);
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								new AdminMainFrameShowReservationsInfo().setVisible(true);;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					setVisible(false);
				}
			}
		});
		btnClose.setBounds(10, 306, 251, 52);
		contentPane.add(btnClose);
		
		JButton btnCreateANew = new JButton("Create a new reservation");
		btnCreateANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame.loggedCustomer = customer;
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new MainFrameCustomTravel().setVisible(true);;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnCreateANew.setBounds(276, 306, 251, 52);
		contentPane.add(btnCreateANew);
	}
}
