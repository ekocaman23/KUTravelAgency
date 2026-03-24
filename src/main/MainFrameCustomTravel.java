package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import travel.Flight;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Choice;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import java.awt.Window.Type;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;

import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MainFrameCustomTravel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static ArrayList<Flight> searchedFlights;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameCustomTravel frame = new MainFrameCustomTravel();
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
	public MainFrameCustomTravel() {
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel maximumTicketPriceLabel = new JLabel("");
		maximumTicketPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		maximumTicketPriceLabel.setBounds(688, 130, 47, 18);
		contentPane.add(maximumTicketPriceLabel);
		
		JLabel minimumTicketPriceLabel = new JLabel("");
		minimumTicketPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		minimumTicketPriceLabel.setBounds(688, 90, 47, 18);
		contentPane.add(minimumTicketPriceLabel);
		
		JLabel airlineLabel = new JLabel("Airline");
		airlineLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		airlineLabel.setBounds(10, 90, 81, 18);
		contentPane.add(airlineLabel);
		
		Choice departureChoice = new Choice();
		
		departureChoice.setBounds(112, 130, 136, 28);
		contentPane.add(departureChoice);
		
		if (MainFrameCustomTravelList.chosenFlight == null) {
			Set<String> departureSet = new TreeSet<>();
			
			for (Flight flight : Flight.getFlightList()) {
				if (flight.getDepartureCity() != null) {
					departureSet.add(flight.getDepartureCity());
				}
				
				
			}
			
			departureChoice.add("None");
			
			for (String departure : departureSet) {
				departureChoice.add(departure);
			}
			
			
		}
		
		else {
			departureChoice.add( (MainFrameCustomTravelList.chosenFlight.getArrivalCity() != null) ? MainFrameCustomTravelList.chosenFlight.getArrivalCity() : MainFrameCustomTravelList.chosenFlight.getFinalArrivalCity());
		}
		
		Choice arrivalChoice = new Choice();
		arrivalChoice.add("None");
		arrivalChoice.setBounds(112, 170, 136, 18);
		contentPane.add(arrivalChoice);
		
		Choice stopoverChoice = new Choice();
		stopoverChoice.add("None");
		stopoverChoice.setBounds(112, 210, 136, 18);
		contentPane.add(stopoverChoice);
		
		Choice airlineChoice = new Choice();
		airlineChoice.add("None");
		airlineChoice.setBounds(112, 90, 136, 18);
		contentPane.add(airlineChoice);
		
		int lowestTicketPrice = Flight.lowestTicketPrice();
		int highestTicketPrice = Flight.highestTicketPrice();
		
		JSlider minimumTicketPriceSlider = new JSlider(lowestTicketPrice, highestTicketPrice);
		minimumTicketPriceSlider.setValue(lowestTicketPrice);
		minimumTicketPriceSlider.setMaximum(Flight.highestTicketPrice());
		minimumTicketPriceSlider.setMinimum(Flight.lowestTicketPrice());
		minimumTicketPriceSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = minimumTicketPriceSlider.getValue();
				minimumTicketPriceLabel.setText(String.valueOf(value));
			}
		});
		
		minimumTicketPriceSlider.setBounds(543, 90, 136, 18);
		contentPane.add(minimumTicketPriceSlider);
		minimumTicketPriceSlider.setVisible(false);
		
		JLabel lblNewLabel_1 = new JLabel("Flights");
		lblNewLabel_1.setFont(new Font("Inter", Font.BOLD, 17));
		lblNewLabel_1.setBounds(10, 10, 75, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Choose your options for the flight.");
		lblNewLabel_2.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 54, 194, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel departureCityLabel = new JLabel("Departure city");
		departureCityLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		departureCityLabel.setBounds(10, 130, 169, 18);
		contentPane.add(departureCityLabel);
		
		JLabel arrivalCityLabel = new JLabel("Arrival city");
		arrivalCityLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		arrivalCityLabel.setBounds(10, 170, 169, 18);
		contentPane.add(arrivalCityLabel);
		
		JLabel stopoverCityLabel = new JLabel("Stopover city");
		stopoverCityLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		stopoverCityLabel.setBounds(10, 210, 169, 18);
		contentPane.add(stopoverCityLabel);
		
		JLabel miniTicketPriceLabel = new JLabel("Minimum");
		miniTicketPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		miniTicketPriceLabel.setBounds(490, 90, 47, 18);
		contentPane.add(miniTicketPriceLabel);
		miniTicketPriceLabel.setVisible(false);
		
		JLabel maxiTicketPriceLabel = new JLabel("Maximum");
		maxiTicketPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		maxiTicketPriceLabel.setBounds(490, 130, 47, 18);
		contentPane.add(maxiTicketPriceLabel);
		maxiTicketPriceLabel.setVisible(false);
		
		JSlider maximumTicketPriceSlider = new JSlider();
		maximumTicketPriceSlider.setValue(highestTicketPrice);
		maximumTicketPriceSlider.setMaximum(Flight.highestTicketPrice());
		maximumTicketPriceSlider.setMinimum(Flight.lowestTicketPrice());
		maximumTicketPriceSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = maximumTicketPriceSlider.getValue();
				maximumTicketPriceLabel.setText(String.valueOf(value));
			}
		});
		maximumTicketPriceSlider.setBounds(543, 130, 136, 18);
		contentPane.add(maximumTicketPriceSlider);
		maximumTicketPriceSlider.setVisible(false);
		
		JLabel lblDepartureTimeStart = new JLabel("Start");
		lblDepartureTimeStart.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDepartureTimeStart.setBounds(523, 170, 34, 18);
		contentPane.add(lblDepartureTimeStart);
		lblDepartureTimeStart.setVisible(false);
		
		JLabel lblDepartureTimeEnd = new JLabel("End");
		lblDepartureTimeEnd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDepartureTimeEnd.setBounds(523, 210, 28, 18);
		contentPane.add(lblDepartureTimeEnd);
		lblDepartureTimeEnd.setVisible(false);
		
		String[] hours = new String[24];
        for (int i = 0; i < 24; i++) {
            hours[i] = String.format("%02d", i); // 00-23
        }
        
        String[] minutes = {"00", "15", "30", "45"};
		
		JComboBox startComboHour = new JComboBox(hours);
		startComboHour.setBounds(574, 170, 40, 21);
		contentPane.add(startComboHour);
		startComboHour.setVisible(false);
		
		JComboBox startComboMinute = new JComboBox(minutes);
		startComboMinute.setBounds(628, 170, 40, 21);
		contentPane.add(startComboMinute);
		startComboMinute.setVisible(false);
		
		JComboBox endComboHour = new JComboBox(hours);
		endComboHour.setBounds(574, 210, 40, 21);
		contentPane.add(endComboHour);
		endComboHour.setVisible(false);
		
		JComboBox endComboMinute = new JComboBox(minutes);
		endComboMinute.setBounds(628, 210, 40, 21);
		contentPane.add(endComboMinute);
		endComboMinute.setVisible(false);
		
		JCheckBox departureCheckbox = new JCheckBox("Departure time");
		departureCheckbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (departureCheckbox.isSelected()) {
					startComboHour.setVisible(true);
					startComboMinute.setVisible(true);
					endComboHour.setVisible(true);
					endComboMinute.setVisible(true);
					lblDepartureTimeStart.setVisible(true);
					lblDepartureTimeEnd.setVisible(true);
                } else {
                	startComboHour.setVisible(false);
					startComboMinute.setVisible(false);
					endComboHour.setVisible(false);
					endComboMinute.setVisible(false); 
					lblDepartureTimeStart.setVisible(false);
					lblDepartureTimeEnd.setVisible(false);
                }
			}
		});
		departureCheckbox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		departureCheckbox.setBounds(388, 185, 106, 21);
		contentPane.add(departureCheckbox);
		
		JCheckBox ticketPriceCheckbox = new JCheckBox("Ticket price");
		ticketPriceCheckbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ticketPriceCheckbox.isSelected()) {
					miniTicketPriceLabel.setVisible(true);
					minimumTicketPriceSlider.setVisible(true);
					maxiTicketPriceLabel.setVisible(true);
					maximumTicketPriceSlider.setVisible(true);
					minimumTicketPriceLabel.setVisible(true);
					maximumTicketPriceLabel.setVisible(true);
                } else {
                	miniTicketPriceLabel.setVisible(false);
					minimumTicketPriceSlider.setVisible(false);
					maxiTicketPriceLabel.setVisible(false);
					maximumTicketPriceSlider.setVisible(false);
					minimumTicketPriceLabel.setVisible(false);
					maximumTicketPriceLabel.setVisible(false);
                }
			}
		});
		
		ticketPriceCheckbox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ticketPriceCheckbox.setBounds(388, 109, 106, 21);
		contentPane.add(ticketPriceCheckbox);
		
		
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String airline;
				
				if (!airlineChoice.getSelectedItem().equals("None")) {
					airline = airlineChoice.getSelectedItem();
				}
				
				else {
					airline = null;
				}
				
				String departureCity;
				
				if (departureChoice.getSelectedItem().equals("None") == false) {
					departureCity = departureChoice.getSelectedItem();
				}
				
				else {
					departureCity = null;
				}
				
				
				String arrivalCity;
				
				if (arrivalChoice.getSelectedItem().equals("None") == false) {
					arrivalCity = arrivalChoice.getSelectedItem();
				}
				
				else {
					arrivalCity = null;
				}
				
				System.out.println(arrivalCity);
				
				String stopoverCity;
				
				if (stopoverChoice.getSelectedItem().equals("None") == false) {
					stopoverCity = stopoverChoice.getSelectedItem();
				}
				
				else {
					stopoverCity = null;
				}
				
				double maximumPrice;
				double minimumPrice;
				
				if (ticketPriceCheckbox.isSelected()) {
					maximumPrice = maximumTicketPriceSlider.getValue();
					minimumPrice = minimumTicketPriceSlider.getValue();
				}
				
				else {
					maximumPrice = highestTicketPrice;
					minimumPrice = lowestTicketPrice;
				}
				
				
				
				double[] priceInterval = new double[]{minimumPrice, maximumPrice};
				
				
				LocalTime[] departureInterval;
				
				
				if (departureCheckbox.isSelected()) {
					String startHour = (String) startComboHour.getSelectedItem();
					String startMinute = (String) startComboMinute.getSelectedItem();
					String endHour = (String) endComboHour.getSelectedItem();
					String endMinute = (String) endComboMinute.getSelectedItem();
					
					String startTime = startHour + ":" + startMinute;
					String endTime = endHour + ":" +endMinute;
					
					departureInterval = new LocalTime[]{LocalTime.parse(startTime), LocalTime.parse(endTime)};
				}
				
				else {
					departureInterval = null;
				}
				
				
				searchedFlights = Flight.searchFlight(airline, departureCity, arrivalCity, departureInterval, priceInterval, stopoverCity);
				
				
				if (searchedFlights.size() != 0) {
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
					
					dispose();
				}
				
				else {
					JOptionPane.showMessageDialog(contentPane, "No flight tickets found with specified options.");
				}
				
				
			
				
			}
			
		});
		
		
		
		
		btnNewButton.setBounds(10, 298, 194, 52);
		contentPane.add(btnNewButton);
		
		
		
		Set<String> airlineSet = new TreeSet<>();
		
		for (Flight flight : Flight.getFlightList()) {
			if (flight.getAirline() != null) {
				airlineSet.add(flight.getAirline());
			}
			
			
		}
		
		for (String airline : airlineSet) {
			airlineChoice.add(airline);
		}
		
		
		
		
		
		
		Set<String> arrivalSet = new TreeSet<>();
		
		for (Flight flight : Flight.getFlightList()) {
			if (flight.getArrivalCity() != null) {
				arrivalSet.add(flight.getArrivalCity());
			}
			
			
		}
		
		for (String arrival : arrivalSet) {
			arrivalChoice.add(arrival);
		}
		
		
		
		
		
		
		Set<String> stopoverSet = new TreeSet<>();
		
		for (Flight flight : Flight.getFlightList()) {
			if (flight.getStopoverCity() != null) {
				stopoverSet.add(flight.getStopoverCity());
			}
			
			
		}
		
		for (String stopover : stopoverSet) {
			stopoverChoice.add(stopover);
		}
		
	}
}
