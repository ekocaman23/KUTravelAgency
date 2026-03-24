package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import travel.Flight;
import travel.Hotel;
import travel.Taxi;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionEvent;

public class MainFrameCustomTaxi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static int taxiNumber = 0;
	public static ArrayList<Taxi> searchedTaxis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameCustomTaxi frame = new MainFrameCustomTaxi();
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
	public MainFrameCustomTaxi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Taxis");
		lblNewLabel_1.setBounds(10, 10, 55, 22);
		lblNewLabel_1.setFont(new Font("Inter", Font.BOLD, 17));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Choose your options for the taxi.\r\n");
		lblNewLabel_2.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 47, 194, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCity.setBounds(10, 90, 35, 18);
		contentPane.add(lblCity);
		
		JLabel lblTaxiType = new JLabel("Taxi type");
		lblTaxiType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTaxiType.setBounds(10, 210, 69, 18);
		contentPane.add(lblTaxiType);
		
		
		
		Choice cityChoice = new Choice();
		cityChoice.setBounds(85, 90, 136, 18);
		Flight firstFlightChoice = MainFrame.chosenFlights.get(taxiNumber);
		cityChoice.add( (firstFlightChoice.getArrivalCity() != null) ? firstFlightChoice.getArrivalCity() : firstFlightChoice.getFinalArrivalCity());
		contentPane.add(cityChoice);
		
		
		
		Choice taxiTypeChoice = new Choice();
		taxiTypeChoice.setBounds(85, 210, 136, 18);
		taxiTypeChoice.add("None");
		contentPane.add(taxiTypeChoice);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String city;
				
				if (!cityChoice.getSelectedItem().equals("None")) {
					city = cityChoice.getSelectedItem();
				}
				
				else {
					city = null;
				}
				
				String taxiType;
				
				if (taxiTypeChoice.getSelectedItem().equals("None") == false) {
					taxiType = taxiTypeChoice.getSelectedItem();
				}
				
				else {
					taxiType = null;
				}
				
				
				searchedTaxis = Taxi.searchTaxi(city, taxiType);
				
				if (searchedTaxis.size() != 0) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								MainFrameCustomTaxiList newFrame = new MainFrameCustomTaxiList();
								newFrame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					dispose();
				}
				
				else {
					JOptionPane.showMessageDialog(contentPane, "No taxis found with specified options.");
				}
			}
		});
		btnNewButton.setBounds(10, 298, 194, 52);
		contentPane.add(btnNewButton);
		
		
		Set<String> taxiTypeSet = new TreeSet<>();
		
		for (Taxi taxi : Taxi.getTaxiList()) {
			if (taxi.getType() != null) {
				taxiTypeSet.add(taxi.getType());
			}
		}
		
		for (String taxiType : taxiTypeSet) {
			taxiTypeChoice.add(taxiType);
		}
	}
	
	
}
