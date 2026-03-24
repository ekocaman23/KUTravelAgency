package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exceptions.PasswordLengthException;
import exceptions.UsernameAlreadyTakenException;
import login_page.Customer;

import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.foreign.AddressLayout;

public class RegisterFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame registerFrame = new RegisterFrame();
					registerFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				String fileName = "assets/Customer_List.txt";
				Customer.saveCustomers(fileName);
				System.exit(0);
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setBounds(0, 0, 500, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel loginPageLabel = new JLabel("< Back to login page");
		loginPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new LoginFrame().setVisible(true);;
						} catch (Exception e) {
							
						}
					}
				});
				
				dispose();
			}
		});
		loginPageLabel.setForeground(Color.RED);
		loginPageLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginPageLabel.setBounds(98, 9, 404, 22);
		contentPane.add(loginPageLabel);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		nameLabel.setBounds(10, 42, 85, 22);
		contentPane.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nameField.setColumns(10);
		nameField.setBounds(10, 74, 180, 18);
		contentPane.add(nameField);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		usernameLabel.setBounds(10, 102, 85, 22);
		contentPane.add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usernameField.setColumns(10);
		usernameField.setBounds(10, 133, 180, 18);
		contentPane.add(usernameField);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setBounds(10, 192, 180, 18);
		contentPane.add(passwordField);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordLabel.setBounds(10, 161, 85, 22);
		contentPane.add(passwordLabel);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input_name = nameField.getText();
				String input_username = usernameField.getText();
				String input_password = new String(passwordField.getPassword());
				
				try {
					new Customer(input_username, input_password, input_name);
					JOptionPane.showMessageDialog(contentPane, "You have successfully registered.");
					nameField.setText(null);
					usernameField.setText(null);
					passwordField.setText(null);
					loginPageLabel.setText("< You have successfully registered! Back to login page");
					loginPageLabel.setForeground(Color.GREEN);
					System.out.println(Customer.getCustomerList());
				} catch (UsernameAlreadyTakenException e2) {
					JOptionPane.showMessageDialog(contentPane, "This username is already taken.");
					usernameField.setText(null);
					passwordField.setText(null);
				} catch (PasswordLengthException e3) {
					JOptionPane.showMessageDialog(contentPane, "The password must be at least 8 characters long.");
					passwordField.setText(null);
				}
			}
		});
		registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		registerBtn.setBounds(10, 220, 180, 33);
		contentPane.add(registerBtn);
		
		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 10, 94, 22);
		contentPane.add(lblNewLabel);
	}

}
