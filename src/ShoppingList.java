import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ShoppingList {
	
	/*
	 * load the JDBC driver
	 * 
	 * set up the database
	 * 
	 * connect to the database
	 * 
	 * insert/delete/modify our data
	 * 
	 * disconnect from the database
	 * 
	 */
	
	public static void main(String[] args) {

		//set up our database connection
		
		String url = "jdbc:mysql://localhost/Shopping?user=example&password=password";
		//SQLite url below
		//String url = "jdbc:sqlite:/Users/asauppe/Documents/teaching/cs364/sqlite/Shopping.db";
		
		Connection connect = connect(url);
		
		if (connect != null) {
			System.out.println("Successful connection!\n");
		} else {
			System.out.println("Connection failed.\n");
		}
		
		//add/modify/delete tuples
		
		Scanner scan = new Scanner(System.in);
		String input = "";
		
		do {
			
			System.out.print("Enter an item: ");
			input = scan.nextLine();
			
			// remove an item
			if(input.charAt(0) == '-') {
				try {
					removeItem(connect, input.substring(1));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// add an item
				try {
					addItem(connect, input);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// indicate they're done
			
		}while(!input.equals("done"));
		
		
		
		
		
		//disconnect from the database
		
		boolean disconnect = disconnect(connect);
		if (disconnect) {
			System.out.println("Disconnection successful!\n");
		} else {
			System.out.println("Not able to disconnect.\n");
		}
	}
	
	private static void removeItem(Connection connect, String input) throws SQLException {
		//deleting an item from the table
		String deleteStr = "DELETE FROM Shopping WHERE Item = ?";
		PreparedStatement delete = connect.prepareStatement(deleteStr);
		delete.setString(1, input);
		delete.executeUpdate();
	}
	
	private static void addItem(Connection connect, String input) throws SQLException {
		
		if (isItemInDB(connect, input)) {
			
			String updateStr = "UPDATE Shopping SET Quantity = Quantity + 1 WHERE Item = ?";
			PreparedStatement update = connect.prepareStatement(updateStr);
			
			// get current value for quantity
			// increment by 1
			// update.setInt for the new quantity
			
			update.setString(1, input);
			
			update.executeUpdate();
			
		} else {
		
			String insertStr = "INSERT INTO Shopping (Item, Quantity) VALUES (?, ?)";
			//String insertStr = "INSERT INTO Shopping (Item, Quantity) VALUES (\"" + input + "\", " + "1)";
			//String insertStr = "INSERT INTO Shopping (Item, Quantity) VALUES ("apple", 1)";
			PreparedStatement insert = connect.prepareStatement(insertStr);
			
			insert.setString(1, input);
			insert.setInt(2, 1);
			
			insert.executeUpdate();
		}
	}
	
	private static boolean isItemInDB(Connection connect, String item) {
		String queryStr = "SELECT * FROM Shopping WHERE Item = ?";
		
		ResultSet resultSet = null;
		
		PreparedStatement query;
		try {
			query = connect.prepareStatement(queryStr);
			query.setString(1, item);
			
			resultSet = query.executeQuery();
			
			if (resultSet.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	private static boolean disconnect(Connection connect) {
		try {
			connect.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	private static Connection connect(String url) {
		Connection connect = null;
		
		try {
			connect = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connect;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
