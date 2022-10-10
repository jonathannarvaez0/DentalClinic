

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard extends JPanel {
	private JTable tableDashboard;
	
	DashboardDB db = new DashboardDB();
	DefaultTableModel  model;
	private JTextField textFieldID;
	private JButton btnDelete;

	/**
	 * Create the panel.
	 */
	public Dashboard() {
		setBounds(270, 11, 520, 478);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 500, 370);
		add(scrollPane);
		
		tableDashboard = new JTable();
		model = new DefaultTableModel();
		tableDashboard.setModel(model);
		Object[] column = {"#", "Patient Name", "Contact", "Status", "Service","Schedule", "Doctor"};
        model.setColumnIdentifiers(column);
        scrollPane.setViewportView(tableDashboard);
        tableUpdate();
        
        textFieldID = new JTextField();
        textFieldID.setBounds(10, 414, -6, 3);
        add(textFieldID);
        textFieldID.setColumns(10);
        
        btnDelete = new JButton("DELETE");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		db.deleteDashboard(textFieldID.getText());
        		tableReset();
				tableUpdate();
				JOptionPane.showMessageDialog(null,"Record Deleted", "Success!", JOptionPane.INFORMATION_MESSAGE);
        	}//BUTTON LISTENER
        });
        btnDelete.setBounds(200, 414, 89, 23);
        add(btnDelete);
        
        tableDashboard.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int selectedIndex = tableDashboard.getSelectedRow();
				textFieldID.setText(model.getValueAt(selectedIndex, 0).toString());
			}
		});//PUT SELECTED VALUES IN TABLE TO TEXT FIELDS
        
		
		setVisible(true);
		
	}
	//CONSTRUCTOR
	
	//METHODS
	
		public void tableUpdate() {
			try 
			{
				Connection con = DriverManager.getConnection("jdbc:ucanaccess://Dental.accdb");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT ID,patientName,contact,status,service,schedule,doctor FROM RECORDS");
				java.sql.ResultSetMetaData rsmd = rs.getMetaData();
				int colNo = rsmd.getColumnCount();
				while(rs.next())
				{
				 Object[] objects = new Object[colNo];
				 for(int i=0;i<colNo;i++)
				 {
				  objects[i]=rs.getObject(i+1);
				 }
				 model.addRow(objects);
				}
				tableDashboard.setModel(model);
			}
			catch(SQLException sql) 
			{
				sql.printStackTrace();
		    }
		}//table update method
		
		public void tableReset() {
			DefaultTableModel dm = (DefaultTableModel) tableDashboard.getModel();
	        int rowCount = dm.getRowCount();
	        //Remove rows one by one from the end of the table
	        for (int i = rowCount - 1; i >= 0; i--) {
	            dm.removeRow(i);
	        }
			
		}//table reset method
}
