package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import travel.Flight;
import travel.Hotel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrameCustomHotelList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JPanel contentPane;
	public static Hotel chosenHotel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameCustomHotelList frame = new MainFrameCustomHotelList();
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
	public MainFrameCustomHotelList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Hotels");
		lblNewLabel_1.setFont(new Font("Inter", Font.BOLD, 17));
		lblNewLabel_1.setBounds(10, 10, 75, 32);
		contentPane.add(lblNewLabel_1);
		
		int allHotels = Hotel.getHotelList().size();
		int foundHotels = MainFrameCustomHotel.searchedHotels.size();
		
		String labelString = String.format("Matching hotels: (%d/%d)", foundHotels, allHotels);
		JLabel lblNewLabel_2 = new JLabel(labelString);
		lblNewLabel_2.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 50, 194, 13);
		contentPane.add(lblNewLabel_2);
		
		
		String[] columns = {"Hotel ID","Hotel Name", "City", "Room Type", "Available Rooms", "Price per Night", "Distance to Airport"};
		Object[][] data = new Object[MainFrameCustomHotel.searchedHotels.size()][7];
		
		ArrayList<Hotel> hotels = MainFrameCustomHotel.searchedHotels;
		
		for (int i = 0; i < MainFrameCustomHotel.searchedHotels.size(); i++) {
			data[i] = new Object[]{hotels.get(i).getHotelID(), hotels.get(i).getName(), hotels.get(i).getLocation(), hotels.get(i).getRoomType(), hotels.get(i).getAvailableRooms(),
					hotels.get(i).getPricePerNight(), hotels.get(i).getDistanceToAirport()};
		}
		
		DefaultTableModel model = new DefaultTableModel(data, columns);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 716, 195);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2_1 = new JLabel("Press a row to choose a hotel.\r\n");
		lblNewLabel_2_1.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(10, 290, 236, 13);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnChoose = new JButton("Rent the room");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(contentPane, "At least 1 hotel option must be selected.");
				}
				
				else {
					int chosenHotelRow = table.getSelectedRow();
					chosenHotel = Hotel.findHotelFromID( (int) data[chosenHotelRow][0]);
					MainFrame.chosenHotels.add(chosenHotel);
					
					if (AdminMainFrame.creatingNewTravel) {
						int night = 1;
						MainFrame.howManyNights.add(night);
					}
					
					else {
						String message = String.format("How many nights will you be staying at %s?", chosenHotel.getLocation());
						int night = Integer.parseInt(JOptionPane.showInputDialog(message));
						MainFrame.howManyNights.add(night);
					}
					
					
					// LocalTime[] localTimeInterval = { (chosenFlight.getArrivalTime() != null ) ? chosenFlight.getArrivalTime() : chosenFlight.getLeg2ArrivalTime() , LocalTime.MAX}; 
					
					MainFrameCustomHotel.hotelNumber++;
					
					if (MainFrameCustomHotel.hotelNumber >= MainFrame.chosenFlights.size()) {
						MainFrameCustomHotel.hotelNumber = 0;
						
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									MainFrameCustomTaxi newFrame = new MainFrameCustomTaxi();
									newFrame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
					}
					
					else {
						try {
							MainFrameCustomHotel newFrame = new MainFrameCustomHotel();
							newFrame.setVisible(true);
						} catch (Exception e5) {
							e5.printStackTrace();
						}
					}
					
					
					
					dispose();
					
				}
				
				
			}
		});
		btnChoose.setBounds(10, 310, 251, 52);
		contentPane.add(btnChoose);
	}
}
