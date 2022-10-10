import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ServicesDB {
	
	public void addService(String name, String description, String price) {
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
            
            String query = "INSERT INTO SERVICES (serviceName,description,price)"+" VALUES(?,?,?)";
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://Dental.accdb"); 
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, description);
            preparedStmt.setString(3, price);
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
	
	public void editService(String ID, String name, String description, String price) 
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
                
        	String query = "UPDATE SERVICES SET  serviceName=?, description=?, price=? WHERE ID=?";
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://Dental.accdb"); 
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, description);
            preparedStmt.setString (3, price);
            preparedStmt.setString (4, ID);
       
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
	
	public void deleteService(String ID) 
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
            
            String query = "DELETE FROM SERVICES WHERE ID = ?";
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
