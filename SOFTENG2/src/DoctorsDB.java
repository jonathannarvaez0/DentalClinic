import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DoctorsDB {
	
	public void addDoctor(String name, String specialization, String contact, String license, String working) {
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
            
            String query = "INSERT INTO DENTIST (DentistName,specialization,contact,license,workingDays)"+" VALUES(?,?,?,?,?)";
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://Dental.accdb"); 
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, specialization);
            preparedStmt.setString(3, contact);
            preparedStmt.setString(4, license);
            preparedStmt.setString(5, working);
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
	
	public void deleteDoctor(String ID) 
    {
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
            
            String query = "DELETE FROM DENTIST WHERE ID = ?";
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://Dental.accdb"); 
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, ID);
            preparedStmt.execute();
            
	    }
            
	    catch (SQLException ex) 
	    {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }// DELETE METHOD
	
	public void editDoctor(String ID, String name, String specialization, String contact, String license, String working) 
    {
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
                
        	String query = "UPDATE DENTIST SET  DentistName=?, specialization=?, contact=?, license=?, workingDays=? WHERE ID=?";
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://Dental.accdb"); 
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, specialization);
            preparedStmt.setString (3, contact);
            preparedStmt.setString (4, license);
            preparedStmt.setString (5, working);
            preparedStmt.setString (6, ID);
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
    	
    }//EDIT METHOD

}
