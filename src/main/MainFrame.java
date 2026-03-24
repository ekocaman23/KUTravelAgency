package main;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import login_page.Customer;
import travel.Flight;
import travel.Hotel;
import travel.Taxi;
import travel.Travel;

import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Choice;
import java.awt.Panel;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static ArrayList<Flight> chosenFlights = new ArrayList<>();
	
	public static ArrayList<Hotel> chosenHotels = new ArrayList<>();
	public static ArrayList<Integer> howManyNights = new ArrayList<>();
	
	public static ArrayList<Taxi> chosenTaxis = new ArrayList<>();
	
	public static double[] chosenPrices = new double[3];
	
	public static boolean isReserved = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame mainFrame = new MainFrame();
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		LoginFrame.loggedCustomer.updateAllTravelTypes();
		
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
		
		setBounds(0, 0, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(5, 85, 1160, 180);
		scrollPane.setBorder(null);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(240, 240, 240));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		scrollPane.setViewportView(panel);
		
		JLabel lblNewLabel = new JLabel( String.format("Logged: %s ", LoginFrame.loggedCustomer.getName())    );
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setBackground(Color.BLACK);
				lblNewLabel.setOpaque(true);
				lblNewLabel.setForeground(Color.WHITE);
			}
			
			@Override
            public void mouseExited(MouseEvent e) {
                lblNewLabel.setForeground(Color.BLACK);  
                lblNewLabel.setBackground(new Color(240, 240, 240));  
            }
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							MainFrameMyStats mainFrame = new MainFrameMyStats();
							mainFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 15));
		lblNewLabel.setBounds(797, 10, 295, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblLogout = new JLabel("Logout");
		lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogout.setBackground(Color.BLACK);
		lblLogout.setOpaque(true);
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new LoginFrame().setVisible(true);;
							LoginFrame.loggedCustomer = null;
						} catch (Exception e) {
							
						}
					}
				});
				
				dispose();
			}
		});
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("Inter", Font.BOLD, 15));
		lblLogout.setBounds(1104, 10, 61, 23);
		contentPane.add(lblLogout);
		
		JLabel lblNewLabel_1 = new JLabel("Your reservations >");
		lblNewLabel_1.setBackground(new Color(255, 101, 0));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Inter", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 54, 230, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Create your own reservation >");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Inter", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(12, 305, 303, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.this.setVisible(false);
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
		panel_1.setBackground(new Color(30, 62, 98));
		panel_1.setBounds(10, 340, 180, 180);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Create a custom");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Inter", Font.BOLD, 12));
		lblNewLabel_2.setBounds(0, 50, 178, 47);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("travel package");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Inter", Font.BOLD, 12));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setBounds(0, 69, 178, 47);
		panel_1.add(lblNewLabel_2_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(380, 340, 950, 180);
		contentPane.add(scrollPane_1);
		scrollPane_1.setBorder(null);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_2.setBackground(new Color(240, 240, 240));
		scrollPane_1.setViewportView(panel_2);
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setFont(new Font("Inter", Font.BOLD, 18));
		lblMainMenu.setBounds(10, 10, 114, 22);
		contentPane.add(lblMainMenu);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Choose one of the reservations below >");
		lblNewLabel_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1.setFont(new Font("Inter", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(380, 345, 373, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Select one of pre-made travel packages >");
		lblNewLabel_1_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_1_2.setFont(new Font("Inter", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(381, 305, 467, 23);
		contentPane.add(lblNewLabel_1_1_2);
		
		Travel sananelan = new Travel("Ali", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		sananelan.setType(3);
		
		Collections.sort(LoginFrame.loggedCustomer.getTravelHistory(), Comparator.comparingInt(Travel::getType));
		
		for (Travel travel : LoginFrame.loggedCustomer.getTravelHistory()) {
            JPanel card = new JPanel();
            card.addMouseListener(new MouseAdapter() {
    			@Override
    			public void mouseClicked(MouseEvent e) {
    				isReserved = true;
    				MainFrameMyTravelInfo.yourTravel = travel;
    				EventQueue.invokeLater(new Runnable() {
    					public void run() {
    						try {
    							new MainFrameMyTravelInfo().setVisible(true);;
    							setVisible(false);
    						} catch (Exception e) {
    							e.printStackTrace();
    						}
    					}
    				});
    			}
    		});
            
            card.setLayout(new GridBagLayout());
            card.setPreferredSize(new Dimension(180, 180));
            
            switch (LoginFrame.loggedCustomer.checkTravelReservation(travel)) {
            	case 1:
            		card.setBackground(new Color(77,142,31));
            		break;
            	case 2:
            		card.setBackground(Color.RED);
            		break;
            		
            	case 3:
            		card.setBackground(Color.GRAY);
            		break;
            }
            	
            		
            
            

            JLabel label = new JLabel( String.format("%s-%s", travel.getFinalDestination(), travel.getFinalArrival() )   );
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setForeground(Color.WHITE);
            card.add(label);

            panel.add(card);
        }

		
		for (Travel travel : Travel.getTravelList()) {
            JPanel card = new JPanel();
            
            card.addMouseListener(new MouseAdapter() {
    			@Override
    			public void mouseClicked(MouseEvent e) {
    				MainFrameMyTravelInfo.yourTravel = travel;
    				EventQueue.invokeLater(new Runnable() {
    					public void run() {
    						try {
    							new MainFrameMyTravelInfo().setVisible(true);;
    							setVisible(false);
    						} catch (Exception e) {
    							e.printStackTrace();
    						}
    					}
    				});
    			}
    		});
            
            
            card.setLayout(new GridBagLayout());
            card.setPreferredSize(new Dimension(180, 180));
            card.setBackground(new Color(30,62,98));

            JLabel label = new JLabel(String.format("%s-%s", travel.getFinalDestination(), travel.getFinalArrival() ));
            label.setForeground(Color.WHITE);
            card.add(label);

            panel_2.add(card);
        }
	}
}

