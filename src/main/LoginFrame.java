package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login_page.Customer;
import travel.Travel;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nicknameField;
	private JPasswordField pwdTheadmin;
	static boolean loginSuccess = false;
	static boolean closeOperation = false;
	public static Customer loggedCustomer;
	public static boolean adminLogged = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
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
		contentPane.setBackground(new Color(240, 240, 240));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nicknameField = new JTextField();
		nicknameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nicknameField.setBounds(10, 74, 180, 18);
		contentPane.add(nicknameField);
		nicknameField.setColumns(10);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		usernameLabel.setBounds(10, 42, 85, 22);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordLabel.setBounds(10, 102, 85, 22);
		contentPane.add(passwordLabel);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				
				dispose();
			}
		});
		registerBtn.setBounds(213, 173, 194, 52);
		contentPane.add(registerBtn);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input_nickname = nicknameField.getText();
				String input_password = new String(pwdTheadmin.getPassword());
				
				ArrayList<Object> myCustomer = Customer.login(input_nickname, input_password);
				int loginStatus = (int) myCustomer.get(0);
				loggedCustomer = (Customer) myCustomer.get(1);
				
				switch(loginStatus) {
				case 1:
					JOptionPane.showMessageDialog(contentPane, "You have successfully logged in.");
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								new MainFrame().setVisible(true);;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					dispose();
					break;
				case 2:
					JOptionPane.showMessageDialog(contentPane, "Your username/password is wrong.");
					break;
				case 3:
					JOptionPane.showMessageDialog(contentPane, "No user with the specified username was found.");
					break;
				
				case 4:
					JOptionPane.showMessageDialog(contentPane, "Administrator login successful.");
					adminLogged = true;
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								new AdminMainFrame().setVisible(true);;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					dispose();
					break;
				}
				
				pwdTheadmin.setText(null);
			}
		});
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		loginBtn.setBounds(10, 173, 194, 52);
		contentPane.add(loginBtn);
		
		pwdTheadmin = new JPasswordField();
		pwdTheadmin.setToolTipText("");
		pwdTheadmin.setHorizontalAlignment(SwingConstants.LEFT);
		pwdTheadmin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pwdTheadmin.setBounds(10, 131, 180, 18);
		contentPane.add(pwdTheadmin);
		
		JLabel lblNewLabel = new JLabel("Log-in");
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 10, 94, 22);
		contentPane.add(lblNewLabel);
	}
}
