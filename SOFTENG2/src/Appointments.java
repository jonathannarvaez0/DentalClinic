

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Appointments extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldContact;
	private JTextField textFieldSchedule;
	AppointmentsDB db = new AppointmentsDB();

	/**
	 * Create the panel.
	 */
	public Appointments() {
		setSize(520,478);
		setLayout(null);
		
		JLabel lblName = new JLabel("Patient Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(47, 53, 92, 28);
		add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(174, 59, 292, 20);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContact.setBounds(47, 110, 92, 14);
		add(lblContact);
		
		textFieldContact = new JTextField();
		textFieldContact.setBounds(174, 109, 292, 20);
		add(textFieldContact);
		textFieldContact.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStatus.setBounds(47, 168, 92, 14);
		add(lblStatus);
		
		JComboBox comboBoxStatus = new JComboBox();
		comboBoxStatus.setBounds(174, 166, 292, 22);
		add(comboBoxStatus);
		
		//FILL COMBO BOX
		comboBoxStatus.addItem(null);
		comboBoxStatus.addItem("New Patient");
		comboBoxStatus.addItem("Returning");
		
		JLabel lblService = new JLabel("Service");
		lblService.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblService.setBounds(47, 229, 46, 14);
		add(lblService);
		
		JComboBox comboBoxService = new JComboBox();
		comboBoxService.setBounds(174, 227, 292, 22);
		add(comboBoxService);
		
		//FILL COMBO BOX SERVICE
		
		comboBoxService.addItem(null);
		try
		{ 
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		}
		catch(ClassNotFoundException cn)
		{
				System.out.println("There was a problem in your code");
				cn.printStackTrace();
		}
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:ucanaccess://Dental.accdb");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT serviceName FROM SERVICES");
			while(rs.next()) 
			{
				String serviceCMB=rs.getString("serviceName");
					
				comboBoxService.addItem(serviceCMB);
			}	
		}
		catch(SQLException ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		JLabel lblSchedule = new JLabel("Schedule");
		lblSchedule.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSchedule.setBounds(47, 289, 92, 14);
		add(lblSchedule);
		
		textFieldSchedule = new JTextField();
		textFieldSchedule.setBounds(174, 288, 292, 20);
		add(textFieldSchedule);
		textFieldSchedule.setColumns(10);
		
		JLabel lblDoctor = new JLabel("Doctor");
		lblDoctor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDoctor.setBounds(47, 359, 92, 14);
		add(lblDoctor);
		
		JComboBox comboBoxDoctor = new JComboBox();
		comboBoxDoctor.setBounds(174, 357, 292, 22);
		add(comboBoxDoctor);
		
		//FILL COMBO BOX
		comboBoxDoctor.addItem(null);
		
		try
		{ 
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		}
		catch(ClassNotFoundException cn)
		{
				System.out.println("There was a problem in your code");
				cn.printStackTrace();
		}
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:ucanaccess://Dental.accdb");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT DentistName, specialization, workingDays FROM DENTIST");
			while(rs.next()) 
			{
				String DentistName=rs.getString("DentistName");
				String specialization=rs.getString("specialization");
				String workingDays=rs.getString("workingDays");
					
				//comboBoxDoctor.addItem(DentistName + " - " + specialization + " - "  + workingDays);
				comboBoxDoctor.addItem(DentistName);
			}	
		}
		catch(SQLException ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		JButton btnSet = new JButton("SET APPOINTMENT");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stat = comboBoxStatus.getSelectedItem().toString();
				String serv = comboBoxService.getSelectedItem().toString();
				String doc = comboBoxDoctor.getSelectedItem().toString();
				
				db.addAppointment(textFieldName.getText(), textFieldContact.getText(), stat, serv, textFieldSchedule.getText(), doc);
				textFieldName.setText(null);
				textFieldContact.setText(null);
				textFieldSchedule.setText(null);
				comboBoxStatus.setSelectedItem(null);
				comboBoxService.setSelectedItem(null);
				comboBoxDoctor.setSelectedItem(null);
				JOptionPane.showMessageDialog(null,"Record Added", "Success!", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnSet.setBounds(197, 422, 125, 23);
		add(btnSet);
		
	}
	//CONSTRUCTOR
	
	//METHODS
}
