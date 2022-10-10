import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AppointmentsDB {
	
	public void addAppointment(String patientName, String contact, String status, String service, String schedule, String doctor) {
		try
		{ 
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		}
		catch(ClassNotFoundException cn)
		{
				System.out.println("There was a problem in your code");
				cn.printStackTrace();
		}
	    try {
            
            String query = "INSERT INTO RECORDS (patientName,contact,status,service,schedule,doctor)"+" VALUES(?,?,?,?,?,?)";
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://Dental.accdb"); 
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, patientName);
            preparedStmt.setString (2, contact);
            preparedStmt.setString(3, status);
            preparedStmt.setString(4, service);
            preparedStmt.setString(5, schedule);
            preparedStmt.setString(6, doctor);
            preparedStmt.execute();
        } 
	    catch (SQLException ex) 
	    {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
	    catch (NumberFormatException ee) 
	    {
            JOptionPane.showMessageDialog(null, "Input Valid value/s!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } 
		
	}//ADD METHOD
}
