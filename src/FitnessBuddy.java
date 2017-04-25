import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FitnessBuddy {
	public static void main (String[] args)
	{
		//set up our database connection

		String url = "jdbc:mysql://localhost/FitnessBuddy?user=example&password=password";
		//SQLite url below
		//String url = "jdbc:sqlite:/Users/asauppe/Documents/teaching/cs364/sqlite/Shopping.db";

		Connection connect = connect(url);

		if (connect != null)
			System.out.println("Successful connection!\n");
		else 
			System.out.println("Connection failed.\n");
	
		GUI g = new GUI();
		g.makeWindow();
	}


	private static boolean disconnect(Connection connect) 
	{
		try 
		{
			connect.close();
			return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private static Connection connect(String url)
	{
		Connection connect = null;

		try 
		{
			connect = DriverManager.getConnection(url);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}
}


