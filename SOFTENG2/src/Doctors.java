import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Doctors extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldSpecialization;
	private JTextField textFieldContact;
	private JTextField textFieldLicense;
	private JTextField textFieldWorking;

	DoctorsDB db = new DoctorsDB();
	DefaultTableModel  model;
	private JTable tableDoctors;
	private JTextField textFieldID;
	/**
	 * Create the panel.
	 */
	public Doctors() {
		
		setSize(520,478);
		setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(26, 294, 100, 14);
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblName);
		
		JLabel lblSpecialization = new JLabel("Specialization");
		lblSpecialization.setBounds(26, 333, 100, 14);
		lblSpecialization.setHorizontalAlignment(SwingConstants.LEFT);
		lblSpecialization.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblSpecialization);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setBounds(26, 370, 100, 14);
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblContact);
		
		JLabel lblLicense = new JLabel("License");
		lblLicense.setBounds(26, 407, 100, 14);
		lblLicense.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblLicense);
		
		JLabel lblWorking = new JLabel("Working Day/s");
		lblWorking.setBounds(26, 442, 100, 14);
		lblWorking.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblWorking);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(148, 293, 200, 20);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSpecialization = new JTextField();
		textFieldSpecialization.setBounds(148, 332, 200, 20);
		add(textFieldSpecialization);
		textFieldSpecialization.setColumns(10);
		
		textFieldContact = new JTextField();
		textFieldContact.setBounds(148, 369, 200, 20);
		add(textFieldContact);
		textFieldContact.setColumns(10);
		
		textFieldLicense = new JTextField();
		textFieldLicense.setBounds(148, 406, 200, 20);
		add(textFieldLicense);
		textFieldLicense.setColumns(10);
		
		textFieldWorking = new JTextField();
		textFieldWorking.setBounds(148, 441, 200, 20);
		add(textFieldWorking);
		textFieldWorking.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBounds(382, 292, 114, 45);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.addDoctor(textFieldName.getText(), textFieldSpecialization.getText(), textFieldContact.getText(), textFieldLicense.getText(), textFieldWorking.getText());
				textFieldName.setText(null);
				textFieldSpecialization.setText(null);
				textFieldContact.setText(null);
				textFieldLicense.setText(null);
				textFieldWorking.setText(null);
				tableReset();
				tableUpdate();
				JOptionPane.showMessageDialog(null,"Record Added", "Success!", JOptionPane.INFORMATION_MESSAGE);
			}//BUTTON LISTENER METHOD
		});
		add(btnAdd);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.editDoctor(textFieldID.getText(), textFieldName.getText(), textFieldSpecialization.getText(), textFieldContact.getText(), textFieldLicense.getText(), textFieldWorking.getText());
				textFieldName.setText(null);
				textFieldSpecialization.setText(null);
				textFieldContact.setText(null);
				textFieldLicense.setText(null);
				textFieldWorking.setText(null);
				JOptionPane.showMessageDialog(null,"Record Edited", "Success!", JOptionPane.INFORMATION_MESSAGE);
				tableReset();
				tableUpdate();
			}//BUTTON LISTENER METHOD
		});
		btnEdit.setBounds(382, 358, 114, 45);
		add(btnEdit);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.deleteDoctor(textFieldID.getText());
				textFieldName.setText(null);
				textFieldSpecialization.setText(null);
				textFieldContact.setText(null);
				textFieldLicense.setText(null);
				textFieldWorking.setText(null);
				JOptionPane.showMessageDialog(null,"Record Deleted", "Success!", JOptionPane.INFORMATION_MESSAGE);
				tableReset();
				tableUpdate();
			}//BUTTON LISTENER METHOD
		});
		btnDelete.setBounds(382, 423, 114, 44);
		add(btnDelete);
		
		//TABLE
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 500, 254);
		add(scrollPane);
		
		tableDoctors = new JTable();
		model = new DefaultTableModel();
		tableDoctors.setModel(model);
		Object[] column = {"#", "Name", "Specialization", "Contact", "License", "Working"};
        model.setColumnIdentifiers(column);
		scrollPane.setViewportView(tableDoctors);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(10, 269, 1, 2);
		add(textFieldID);
		textFieldID.setColumns(10);
		
		//getContentPane().setLayout(groupLayout);
		
		tableUpdate();
		
		tableDoctors.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int selectedIndex = tableDoctors.getSelectedRow();
				textFieldID.setText(model.getValueAt(selectedIndex, 0).toString());
				textFieldName.setText(model.getValueAt(selectedIndex, 1).toString());
				textFieldSpecialization.setText(model.getValueAt(selectedIndex, 2).toString());
				textFieldContact.setText(model.getValueAt(selectedIndex, 3).toString());
				textFieldLicense.setText(model.getValueAt(selectedIndex, 4).toString());
				textFieldWorking.setText(model.getValueAt(selectedIndex, 5).toString());
			}
		});//PUT SELECTED VALUES IN TABLE TO TEXT FIELDS
		
	}//CONSTRUCTOR
	
	//METHODS
	
	public void tableUpdate() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:ucanaccess://Dental.accdb");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID,DentistName,specialization,contact,license,workingDays FROM DENTIST");
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
			tableDoctors.setModel(model);
		}
		catch(SQLException sql) 
		{
			sql.printStackTrace();
	    }
	}//table update method
	
	public void tableReset() {
		DefaultTableModel dm = (DefaultTableModel) tableDoctors.getModel();
        int rowCount = dm.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
		
	}//table reset method
}
