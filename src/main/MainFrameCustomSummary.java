package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import travel.Flight;
import travel.Hotel;
import travel.Taxi;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrameCustomSummary extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;
	private JTable table;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameCustomSummary frame = new MainFrameCustomSummary();
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
	public MainFrameCustomSummary() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Summary");
		lblNewLabel_1.setBounds(10, 10, 104, 22);
		lblNewLabel_1.setFont(new Font("Inter", Font.BOLD, 17));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Your shopping card:");
		lblNewLabel_2.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 47, 114, 13);
		contentPane.add(lblNewLabel_2);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 80, 716, 219);
		contentPane.add(tabbedPane);
		
		String[] fcolumns = {"Flight ID", "Airline", "Departure City", "Stopover City", "Arrival City", "Departure Time", "Arrival Time", "Ticket Class", "Ticket Price"};
		Object[][] fdata = new Object[MainFrame.chosenFlights.size()][9];
		
		ArrayList<Flight> flights = MainFrame.chosenFlights;
		
		MainFrame.chosenPrices[0] = 0;
		
		for (int i = 0; i < MainFrame.chosenFlights.size(); i++) {
			MainFrame.chosenPrices[0] += flights.get(i).getTicketPrice();
			fdata[i] = new Object[]{flights.get(i).getFlightID(), flights.get(i).getAirline(),flights.get(i).getDepartureCity(),flights.get(i).getStopoverCity(),(flights.get(i).getArrivalCity() != null) ? flights.get(i).getArrivalCity() : flights.get(i).getFinalArrivalCity(),
					   (flights.get(i).getDepartureTime() != null) ? flights.get(i).getDepartureTime() : flights.get(i).getLeg1DepartureTime(),(flights.get(i).getArrivalTime() != null) ? flights.get(i).getArrivalTime() : flights.get(i).getLeg2ArrivalTime(),
							flights.get(i).getTicketClass(),flights.get(i).getTicketPrice()};
		}
		
		DefaultTableModel fmodel = new DefaultTableModel(fdata, fcolumns);
		
		String[] columns = new String[1];
		Object[][] data = new Object[MainFrame.chosenHotels.size()][8];
		
		if (!AdminMainFrame.creatingNewTravel) {
			columns = new String[]{"Hotel ID","Hotel Name", "City", "Room Type", "Available Rooms", "Price per Night", "Nights to Stay", "Total Price"};
			
			ArrayList<Hotel> hotels = MainFrame.chosenHotels;
			
			MainFrame.chosenPrices[1] = 0;
			
			for (int i = 0; i < MainFrame.chosenHotels.size(); i++) {
				MainFrame.chosenPrices[1] += hotels.get(i).getPricePerNight() * MainFrame.howManyNights.get(i);
				
				data[i] = new Object[]{hotels.get(i).getHotelID(), hotels.get(i).getName(), hotels.get(i).getLocation(), hotels.get(i).getRoomType(), hotels.get(i).getAvailableRooms(),
						hotels.get(i).getPricePerNight(), MainFrame.howManyNights.get(i), hotels.get(i).getPricePerNight() * MainFrame.howManyNights.get(i) };
			}
		}
		
		else {
			columns = new String[]{"Hotel ID","Hotel Name", "City", "Room Type", "Available Rooms", "Price per Night"};
			
			ArrayList<Hotel> hotels = MainFrame.chosenHotels;
			
			MainFrame.chosenPrices[1] = 0;
			
			for (int i = 0; i < MainFrame.chosenHotels.size(); i++) {
				MainFrame.chosenPrices[1] += hotels.get(i).getPricePerNight() * MainFrame.howManyNights.get(i);
				
				data[i] = new Object[]{hotels.get(i).getHotelID(), hotels.get(i).getName(), hotels.get(i).getLocation(), hotels.get(i).getRoomType(), hotels.get(i).getAvailableRooms(),
						hotels.get(i).getPricePerNight() };
			}
		}
	
		
		
		
		DefaultTableModel model = new DefaultTableModel(data, columns);
		
		
		
		String[] tcolumns = {"Taxi ID", "City", "Type", "Available Taxis", "Fare", "Round Trip", "Total Fare"};
		Object[][] tdata = new Object[MainFrame.chosenTaxis.size()][7];
		
		ArrayList<Taxi> taxis = MainFrame.chosenTaxis;
		
		MainFrame.chosenPrices[2] = 0;
		
		for (int i = 0; i < MainFrame.chosenTaxis.size(); i++) {
			boolean theLatest = (i + 1 >= MainFrame.chosenTaxis.size());
			String fare = String.format("%.2f", taxis.get(i).calculateFare(MainFrame.chosenHotels.get(i)));
			String totalFare = (theLatest) ? String.format(Locale.US ,"%.2f", taxis.get(i).calculateFare(MainFrame.chosenHotels.get(i))) : String.format(Locale.US, "%,2f", 2*taxis.get(i).calculateFare(MainFrame.chosenHotels.get(i)));
			
			MainFrame.chosenPrices[2] += Double.parseDouble(totalFare);
			
			tdata[i] = new Object[]{taxis.get(i).getTaxiID(), taxis.get(i).getCity(), taxis.get(i).getType(), taxis.get(i).getAvailableTaxis(), fare, (!theLatest) ? "Yes" : "No", totalFare};
		}
		
		DefaultTableModel tmodel = new DefaultTableModel(tdata, tcolumns);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("Flights", null, scrollPane_2, null);
		
		table = new JTable(fmodel);
		scrollPane_2.setViewportView(table);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Hotels", null, scrollPane, null);
		
		table_1 = new JTable(model);
		scrollPane.setViewportView(table_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Taxis", null, scrollPane_1, null);
		
		table_2 = new JTable(tmodel);
		scrollPane_1.setViewportView(table_2);
		
		JButton btnBuy = new JButton("Go to payment");
		
		if (AdminMainFrame.creatingNewTravel) {
			btnBuy.setText("View the prices");
		}
		
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							MainFrameCustomSummary2 newFrame = new MainFrameCustomSummary2();
							newFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				setVisible(false);
			}
		});
		btnBuy.setBounds(10, 330, 251, 52);
		contentPane.add(btnBuy);
	}
}
