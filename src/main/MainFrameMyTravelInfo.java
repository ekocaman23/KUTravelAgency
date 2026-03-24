package main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import travel.Flight;
import travel.Hotel;
import travel.Taxi;
import travel.Travel;

import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MainFrameMyTravelInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	public static Travel yourTravel;
	public static Flight exactFlight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameMyTravelInfo frame = new MainFrameMyTravelInfo();
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
	public MainFrameMyTravelInfo() {
		setType(Type.POPUP);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYourTravel = new JLabel("Your Reservation");
		lblYourTravel.setBounds(10, 10, 176, 22);
		lblYourTravel.setFont(new Font("Inter", Font.BOLD, 17));
		contentPane.add(lblYourTravel);
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (table != null && table_1 != null && table_2 != null) {
					table.clearSelection();
	                table_1.clearSelection();
	                table_2.clearSelection();
	                
	                
				}
				
				
			}
			
		});
		tabbedPane.setBounds(10, 76, 716, 219);
		contentPane.add(tabbedPane);
		
		String[] fcolumns = {"Flight ID", "Airline", "Departure City", "Stopover City", "Arrival City", "Departure Time", "Arrival Time", "Ticket Class", "Ticket Price"};
		Object[][] fdata = new Object[yourTravel.getFlights().size()][9];
		
		ArrayList<Flight> flights = yourTravel.getFlights();
		
		for (int i = 0; i < yourTravel.getFlights().size(); i++) {
			
			fdata[i] = new Object[]{flights.get(i).getFlightID(), flights.get(i).getAirline(),flights.get(i).getDepartureCity(),flights.get(i).getStopoverCity(),(flights.get(i).getArrivalCity() != null) ? flights.get(i).getArrivalCity() : flights.get(i).getFinalArrivalCity(),
					   (flights.get(i).getDepartureTime() != null) ? flights.get(i).getDepartureTime() : flights.get(i).getLeg1DepartureTime(),(flights.get(i).getArrivalTime() != null) ? flights.get(i).getArrivalTime() : flights.get(i).getLeg2ArrivalTime(),
							flights.get(i).getTicketClass(),flights.get(i).getTicketPrice()};
		}
		
		DefaultTableModel fmodel = new DefaultTableModel(fdata, fcolumns);
		
		
		String[] columns = new String[1];
		Object[][] data = new Object[yourTravel.getHotels().size()][8];
		
		if (!LoginFrame.adminLogged && LoginFrame.loggedCustomer.getTravelHistory().contains(yourTravel)) {
			columns = new String[]{"Hotel ID","Hotel Name", "City", "Room Type", "Available Rooms", "Price per Night", "Nights to Stay", "Total Price"};
			ArrayList<Hotel> hotels = yourTravel.getHotels();
			
			for (int i = 0; i < yourTravel.getHotels().size(); i++) {
				data[i] = new Object[]{hotels.get(i).getHotelID(), hotels.get(i).getName(), hotels.get(i).getLocation(), hotels.get(i).getRoomType(), hotels.get(i).getAvailableRooms(),
						hotels.get(i).getPricePerNight(), yourTravel.getHowManyNights().get(i), hotels.get(i).getPricePerNight() * yourTravel.getHowManyNights().get(i) };
			}
		}
		
		else {
			columns = new String[]{"Hotel ID","Hotel Name", "City", "Room Type", "Available Rooms", "Price per Night"};
			ArrayList<Hotel> hotels = yourTravel.getHotels();
			
			for (int i = 0; i < yourTravel.getHotels().size(); i++) {
				data[i] = new Object[]{hotels.get(i).getHotelID(), hotels.get(i).getName(), hotels.get(i).getLocation(), hotels.get(i).getRoomType(), hotels.get(i).getAvailableRooms(),
						hotels.get(i).getPricePerNight() };
			}
			
			
		}
		
		DefaultTableModel model = new DefaultTableModel(data, columns);
		
		
		
		String[] tcolumns = {"Taxi ID", "City", "Type", "Available Taxis", "Fare", "Round Trip", "Total Fare"};
		Object[][] tdata = new Object[yourTravel.getTaxis().size()][7];
		
		ArrayList<Taxi> taxis = yourTravel.getTaxis();
		
		MainFrame.chosenPrices[2] = 0;
		
		for (int i = 0; i < yourTravel.getTaxis().size(); i++) {
			boolean theLatest = (i + 1 >= yourTravel.getTaxis().size());
			String fare = String.format("%.2f", taxis.get(i).calculateFare(yourTravel.getHotels().get(i)));
			String totalFare = (theLatest) ? String.format(Locale.US ,"%.2f", taxis.get(i).calculateFare(yourTravel.getHotels().get(i))) : String.format(Locale.US, "%,2f", 2*taxis.get(i).calculateFare(yourTravel.getHotels().get(i)));
			
			tdata[i] = new Object[]{taxis.get(i).getTaxiID(), taxis.get(i).getCity(), taxis.get(i).getType(), taxis.get(i).getAvailableTaxis(), fare, (!theLatest) ? "Yes" : "No", totalFare};
		}
		
		DefaultTableModel tmodel = new DefaultTableModel(tdata, tcolumns);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Flights", null, scrollPane, null);
		
		table = new JTable(fmodel);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Hotels", null, scrollPane_1, null);
		
		table_1 = new JTable(model);
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("Taxis", null, scrollPane_2, null);
		
		table_2 = new JTable(tmodel);
		scrollPane_2.setViewportView(table_2);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMainFrame.adminEditing = false;
				MainFrame.isReserved = false;
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new MainFrame().setVisible(true);;
							setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				setVisible(false);
			}
		});
		
		btnClose.setBounds(10, 325, 251, 52);
		contentPane.add(btnClose);
		
		JLabel lblNewLabel_2 = new JLabel("The reservation status:");
		lblNewLabel_2.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 42, 131, 13);
		contentPane.add(lblNewLabel_2);
		
		String theStatus = "";
		
		if (LoginFrame.loggedCustomer == null) {
			theStatus = "Not reserved yet";
		}
		
		else if (!LoginFrame.loggedCustomer.getTravelHistory().contains(yourTravel)) {
			theStatus = "Not reserved yet";
		}
		
		else {
			switch (LoginFrame.loggedCustomer.checkTravelReservation(yourTravel)) {
			case 1:
				theStatus = "Active";
				break;
			case 2:
				theStatus = "Cancelled";
				break;
			case 3:
				theStatus = "Expired";
				break;
		}
		}
		
		
		
		JLabel lblNewLabel_2_1 = new JLabel(theStatus);
		
		switch (theStatus) {
		case "Not reserved yet":
			lblNewLabel_2_1.setForeground(Color.BLUE);
			break;
		
		case "Active":
			lblNewLabel_2_1.setForeground(Color.GREEN);
			break;
			
		case "Cancelled":
			lblNewLabel_2_1.setForeground(Color.RED);
			break;
			
		case "Expired":
			lblNewLabel_2_1.setForeground(Color.GRAY);
		}
		
		lblNewLabel_2_1.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(150, 42, 131, 13);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnReserve = new JButton("Make reservation");
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.isReserved = false;
				
				ArrayList<Integer> howManyNights = new ArrayList<Integer>();
				double totalHotelPrice = 0;
				
				for (int i = 0; i < yourTravel.getHotels().size(); i++) {
					String message = String.format("How many nights will you be staying at %s?", yourTravel.getHotels().get(i).getLocation());
					int night = Integer.parseInt(JOptionPane.showInputDialog(message));
					howManyNights.add(night);
					totalHotelPrice += night * yourTravel.getHotels().get(i).getPricePerNight();
				}
				
				for (Travel t : LoginFrame.loggedCustomer.getTravelHistory()) {
					if (t.getTravelID() == yourTravel.getTravelID()) {
						t.setTotalHotelPrice(totalHotelPrice);
						t.setHowManyNights(howManyNights);
					}
				}
				
				yourTravel.setHowManyNights(howManyNights);
				yourTravel.setTotalHotelPrice(totalHotelPrice);
				
				if (!LoginFrame.loggedCustomer.travelIDExists(yourTravel.getTravelID())) {
					
					LoginFrame.loggedCustomer.updateTravelHistory(yourTravel);
					LoginFrame.loggedCustomer.addTravelReservation(yourTravel);
				}
				
				else {
					LoginFrame.loggedCustomer.updateTravelReservation(yourTravel, 1);
				}
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new MainFrame().setVisible(true);;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				setVisible(false);
			}
		});
		btnReserve.setBounds(284, 325, 251, 52);
		
		if (!LoginFrame.adminLogged && !MainFrame.isReserved) {
			if (!LoginFrame.loggedCustomer.travelIDExists(yourTravel.getTravelID()) || LoginFrame.loggedCustomer.checkTravelReservation(yourTravel) != 1) {
				contentPane.add(btnReserve);
			}
		}
		
		JButton btnClose_1 = new JButton("Edit");
		btnClose_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( (table.getSelectedRowCount() == 0) && (table_1.getSelectedRowCount() == 0) && (table_2.getSelectedRowCount() == 0)) {
					JOptionPane.showMessageDialog(contentPane, "Choose a row from any table to edit.");
				}
				
				else {
					if (table.getSelectedRowCount() != 0) {
	                	exactFlight = Flight.findFlightFromID(String.valueOf(fdata[table.getSelectedRow()][0]));
	                	MainFrameCustomTravel.searchedFlights = Flight.searchFlight(null, exactFlight.getDepartureCity(), (exactFlight.getArrivalCity() != null) ? exactFlight.getArrivalCity() : exactFlight.getFinalArrivalCity(), null, null, null);
	                	EventQueue.invokeLater(new Runnable() {
	    					public void run() {
	    						try {
	    							new MainFrameCustomTravelList().setVisible(true);;
	    						} catch (Exception e) {
	    							e.printStackTrace();
	    						}
	    					}
	    				});
	    				setVisible(false);
	                }
	                
	                if (table.getSelectedRowCount() != 0) {
	                	
	                }
	                
	                if (table.getSelectedRowCount() != 0) {
	                	
	                }
				}
			}
		});
		btnClose_1.setBounds(271, 325, 176, 52);
		
		
		JButton btnClose_1_1 = new JButton("Delete");
		btnClose_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Travel.getTravelList().remove(yourTravel);
				AdminMainFrame.adminEditing = false;
				setVisible(false);
			}
		});
		btnClose_1_1.setBounds(457, 325, 176, 52);
		
		if (AdminMainFrame.adminEditing) {
			contentPane.add(btnClose_1);
			contentPane.add(btnClose_1_1);
		}
		
		
		JButton btnCancelTheTravel = new JButton("Cancel the travel");
		btnCancelTheTravel.setBounds(271, 325, 251, 52);
		btnCancelTheTravel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.isReserved = false;
				LoginFrame.loggedCustomer.updateTravelReservation(yourTravel, 2);
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new MainFrame().setVisible(true);;
							setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				setVisible(false);
			}
		});
		
		if (MainFrame.isReserved) {
			if (LoginFrame.loggedCustomer.checkTravelReservation(yourTravel) == 1) {
				contentPane.add(btnCancelTheTravel);
			}
		}
		
		
	}
}
