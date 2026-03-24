package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import login_page.Customer;
import travel.Flight;
import travel.Travel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminMainFrameShowTravels extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainFrameShowTravels frame = new AdminMainFrameShowTravels();
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
	public AdminMainFrameShowTravels() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				AdminMainFrame.adminEditing = false;
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAllTravels = new JLabel("All Travels");
		lblAllTravels.setBounds(10, 10, 145, 22);
		lblAllTravels.setFont(new Font("Inter", Font.BOLD, 17));
		contentPane.add(lblAllTravels);
		
		JLabel lblNewLabel_2 = new JLabel("All travel packages which are created by administrator:");
		lblNewLabel_2.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 42, 405, 13);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 716, 194);
		contentPane.add(scrollPane);
		
		
		String[] columns = {"Travel ID", "Travel Locations"};
		Object[][] data = new Object[Travel.getTravelList().size()][2];
		
		
			for (int i = 0; i < Travel.getTravelList().size(); i++) {
				String travelLocations = Travel.getTravelList().get(i).getFlights().get(0).getDepartureCity() + " > ";
				
				for (Flight flight : Travel.getTravelList().get(i).getFlights()) {
					if (flight.getArrivalCity() != null) {
						if ( Travel.getTravelList().get(i).getFlights().indexOf(flight) + 1  >= Travel.getTravelList().get(i).getFlights().size()) {
							travelLocations += flight.getArrivalCity();
						}
						
						else {
							travelLocations += flight.getArrivalCity() + " > ";
						}
						
					}
					
					else {
						if (Travel.getTravelList().get(i).getFlights().indexOf(flight) + 1 >= Travel.getTravelList().get(i).getFlights().size()) {
							travelLocations += flight.getFinalArrivalCity();
						}
						
						else {
							travelLocations += flight.getFinalArrivalCity() + " > ";
						}
						
					}
				}
				
				data[i] = new Object[]{Travel.getTravelList().get(i).getTravelID(),travelLocations};
			}
		
	
		
		
		
		
		
		
		
		
		
		DefaultTableModel model = new DefaultTableModel(data, columns);
		
		table = new JTable(model);
		TableColumn column = table.getColumnModel().getColumn(0);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        column.setCellRenderer(centerRenderer);
        
        TableColumn column2 = table.getColumnModel().getColumn(1);
        column2.setCellRenderer(centerRenderer);
        
        		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
	    column.setMinWidth(116);
	    column.setPreferredWidth(116);
	        
	    column2.setMinWidth(597);
	    column2.setPreferredWidth(597);
	        
		scrollPane.setViewportView(table);
		
		
		JButton btnClose = new JButton("Show info");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(contentPane, "Choose a row to show info about a travel package.");
				}
				else {
					int chosenTravelRow = table.getSelectedRow();
					
					
					MainFrameMyTravelInfo.yourTravel = Travel.findTravelFromID( (int) data[chosenTravelRow][0] );
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								new MainFrameMyTravelInfo().setVisible(true);;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					setVisible(false);
				}
				
				
				
			}
		});
		btnClose.setBounds(10, 325, 251, 52);
		contentPane.add(btnClose);
		
	}
}
