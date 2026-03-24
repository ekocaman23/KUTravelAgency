package main;

import java.awt.Choice;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login_page.Customer;
import travel.Taxi;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMainFrameShowSpecific extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainFrameShowSpecific frame = new AdminMainFrameShowSpecific();
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
	public AdminMainFrameShowSpecific() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 400, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAllTravels = new JLabel("Reservations");
		lblAllTravels.setBounds(10, 10, 135, 22);
		lblAllTravels.setFont(new Font("Inter", Font.BOLD, 17));
		contentPane.add(lblAllTravels);
		
		JLabel lblNewLabel_2 = new JLabel("Choose an username to check reservations.");
		lblNewLabel_2.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 42, 250, 13);
		contentPane.add(lblNewLabel_2);
		
		Choice usernameChoice = new Choice();
		usernameChoice.setBounds(10, 65, 179, 21);
		contentPane.add(usernameChoice);
		
		JButton btnClose = new JButton("Show reservations");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AdminMainFrameShowSpecificReservations.customer = Customer.findCustomerFromUsername(usernameChoice.getSelectedItem());
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
		});
		btnClose.setBounds(10, 127, 251, 52);
		contentPane.add(btnClose);
		
		Set<String> usernameSet = new TreeSet<>();
		
		for (Customer customer : Customer.getCustomerList()) {
			usernameSet.add(customer.getUsername());
		}
		
		for (String username : usernameSet) {
			usernameChoice.add(username);
		}
	}
}
