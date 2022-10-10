import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DashboardDB {
	
	public void deleteDashboard(String ID) 
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
	    	
	    	Connection con = DriverManager.getConnection("jdbc:ucanaccess://Dental.accdb"); 
	    	String sql = "insert into PAST select * from RECORDS where ID =?";
	    	PreparedStatement preparedStmt1 = con.prepareStatement(sql);
	    	preparedStmt1.setString (1, ID);
            preparedStmt1.execute();
	    	
            String query = "DELETE FROM RECORDS WHERE ID = ?";
            //Connection con = DriverManager.getConnection("jdbc:ucanaccess://Dental.accdb"); 
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
