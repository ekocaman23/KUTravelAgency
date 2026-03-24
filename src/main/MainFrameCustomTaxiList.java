package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import travel.Hotel;
import travel.Taxi;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrameCustomTaxiList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JPanel contentPane;
	public static Taxi chosenTaxi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameCustomTaxiList frame = new MainFrameCustomTaxiList();
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
	public MainFrameCustomTaxiList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Taxis");
		lblNewLabel_1.setFont(new Font("Inter", Font.BOLD, 17));
		lblNewLabel_1.setBounds(10, 10, 75, 32);
		contentPane.add(lblNewLabel_1);
		
		int allTaxis = Taxi.getTaxiList().size();
		int foundTaxis = MainFrameCustomTaxi.searchedTaxis.size();
		
		String labelString = String.format("Matching taxis: (%d/%d)", foundTaxis, allTaxis);
		JLabel lblNewLabel_2 = new JLabel(labelString);
		lblNewLabel_2.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 50, 194, 13);
		contentPane.add(lblNewLabel_2);
		
		String[] columns = {"Taxi ID", "City", "Type", "Available Taxis", "Total Fare"};
		Object[][] data = new Object[MainFrameCustomTaxi.searchedTaxis.size()][5];
		
		ArrayList<Taxi> taxis = MainFrameCustomTaxi.searchedTaxis;
		
		for (int i = 0; i < MainFrameCustomTaxi.searchedTaxis.size(); i++) {
			String fare = String.format("%.2f", taxis.get(i).calculateFare(MainFrame.chosenHotels.get(MainFrameCustomTaxi.taxiNumber)));
			
			data[i] = new Object[]{taxis.get(i).getTaxiID(), taxis.get(i).getCity(), taxis.get(i).getType(), taxis.get(i).getAvailableTaxis(), 
					fare};
		}
		
		DefaultTableModel model = new DefaultTableModel(data, columns);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 716, 195);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2_1 = new JLabel("Press a row to choose a taxi.");
		lblNewLabel_2_1.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(10, 290, 170, 13);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnChoose = new JButton("Take the taxi");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(contentPane, "At least 1 taxi option must be selected.");
				}
				
				else {
					int chosenTaxiRow = table.getSelectedRow();
					chosenTaxi = Taxi.findTaxiFromID((int) data[chosenTaxiRow][0]);
					MainFrame.chosenTaxis.add(chosenTaxi);
					
					MainFrameCustomTaxi.taxiNumber++;
					
					if (MainFrameCustomTaxi.taxiNumber >= MainFrame.chosenHotels.size()) {
						MainFrameCustomTaxi.taxiNumber = 0;
						
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									MainFrameCustomSummary newFrame = new MainFrameCustomSummary();
									newFrame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
					}
					
					else {
						try {
							MainFrameCustomTaxi newFrame = new MainFrameCustomTaxi();
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
