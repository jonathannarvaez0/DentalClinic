

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Records extends JPanel {
	private JTable tableRecords;
	private JTextField textFieldID;
	
	RecordsDB db = new RecordsDB();
	DefaultTableModel  model;

	/**
	 * Create the panel.
	 */
	public Records() {
		setSize(520,478);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 500, 370);
		add(scrollPane);
		
		tableRecords = new JTable();
		model = new DefaultTableModel();
		tableRecords.setModel(model);
		Object[] column = {"#", "Patient Name", "Contact", "Service", "Schedule","Doctor", "Status"};
        model.setColumnIdentifiers(column);
        scrollPane.setViewportView(tableRecords);
        tableUpdate();
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.deleteRecord(textFieldID.getText());
        		tableReset();
				tableUpdate();
				JOptionPane.showMessageDialog(null,"Record Deleted", "Success!", JOptionPane.INFORMATION_MESSAGE);
			}//BUTTON LISTENER
		});
		btnDelete.setBounds(207, 417, 89, 23);
		add(btnDelete);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(10, 417, -1, 3);
		add(textFieldID);
		textFieldID.setColumns(10);
		
		tableRecords.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int selectedIndex = tableRecords.getSelectedRow();
				textFieldID.setText(model.getValueAt(selectedIndex, 0).toString());
			}
		});//PUT SELECTED VALUES IN TABLE TO TEXT FIELDS
	}
	//CONSTRUCTOR
	
	//METHODS
	public void tableUpdate() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:ucanaccess://Dental.accdb");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID,patientName,contact,status,service,schedule,doctor FROM PAST");
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
			tableRecords.setModel(model);
		}
		catch(SQLException sql) 
		{
			sql.printStackTrace();
	    }
	}//table update method
	
	public void tableReset() {
		DefaultTableModel dm = (DefaultTableModel) tableRecords.getModel();
        int rowCount = dm.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
		
	}//table reset method
}
