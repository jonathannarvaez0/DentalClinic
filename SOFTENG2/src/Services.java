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

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Services extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldDescription;
	private JTextField textFieldPrice;
	private JTextField textFieldID;
	private JTable tableServices;
	
	ServicesDB db = new ServicesDB();
	DefaultTableModel  model;

	/**
	 * Create the panel.
	 */
	public Services() {
		setSize(520,478);
		setLayout(null);
		
		JLabel Name = new JLabel("Name");
		Name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Name.setBounds(24, 336, 100, 14);
		add(Name);
		
		JLabel lblNewLabel = new JLabel("Description");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(24, 385, 100, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Price");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(24, 435, 100, 14);
		add(lblNewLabel_1);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(135, 335, 215, 20);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setBounds(134, 384, 216, 20);
		add(textFieldDescription);
		textFieldDescription.setColumns(10);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setBounds(135, 434, 215, 20);
		add(textFieldPrice);
		textFieldPrice.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.addService(textFieldName.getText(), textFieldDescription.getText(), textFieldPrice.getText());
				textFieldName.setText(null);
				textFieldDescription.setText(null);
				textFieldPrice.setText(null);
				tableReset();
				tableUpdate();
				JOptionPane.showMessageDialog(null,"Record Added", "Success!", JOptionPane.INFORMATION_MESSAGE);
			}//BUTTON LISTENER
		});
		btnAdd.setBounds(392, 334, 89, 23);
		add(btnAdd);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.editService(textFieldID.getText(),textFieldName.getText(), textFieldDescription.getText(), textFieldPrice.getText());
				textFieldName.setText(null);
				textFieldDescription.setText(null);
				textFieldPrice.setText(null);
				tableReset();
				tableUpdate();
				JOptionPane.showMessageDialog(null,"Record Edited", "Success!", JOptionPane.INFORMATION_MESSAGE);
			}//BUTTON LISTENER
		});
		btnEdit.setBounds(392, 383, 89, 23);
		add(btnEdit);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.deleteService(textFieldID.getText());
				textFieldName.setText(null);
				textFieldDescription.setText(null);
				textFieldPrice.setText(null);
				tableReset();
				tableUpdate();
				JOptionPane.showMessageDialog(null,"Record Deleted", "Success!", JOptionPane.INFORMATION_MESSAGE);
			}//BUTTON LISTENER
		});
		btnDelete.setBounds(392, 433, 89, 23);
		add(btnDelete);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(24, 325, -2, 0);
		add(textFieldID);
		textFieldID.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 500, 303);
		add(scrollPane);
		
		tableServices = new JTable();
		model = new DefaultTableModel();
		tableServices.setModel(model);
		Object[] column = {"#", "Name", "Description", "Price"};
        model.setColumnIdentifiers(column);
        scrollPane.setViewportView(tableServices);
        
        tableUpdate();
		
		tableServices.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int selectedIndex = tableServices.getSelectedRow();
				textFieldID.setText(model.getValueAt(selectedIndex, 0).toString());
				textFieldName.setText(model.getValueAt(selectedIndex, 1).toString());
				textFieldDescription.setText(model.getValueAt(selectedIndex, 2).toString());
				textFieldPrice.setText(model.getValueAt(selectedIndex, 3).toString());
			}
		});//PUT SELECTED VALUES IN TABLE TO TEXT FIELDS
	}//CONSTRUCTOR
	
	//METHODS
	
	public void tableUpdate() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:ucanaccess://Dental.accdb");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID,serviceName,description,price FROM SERVICES");
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
			tableServices.setModel(model);
		}
		catch(SQLException sql) 
		{
			sql.printStackTrace();
	    }
	}//table update method
	
	public void tableReset() {
		DefaultTableModel dm = (DefaultTableModel) tableServices.getModel();
        int rowCount = dm.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
		
	}//table reset method
}
