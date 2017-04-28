import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener
{	
	public static final String DEFAULT_CONNECTION_URL = 
			"jdbc:mysql://localhost/FitnessBuddy?user=example&password=password";
	private Connection database = null;
	
	//Main window, button panel, buttons and timer
	private JFrame logWindow, mainMenu, signWindow, displayWindow, submit;
	private Button log, back, sign, dietB, sleepB, actB, bodyB, submitB, submitUser, display;
	private Button submitBack, submitEnter, displayB;
	private Button signBack;
	private JTextField user;
	private JTextField firstName, middleName, lastName, location, email, user1;
	private JPasswordField pass, pass1, pass2;
	private JTextField day, month, year, calorie, unsaturatedFat, saturatedFat, protein, fiber, carbohydrate,
	quality, hour, weight, height, bodyFatPercentage, bmi, waistSize, duration, caloriesBurned, activity;
	private JTextField calorie1, unsaturatedFat1, saturatedFat1, protein1, fiber1, carbohydrate1,
	quality1, hour1, weight1, height1, bodyFatPercentage1, bmi1, waistSize1, duration1, caloriesBurned1, activity1;
	private JTextField day1, month1, year1;

	public void logWindow()
	{
		//Create a login window
		logWindow = new JFrame("FITNESS BUDDY");
		logWindow.setSize(600, 600);
		logWindow.setLocation(200, 20);
		logWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logWindow.setLayout(new GridLayout(8, 8));

		//Create text field and button
		user = new JTextField("Username", 20);
		pass = new JPasswordField("Password", 20);
		log = new Button("LOG IN");
		sign = new Button("NEW USER? SIGN UP!");
		log.addActionListener(this);
		sign.addActionListener(this);

		logWindow.add(user);
		logWindow.add(pass);
		logWindow.add(log);
		logWindow.add(sign);
	}

	public void signWindow()
	{
		//Create a sign in window
		signWindow = new JFrame("SIGN UP");
		signWindow.setSize(600, 600);
		signWindow.setLocation(200, 20);
		signWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signWindow.setLayout(new GridLayout(8, 8));

		//Create text file for sign up window
		firstName = new JTextField("First Name", 20);
		middleName = new JTextField("Middle Name", 20);
		lastName = new JTextField("Last Name", 20);
		location = new JTextField("Location", 20);
		email = new JTextField("Email Address", 20);
		user1 = new JTextField("Username", 20);
		pass1 = new JPasswordField("Password", 20);
		pass2 = new JPasswordField("Password", 20);


		signBack = new Button("Login Window");
		signBack.addActionListener(this);
		submitUser = new Button("Sign In. It's Free!");
		submitUser.addActionListener(this);

		signWindow.add(firstName);
		signWindow.add(middleName);
		signWindow.add(lastName);
		signWindow.add(location);
		signWindow.add(email);
		signWindow.add(user1);
		signWindow.add(pass1);
		signWindow.add(pass2);
		signWindow.add(submitUser);
		signWindow.add(signBack);
	}

	public void mainMenu()
	{
		//Create a main menu window
		mainMenu = new JFrame("Main Menu");
		mainMenu.setSize(600, 600);
		mainMenu.setLocation(200, 20);
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenu.setLayout(new GridLayout(8, 8));

		//All buttons we need
		dietB = new Button("DIET");
		sleepB = new Button("SLEEP");
		actB = new Button("ACTIVITY");
		bodyB = new Button("BODY");
		submitB = new Button("SUBMIT DATA");
		back = new Button("LOG OUT");
		display = new Button("DISPLAY DATA");

		dietB.addActionListener(this);
		sleepB.addActionListener(this);
		actB.addActionListener(this);
		bodyB.addActionListener(this);
		submitB.addActionListener(this);
		back.addActionListener(this);
		display.addActionListener(this);
		
		day1 = new JTextField("DAY");
		month1 = new JTextField("MONTH");
		year1 = new JTextField("YEAR");

		mainMenu.add(day1);
		mainMenu.add(month1);
		mainMenu.add(year1);
		mainMenu.add(display);
		mainMenu.add(submitB);
		mainMenu.add(back);
	}



	public void submitWindow()
	{
		//Submit window
		submit = new JFrame("PLEASE ENTER DATA");
		submit.setSize(600, 600);
		submit.setLocation(200, 20);
		submit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		submit.setLayout(new GridLayout(8, 8));

		//Submit function
		day  = new JTextField("Day", 2);
		month  = new JTextField("Month", 2);
		year  = new JTextField("Year", 4);
		calorie = new JTextField("Calories Intake", 20);
		unsaturatedFat = new JTextField("Unsaturated Fat", 20);
		saturatedFat = new JTextField("Saturated Fat", 20);
		protein = new JTextField("Protein", 20);
		fiber = new JTextField("Fiber", 20);
		carbohydrate = new JTextField("Carbohydrate", 20);
		quality  = new JTextField("Quality", 2);
		hour  = new JTextField("Hours", 20);	
		weight = new JTextField("Weight", 2);
		height = new JTextField("Height", 2);
		bodyFatPercentage = new JTextField("Body Fat Percentage", 2);
		bmi = new JTextField("BMI", 20);
		waistSize = new JTextField("Waist Size", 20);
		duration = new JTextField("Duration", 20);
		caloriesBurned = new JTextField("Calories Burned", 20);
		activity = new JTextField("Name of Activity", 20);

		submit.add(day);
		submit.add(month);
		submit.add(year);
		submit.add(calorie);
		submit.add(unsaturatedFat);
		submit.add(saturatedFat);
		submit.add(protein);
		submit.add(protein);
		submit.add(fiber);
		submit.add(carbohydrate);
		submit.add(quality);
		submit.add(hour);
		submit.add(weight);
		submit.add(height);
		submit.add(bodyFatPercentage);
		submit.add(bmi);
		submit.add(waistSize);
		submit.add(duration);
		submit.add(caloriesBurned);
		submit.add(activity);

		submitBack = new Button("MAIN MENU");
		submitEnter = new Button("SUBMIT DATA");

		submitBack.addActionListener(this);
		submitEnter.addActionListener(this);

		submit.add(submitEnter);
		submit.add(submitBack);

	}

	public void displayWindow()
	{
		//display window
		displayWindow = new JFrame("DISPLAYING DATA");
		displayWindow.setSize(600, 600);
		displayWindow.setLocation(200, 20);
		displayWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		displayWindow.setLayout(new GridLayout(8, 8));
		
		calorie1 = new JTextField("Calories Intake: ", 20);
		unsaturatedFat1 = new JTextField("Unsaturated Fat: ", 20);
		saturatedFat1 = new JTextField("Saturated Fat: ", 20);
		protein1 = new JTextField("Protein: ", 20);
		fiber1 = new JTextField("Fiber: ", 20);
		carbohydrate1 = new JTextField("Carbohydrate: ", 20);
		quality1  = new JTextField("Quality: ", 2);
		hour1  = new JTextField("Hours: ", 20);	
		weight1 = new JTextField("Weight: ", 2);
		height1 = new JTextField("Height: ", 2);
		bodyFatPercentage1 = new JTextField("Body Fat Percentage: ", 2);
		bmi1 = new JTextField("BMI: ", 20);
		waistSize1 = new JTextField("Waist Size: ", 20);
		duration1 = new JTextField("Duration: ", 20);
		caloriesBurned1 = new JTextField("Calories Burned: ", 20);
		activity1 = new JTextField("Name of Activity: ", 20);
		
		displayWindow.add(calorie1);
		displayWindow.add(unsaturatedFat1);
		displayWindow.add(saturatedFat1);
		displayWindow.add(protein1);
		displayWindow.add(protein1);
		displayWindow.add(fiber1);
		displayWindow.add(carbohydrate1);
		displayWindow.add(quality1);
		displayWindow.add(hour1);
		displayWindow.add(weight1);
		displayWindow.add(height1);
		displayWindow.add(bodyFatPercentage1);
		displayWindow.add(bmi1);
		displayWindow.add(waistSize1);
		displayWindow.add(duration1);
		displayWindow.add(caloriesBurned1);
		displayWindow.add(activity1);
		
		
		displayB = new Button("MAIN MENU");
		displayB.addActionListener(this);
		
		displayWindow.add(displayB);
	}

	public void makeWindow()
	{
		Connection database = FitnessBuddy.connect(GUI.DEFAULT_CONNECTION_URL);
		
		logWindow();
		signWindow();
		mainMenu();
		submitWindow();
		displayWindow();
		logWindow.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == log)
		{
			logWindow.setVisible(false);
			mainMenu.setVisible(true);
		}
		if (e.getSource() == back)
		{
			mainMenu.setVisible(false);
			logWindow.setVisible(true);
		}
		if (e.getSource() == sign)
		{
			logWindow.setVisible(false);
			signWindow.setVisible(true);
		}
		if (e.getSource() == signBack)
		{
			logWindow.setVisible(true);
			signWindow.setVisible(false);
		}	
		if (e.getSource() == submitB)
		{
			mainMenu.setVisible(false);
			submit.setVisible(true);
		}
		if (e.getSource() == submitBack)
		{
			submit.setVisible(false);
			mainMenu.setVisible(true);
		}
		if (e.getSource() == submitEnter)
		{
			try {
				if (this.submitData()) {
					this.displaySuccess("Data successfully submitted.");
					submit.setVisible(false);
					mainMenu.setVisible(true);
				}
				else {
					this.displayError("Data entry error", "Data not submitted. Check the submission window for errors.");
				}
			}
			catch (Exception ex) {
				this.displayError("Error caught!", "What, you expect me to know? Find it yourself, ya lazy pile.");
			}
		}
		if (e.getSource() == submitUser)
		{
			signWindow.setVisible(false);
			logWindow.setVisible(true);
		}
		if (e.getSource() == display)
		{
			mainMenu.setVisible(false);
			displayWindow.setVisible(true);
		}
		if (e.getSource() == displayB)
		{
			displayWindow.setVisible(false);
			mainMenu.setVisible(true);
		}
	}
	
	private boolean submitData() throws SQLException 
	{
		
		boolean successfulSubmit = false;
		String activityInsert = "INSERT INTO Shopping (Item, Quantity) VALUES (?, ?)";
		String sleepInsert = "INSERT INTO Shopping (Item, Quantity) VALUES (?, ?)";
		String bodyMeasurementsInsert = "INSERT INTO Shopping (Item, Quantity) VALUES (?, ?)";
		String nutritionInsert = "INSERT INTO Shopping (Item, Quantity) VALUES (?, ?)";
		
		PreparedStatement insert = database.prepareStatement(activityInsert);
		
		insert = database.prepareStatement(activityInsert);
		insert.executeUpdate();
		
		return successfulSubmit;
		
	}
	
	private void displaySuccess(String message) {
		
		this.displayPopup(false, "Success!", message);
		
	}
	
	private void displaySuccess(String title, String message) {
		
		this.displayPopup(false, title, message);
		
	}
	
	private void displayError(String message) {
		
		this.displayPopup(true, "Error!", message);
		
	}
	
	private void displayError(String title, String message) {
		
		this.displayPopup(true, title, message);
		
	}
	
	private void displayPopup(boolean isError, String title, String message) {
		
		JOptionPane.showMessageDialog(null, message, title, (isError ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE));
		
	}
}
