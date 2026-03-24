package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import travel.Flight;

import java.awt.Window.Type;
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;

public class MainFrameCustomTravelList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public static Flight chosenFlight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameCustomTravelList frame = new MainFrameCustomTravelList();
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
	public MainFrameCustomTravelList() {
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Flights");
		lblNewLabel_1.setBounds(10, 10, 75, 32);
		lblNewLabel_1.setFont(new Font("Inter", Font.BOLD, 17));
		contentPane.add(lblNewLabel_1);
		
		int allTickets = Flight.getFlightList().size();
		int foundTickets = MainFrameCustomTravel.searchedFlights.size();
		
		
		String labelString = String.format("Matching flight tickets: (%d/%d)", foundTickets, allTickets);
		JLabel lblNewLabel_2 = new JLabel(labelString);
		lblNewLabel_2.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 54, 194, 13);
		contentPane.add(lblNewLabel_2);
		
		String[] columns = {"Flight ID", "Airline", "Departure City", "Stopover City", "Arrival City", "Departure Time", "Arrival Time", "Ticket Class", "Ticket Price"};
		Object[][] data = new Object[MainFrameCustomTravel.searchedFlights.size()][9];
		
		ArrayList<Flight> flights = MainFrameCustomTravel.searchedFlights;
		
		for (int i = 0; i < MainFrameCustomTravel.searchedFlights.size(); i++) {
			data[i] = new Object[]{flights.get(i).getFlightID(), flights.get(i).getAirline(),flights.get(i).getDepartureCity(),flights.get(i).getStopoverCity(),(flights.get(i).getArrivalCity() != null) ? flights.get(i).getArrivalCity() : flights.get(i).getFinalArrivalCity(),
					   (flights.get(i).getDepartureTime() != null) ? flights.get(i).getDepartureTime() : flights.get(i).getLeg1DepartureTime(),(flights.get(i).getArrivalTime() != null) ? flights.get(i).getArrivalTime() : flights.get(i).getLeg2ArrivalTime(),
							flights.get(i).getTicketClass(),flights.get(i).getTicketPrice()};
		}
		
		DefaultTableModel model = new DefaultTableModel(data, columns);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 716, 195);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2_1 = new JLabel("Press a row to choose a flight ticket.");
		lblNewLabel_2_1.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(10, 282, 236, 13);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnNewButton = new JButton("Change the flight ticket");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = MainFrameMyTravelInfo.yourTravel.getFlights().indexOf(MainFrameMyTravelInfo.exactFlight);
				MainFrameMyTravelInfo.yourTravel.getFlights().remove(index);
				
				int chosenFlightRow = table.getSelectedRow();
				chosenFlight = Flight.findFlightFromID((String)data[chosenFlightRow][0]);
				
				MainFrameMyTravelInfo.yourTravel.getFlights().add(index, chosenFlight);
				chosenFlight = null;
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							MainFrameMyTravelInfo newFrame = new MainFrameMyTravelInfo();
							newFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 337, 251, 52);
		
		
		JButton btnChoose = new JButton("Buy and search an another ticket");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int chosenFlightRow = table.getSelectedRow();
				chosenFlight = Flight.findFlightFromID((String)data[chosenFlightRow][0]);
				MainFrame.chosenFlights.add(chosenFlight);
				
				// LocalTime[] localTimeInterval = { (chosenFlight.getArrivalTime() != null ) ? chosenFlight.getArrivalTime() : chosenFlight.getLeg2ArrivalTime() , LocalTime.MAX}; 
				
				MainFrameCustomTravel.searchedFlights = Flight.searchFlight(null, (chosenFlight.getArrivalCity() != null) ? chosenFlight.getArrivalCity() : chosenFlight.getFinalArrivalCity(), null, null, null, null);
				
				try {
					MainFrameCustomTravel newFrame = new MainFrameCustomTravel();
					newFrame.setVisible(true);
				} catch (Exception e5) {
					e5.printStackTrace();
				}
				
				setVisible(false);
			}
		});
		btnChoose.setBounds(10, 338, 251, 52);
		
		
		JButton btnChooseAndGo = new JButton("Buy and go to rent a hotel room");
		btnChooseAndGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(contentPane, "At least 1 flight ticket option must be selected.");
				}
				
				else {
					int chosenFlightRow = table.getSelectedRow();
					chosenFlight = Flight.findFlightFromID((String)data[chosenFlightRow][0]);
					MainFrame.chosenFlights.add(chosenFlight);
					
					MainFrameCustomTravelList.chosenFlight = null;
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								MainFrameCustomHotel newFrame = new MainFrameCustomHotel();
								newFrame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					dispose();
				}
				
				
				
			}
		});
		btnChooseAndGo.setBounds(271, 338, 251, 52);
		
		
		if (!AdminMainFrame.adminEditing) {
			contentPane.add(btnChoose);
			contentPane.add(btnChooseAndGo);
		}
		
		else {
			contentPane.add(btnNewButton);
		}
	}
}
