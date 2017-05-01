import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Date;

public class FitnessBuddy {
	public static void main (String[] args) throws ParseException
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

	public static boolean getDateInDB(Connection connect, int userID, Date date) 
	{
		String queryStr = "SELECT * FROM Sleep WHERE UserID = ? AND `Date` = ?";

		ResultSet resultSet = null;

		PreparedStatement query;
		try {
			query = connect.prepareStatement(queryStr);
			query.setInt(1, userID);
			query.setDate(2, date);
			
			System.out.print(query.toString());
			
			resultSet = query.executeQuery();

			if (resultSet.next()) 
			{
				return true;
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	
	public static String getUserIDInDB(Connection connect, String item) 
	{
		String queryStr = "SELECT * FROM User WHERE Username = ?";

		ResultSet resultSet = null;

		PreparedStatement query;
		try {
			query = connect.prepareStatement(queryStr);
			query.setString(1, item);

			resultSet = query.executeQuery();

			if (resultSet.next()) 
			{
				return resultSet.getString(1);
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public static String getPasswordInDB(Connection connect, String item) 
	{
		String queryStr = "SELECT * FROM User WHERE Username = ?";

		ResultSet resultSet = null;

		PreparedStatement query;
		try {
			query = connect.prepareStatement(queryStr);
			query.setString(1, item);

			resultSet = query.executeQuery();

			if (resultSet.next()) 
			{
				return resultSet.getString(3);
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public static boolean isItemInDB(Connection connect, String item) 
	{
		String queryStr = "SELECT * FROM User WHERE Username = ?";

		ResultSet resultSet = null;

		PreparedStatement query;
		try {
			query = connect.prepareStatement(queryStr);
			query.setString(1, item);

			resultSet = query.executeQuery();

			if (resultSet.next()) 
			{
				return true;
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	private static void addItem(Connection connect, String input) throws SQLException
	{

		if (isItemInDB(connect, input))
		{

			String updateStr = "UPDATE Shopping SET Quantity = Quantity + 1 WHERE Item = ?";
			PreparedStatement update = connect.prepareStatement(updateStr);

			// get current value for quantity
			// increment by 1
			// update.setInt for the new quantity

			update.setString(1, input);

			update.executeUpdate();

		} else
		{

			String insertStr = "INSERT INTO Shopping (Item, Quantity) VALUES (?, ?)";
			//String insertStr = "INSERT INTO Shopping (Item, Quantity) VALUES (\"" + input + "\", " + "1)";
			//String insertStr = "INSERT INTO Shopping (Item, Quantity) VALUES ("apple", 1)";
			PreparedStatement insert = connect.prepareStatement(insertStr);

			insert.setString(1, input);
			insert.setInt(2, 1);

			insert.executeUpdate();
		}
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

	static Connection connect(String url)
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


