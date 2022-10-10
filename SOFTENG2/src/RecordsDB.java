import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class RecordsDB {
	public void deleteRecord(String ID) 
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
            
            String query = "DELETE FROM PAST WHERE ID = ?";
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
}
