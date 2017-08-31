import java.sql.*;
import javax.swing.*;
public class sqliteConnection {
	Connection conn = null;
	public static Connection dbConnector(){
		try
		{
			Class.forName("org.sqlite.JDBC");
				Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Jeremiah\\Documents\\Tory\\Tory.sqlite");
			//	JOptionPane.showMessageDialog(null, "");
				return conn;
				}catch(Exception io){
			JOptionPane.showMessageDialog(null, io);
			return null;
		}
	
	}
}
