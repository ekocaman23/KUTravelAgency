package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrameMyStats extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameMyStats frame = new MainFrameMyStats();
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
	public MainFrameMyStats() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (LoginFrame.adminLogged) {
					LoginFrame.loggedCustomer = null;
				}
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		setBounds(100, 100, 500, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel( String.format("User Statistics (%s)", LoginFrame.loggedCustomer.getName()));
		lblNewLabel_1.setBounds(10, 10, 383, 22);
		lblNewLabel_1.setFont(new Font("Inter", Font.BOLD, 17));
		contentPane.add(lblNewLabel_1);
		
		double[] travelCounter = LoginFrame.loggedCustomer.travelCount();
		
		String[] columns = {"Total reservations", "Successful", "Cancelled", "Total amount spent ($)"};
		Object[][] data = new Object[1][4];
		data[0] = new Object[]{travelCounter[0], travelCounter[1], travelCounter[2], String.format("%.2f", LoginFrame.loggedCustomer.totalPrice())};
		
		DefaultTableModel model = new DefaultTableModel(data, columns);
		
		
		
		String[] scolumns = {"Average spending per reservation", "Reservation success rate", "Cancellation rate"};
		Object[][] sdata = new Object[1][3];
		sdata[0] = new Object[]{String.format("%.2f", LoginFrame.loggedCustomer.totalPrice() / travelCounter[1]), String.format("%.2f%%", (travelCounter[1] / travelCounter[0]) * 100), String.format("%.2f%%", (travelCounter[2] / travelCounter[0]) * 100)};
		
		DefaultTableModel smodel = new DefaultTableModel(sdata, scolumns);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 445, 42);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		TableColumn column = table.getColumnModel().getColumn(0);
		TableColumn column2 = table.getColumnModel().getColumn(1);
		TableColumn column3 = table.getColumnModel().getColumn(2);
		TableColumn column4 = table.getColumnModel().getColumn(3);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		column.setCellRenderer(centerRenderer);
		column2.setCellRenderer(centerRenderer);
		column3.setCellRenderer(centerRenderer);
		column4.setCellRenderer(centerRenderer);
		
		JLabel lblNewLabel_2 = new JLabel("The statistics about your reservations: ");
		lblNewLabel_2.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 42, 292, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Detailed statistics derived from previous statistics:");
		lblNewLabel_2_1.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(10, 118, 292, 13);
		contentPane.add(lblNewLabel_2_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 145, 445, 42);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable(smodel);
		scrollPane_1.setViewportView(table_1);
		
		TableColumn scolumn = table_1.getColumnModel().getColumn(0);
		TableColumn scolumn2 = table_1.getColumnModel().getColumn(1);
		TableColumn scolumn3 = table_1.getColumnModel().getColumn(2);
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		scolumn.setCellRenderer(centerRenderer);
		scolumn2.setCellRenderer(centerRenderer);
		scolumn3.setCellRenderer(centerRenderer);
	}
}
