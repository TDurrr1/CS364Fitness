import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FitnessBuddy {
	
	public static void main (String[] args)
	{	
		GUI g = new GUI();
		g.makeWindow();
	}

	public static boolean disconnect(Connection connect) 
	{
		try {
			connect.close();
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Connection connect(String url)
	{
		Connection connect = null;

		try {
			connect = DriverManager.getConnection(url);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connect;
	}
}
