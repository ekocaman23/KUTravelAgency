package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import travel.Flight;
import travel.Hotel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.Choice;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MainFrameCustomHotel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static int hotelNumber = 0;
	public static ArrayList<Hotel> searchedHotels;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameCustomHotel frame = new MainFrameCustomHotel();
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
	public MainFrameCustomHotel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel maximumPriceLabel = new JLabel("Maximum");
		maximumPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		maximumPriceLabel.setBounds(469, 130, 81, 18);
		maximumPriceLabel.setVisible(false);
		contentPane.add(maximumPriceLabel);
		
		JLabel minimumPriceLabel = new JLabel("Minimum");
		minimumPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		minimumPriceLabel.setBounds(469, 91, 81, 18);
		minimumPriceLabel.setVisible(false);
		contentPane.add(minimumPriceLabel);
		
		JLabel maximumDistanceValue = new JLabel("");
		maximumDistanceValue.setBounds(687, 206, 45, 13);
		contentPane.add(maximumDistanceValue);
		maximumDistanceValue.setVisible(false);
		
		JLabel minimumDistanceValue = new JLabel("");
		minimumDistanceValue.setBounds(687, 166, 45, 13);
		contentPane.add(minimumDistanceValue);
		minimumDistanceValue.setVisible(false);
		
		JLabel maximumPriceValue = new JLabel("");
		maximumPriceValue.setBounds(687, 134, 45, 13);
		contentPane.add(maximumPriceValue);
		maximumPriceValue.setVisible(false);
		
		JLabel minimumPriceValue = new JLabel("");
		minimumPriceValue.setBounds(687, 94, 45, 13);
		contentPane.add(minimumPriceValue);
		minimumPriceValue.setVisible(false);
		
		JLabel lblNewLabel_1 = new JLabel("Hotels");
		lblNewLabel_1.setBounds(10, 10, 56, 22);
		lblNewLabel_1.setFont(new Font("Inter", Font.BOLD, 17));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Choose your options for the hotel.");
		lblNewLabel_2.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 39, 194, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel nameLbl = new JLabel("Hotel name");
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nameLbl.setBounds(10, 90, 81, 18);
		contentPane.add(nameLbl);
		
		JLabel locationLbl = new JLabel("Location");
		locationLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		locationLbl.setBounds(10, 150, 69, 18);
		contentPane.add(locationLbl);
		
		JLabel roomTypeLbl = new JLabel("Room type");
		roomTypeLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		roomTypeLbl.setBounds(10, 210, 69, 18);
		contentPane.add(roomTypeLbl);
		
		Choice nameChoice = new Choice();
		nameChoice.setBounds(96, 90, 136, 18);
		nameChoice.add("None");
		contentPane.add(nameChoice);
		
		Choice locationChoice = new Choice();
		locationChoice.setBounds(96, 150, 136, 18);
		contentPane.add(locationChoice);
		Flight firstFlightChoice = MainFrame.chosenFlights.get(hotelNumber);
		locationChoice.add( (firstFlightChoice.getArrivalCity() != null) ? firstFlightChoice.getArrivalCity() : firstFlightChoice.getFinalArrivalCity()  );
		
		Choice roomTypeChoice = new Choice();
		roomTypeChoice.setBounds(96, 210, 136, 18);
		roomTypeChoice.add("None");
		contentPane.add(roomTypeChoice);
		
		
		
		JSlider minimumPriceSlider = new JSlider();
		minimumPriceSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = minimumPriceSlider.getValue();
				minimumPriceValue.setText(String.valueOf(value));
			}
		});
		
		minimumPriceSlider.setMaximum(Hotel.highestPrice());
		minimumPriceSlider.setMinimum(Hotel.lowestPrice());
		minimumPriceSlider.setBounds(524, 88, 153, 26);
		minimumPriceSlider.setVisible(false);
		contentPane.add(minimumPriceSlider);
		
		JSlider maximumPriceSlider = new JSlider();
		maximumPriceSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = maximumPriceSlider.getValue();
				maximumPriceValue.setText(String.valueOf(value));
			}
		});
		maximumPriceSlider.setMaximum(Hotel.highestPrice());
		maximumPriceSlider.setMinimum(Hotel.lowestPrice());
		maximumPriceSlider.setBounds(524, 127, 153, 26);
		maximumPriceSlider.setVisible(false);
		
		contentPane.add(maximumPriceSlider);
		
		JLabel minimumDistanceLabel = new JLabel("Minimum");
		minimumDistanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		minimumDistanceLabel.setBounds(469, 163, 81, 18);
		contentPane.add(minimumDistanceLabel);
		minimumDistanceLabel.setVisible(false);
		
		JLabel maximumDistanceLabel = new JLabel("Maximum");
		maximumDistanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		maximumDistanceLabel.setBounds(469, 203, 81, 18);
		maximumDistanceLabel.setVisible(false);
		contentPane.add(maximumDistanceLabel);
		
		JSlider minimumDistanceSlider = new JSlider();
		minimumDistanceSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = minimumDistanceSlider.getValue();
				minimumDistanceValue.setText(String.valueOf(value));
			}
		});
		
		minimumDistanceSlider.setMaximum(Hotel.highestDistance());
		minimumDistanceSlider.setMinimum(Hotel.lowestDistance());
		minimumDistanceSlider.setVisible(false);
		minimumDistanceSlider.setBounds(524, 160, 153, 26);
		contentPane.add(minimumDistanceSlider);
		
		JSlider maximumDistanceSlider = new JSlider();
		maximumDistanceSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = maximumDistanceSlider.getValue();
				maximumDistanceValue.setText(String.valueOf(value));
			}
		});
		
		maximumDistanceSlider.setMaximum(Hotel.highestDistance());
		maximumDistanceSlider.setMinimum(Hotel.lowestDistance());
		maximumDistanceSlider.setVisible(false);
		maximumDistanceSlider.setBounds(524, 200, 153, 26);
		contentPane.add(maximumDistanceSlider);
		
		JCheckBox priceCheckbox = new JCheckBox("Price per night");
		priceCheckbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (priceCheckbox.isSelected()) {
					minimumPriceLabel.setVisible(true);
					minimumPriceSlider.setVisible(true);
					minimumPriceValue.setVisible(true);
					maximumPriceLabel.setVisible(true);
					maximumPriceSlider.setVisible(true);
					maximumPriceValue.setVisible(true);
                } else {
                	minimumPriceLabel.setVisible(false);
					minimumPriceSlider.setVisible(false);
					minimumPriceValue.setVisible(false);
					maximumPriceLabel.setVisible(false);
					maximumPriceSlider.setVisible(false);
					maximumPriceValue.setVisible(false);
                }
			}
		});
		priceCheckbox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		priceCheckbox.setBounds(338, 109, 106, 21);
		contentPane.add(priceCheckbox);
		
		JCheckBox distanceCheckbox = new JCheckBox("Distance to airport");
		distanceCheckbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (distanceCheckbox.isSelected()) {
					minimumDistanceLabel.setVisible(true);
					minimumDistanceSlider.setVisible(true);
					minimumDistanceValue.setVisible(true);
					maximumDistanceLabel.setVisible(true);
					maximumDistanceSlider.setVisible(true);
					maximumDistanceValue.setVisible(true);
                } else {
                	minimumDistanceLabel.setVisible(false);
					minimumDistanceSlider.setVisible(false);
					minimumDistanceValue.setVisible(false);
					maximumDistanceLabel.setVisible(false);
					maximumDistanceSlider.setVisible(false);
					maximumDistanceValue.setVisible(false);
                }
			}
		});
		
		distanceCheckbox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		distanceCheckbox.setBounds(338, 185, 130, 21);
		contentPane.add(distanceCheckbox);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name;
				
				if (!nameChoice.getSelectedItem().equals("None")) {
					name = nameChoice.getSelectedItem();
				}
				
				else {
					name = null;
				}
				
				String location;
				
				if (locationChoice.getSelectedItem().equals("None") == false) {
					location = locationChoice.getSelectedItem();
				}
				
				else {
					location = null;
				}
				
				
				String roomType;
				
				if (roomTypeChoice.getSelectedItem().equals("None") == false) {
					roomType = roomTypeChoice.getSelectedItem();
				}
				
				else {
					roomType = null;
				}
				
				double maximumPrice;
				double minimumPrice;
				
				if (priceCheckbox.isSelected()) {
					maximumPrice = maximumPriceSlider.getValue();
					minimumPrice = minimumPriceSlider.getValue();
				}
				
				else {
					maximumPrice = Hotel.highestPrice();
					minimumPrice = Hotel.lowestPrice();
				}
				
				
				
				double[] priceInterval = new double[]{minimumPrice, maximumPrice};
				
				
				double maximumDistance;
				double minimumDistance;
				
				
				if (distanceCheckbox.isSelected()) {
					maximumDistance = maximumDistanceSlider.getValue();
					minimumDistance = minimumDistanceSlider.getValue();
				}
				
				else {
					maximumDistance = Hotel.highestDistance();
					minimumDistance = Hotel.lowestDistance();
				}
				
				
				
				double[] distanceInterval = new double[]{minimumDistance, maximumDistance};

				
				searchedHotels = Hotel.searchHotel(name, location, priceInterval, roomType, distanceInterval);
				
				
				if (searchedHotels.size() != 0) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								MainFrameCustomHotelList newFrame = new MainFrameCustomHotelList();
								newFrame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					dispose();
				}
				
				else {
					JOptionPane.showMessageDialog(contentPane, "No hotels found with specified options.");
				}
				
				
			
			}
		});
		btnNewButton.setBounds(10, 298, 194, 52);
		contentPane.add(btnNewButton);
		
		Set<String> nameSet = new TreeSet<>();
		
		for (Hotel hotel : Hotel.getHotelList()) {
			if (hotel.getName() != null) {
				nameSet.add(hotel.getName());
			}
			
			
		}
		
		for (String name : nameSet) {
			nameChoice.add(name);
		}
		
		
		Set<String> roomTypeSet = new TreeSet<>();
		
		for (Hotel hotel : Hotel.getHotelList()) {
			if (hotel.getRoomType() != null) {
				roomTypeSet.add(hotel.getRoomType());
			}
			
			
		}
		
		for (String roomType : roomTypeSet) {
			roomTypeChoice.add(roomType);
		}
	}
}
