package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login_page.Customer;
import travel.Travel;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static boolean adminEditing = false;
	public static boolean creatingNewTravel = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainFrame frame = new AdminMainFrame();
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
	public AdminMainFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				String travelFileName = "assets/Travel_List.txt";
				Travel.saveTravels(travelFileName);
				
				String fileName = "assets/Customer_List.txt";
				Customer.saveCustomers(fileName);
				
				System.exit(0);
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setBounds(100, 100, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setBounds(10, 10, 97, 23);
		lblMainMenu.setFont(new Font("Inter", Font.BOLD, 18));
		contentPane.add(lblMainMenu);
		
		JLabel lblLogout = new JLabel("Logout");
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginFrame.adminLogged = false;
				adminEditing = false;
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
		lblLogout.setOpaque(true);
		lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("Inter", Font.BOLD, 15));
		lblLogout.setBackground(Color.BLACK);
		lblLogout.setBounds(1104, 10, 61, 23);
		contentPane.add(lblLogout);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				creatingNewTravel = true;
				
				AdminMainFrame.this.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new MainFrameCustomTravel().setVisible(true);;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(30, 62, 98));
		panel_1.setBounds(10, 85, 180, 180);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Create a new");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Inter", Font.BOLD, 12));
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(0, 54, 178, 47);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("travel package");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Inter", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(0, 69, 178, 47);
		panel_1.add(lblNewLabel_2_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminEditing = true;
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new AdminMainFrameShowTravels().setVisible(true);;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(30, 62, 98));
		panel_1_1.setBounds(215, 85, 180, 180);
		contentPane.add(panel_1_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("View/edit/delete an existing");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Inter", Font.BOLD, 12));
		lblNewLabel_2_2.setBackground(Color.WHITE);
		lblNewLabel_2_2.setBounds(3, 54, 178, 47);
		panel_1_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("travel package");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setFont(new Font("Inter", Font.BOLD, 12));
		lblNewLabel_2_1_1.setBounds(0, 69, 178, 47);
		panel_1_1.add(lblNewLabel_2_1_1);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new AdminMainFrameShowReservations().setVisible(true);;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel_1_2.setLayout(null);
		panel_1_2.setBackground(new Color(30, 62, 98));
		panel_1_2.setBounds(609, 87, 180, 180);
		contentPane.add(panel_1_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("View all reservations");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setFont(new Font("Inter", Font.BOLD, 12));
		lblNewLabel_2_3.setBackground(Color.WHITE);
		lblNewLabel_2_3.setBounds(0, 62, 178, 47);
		panel_1_2.add(lblNewLabel_2_3);
		
		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new AdminMainFrameShowSpecific().setVisible(true);;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel_1_2_1.setLayout(null);
		panel_1_2_1.setBackground(new Color(30, 62, 98));
		panel_1_2_1.setBounds(819, 87, 180, 180);
		contentPane.add(panel_1_2_1);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("View specified user's ");
		lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3_1.setForeground(Color.WHITE);
		lblNewLabel_2_3_1.setFont(new Font("Inter", Font.BOLD, 12));
		lblNewLabel_2_3_1.setBackground(Color.WHITE);
		lblNewLabel_2_3_1.setBounds(0, 58, 178, 47);
		panel_1_2_1.add(lblNewLabel_2_3_1);
		
		JLabel lblNewLabel_2_3_1_1 = new JLabel("reservations");
		lblNewLabel_2_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_3_1_1.setFont(new Font("Inter", Font.BOLD, 12));
		lblNewLabel_2_3_1_1.setBackground(Color.WHITE);
		lblNewLabel_2_3_1_1.setBounds(0, 73, 178, 47);
		panel_1_2_1.add(lblNewLabel_2_3_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Pre-made travel packages >");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Inter", Font.BOLD, 18));
		lblNewLabel_1.setBackground(new Color(255, 101, 0));
		lblNewLabel_1.setBounds(10, 54, 300, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Travel reservations >");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Inter", Font.BOLD, 18));
		lblNewLabel_1_1.setBackground(new Color(255, 101, 0));
		lblNewLabel_1_1.setBounds(609, 52, 300, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Statistics >");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setFont(new Font("Inter", Font.BOLD, 18));
		lblNewLabel_1_2.setBackground(new Color(255, 101, 0));
		lblNewLabel_1_2.setBounds(10, 334, 300, 23);
		contentPane.add(lblNewLabel_1_2);
		
		JPanel panel_1_3 = new JPanel();
		panel_1_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new AdminMainFrameShowStats().setVisible(true);;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel_1_3.setLayout(null);
		panel_1_3.setBackground(new Color(30, 62, 98));
		panel_1_3.setBounds(10, 369, 180, 180);
		contentPane.add(panel_1_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("View specified user's");
		lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_4.setForeground(Color.WHITE);
		lblNewLabel_2_4.setFont(new Font("Inter", Font.BOLD, 12));
		lblNewLabel_2_4.setBackground(Color.WHITE);
		lblNewLabel_2_4.setBounds(0, 54, 178, 47);
		panel_1_3.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("statistics");
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_1_2.setFont(new Font("Inter", Font.BOLD, 12));
		lblNewLabel_2_1_2.setBounds(0, 69, 178, 47);
		panel_1_3.add(lblNewLabel_2_1_2);
	}
}
