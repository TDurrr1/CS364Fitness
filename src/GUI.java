import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import java.sql.Connection;
import java.sql.PreparedStatement;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener, FocusListener {	
	
	/*** Class constants ***/
	
	public static final String DEFAULT_CONNECTION_URL = "jdbc:mysql://localhost/FitnessBuddy?user=example&password=password";
	
	/*
	 * These constants are meant for easily accessing the information contained in the two-dimensional arrays below.
	 */
	// Dimension one: Data type
	public static final int NAME = 0;
	public static final int UNIT = 1;
	public static final int MAX_LENGTH = 2;
	
	// Dimension two: Table
	public static final int ACTIVITY = 0;
	public static final int BODYMEASUREMENTS = 1;
	public static final int NUTRITION = 2;
	public static final int SLEEP = 3;
	
    /* The way these arrays are set up is as follows.
     *      - In each 2D array, there is one 1D array corresponding each of the alphabetically-ordered database tables:
     *         - Activity,
     *         - BodyMeasurements,
     *         - Nutrition, and
     *         - Sleep.
     *       (No data for UserId nor Date are stored here because they are either easily retrieved elsewhere or easy enough to
     *       enter manually.)
     *     - These are contained in the three 2D arrays which store the following data:
     *         - FIELD_NAMES: The user-friendly title of each piece of data to be entered.
     *             - NOTE: To get the attribute name for any field name, simply remove all spaces.
     *         - FIELD_UNITS: The spelled-out name of the unit which the correlating SUBMISSION_FIELD_NAMES value
     *           measured in. If this value is null, then this value doesn't have a unit.
     *         - FIELD_MAX_LENGTHS: The maximum length of each piece of data in SUBMISSION_FIELD_NAMES.
	 *           
	 *     Thus, this format is the way in which data is accessed:
	 *         FIELD_NAMES[<table>][<attribute>]
	 *         
	 *     For example, to find the name for the unit of the second attribute in the BodyMeasurements table, use...
	 *         FIELD_UNITS[BODYMEASUREMENTS][1]
	 */
	private final String FIELD_NAMES[][] = {
			{"Activity name", "Activity duration", "Calories burned"},
			{"Weight", "Height", "Body fat percentage", "Body mass index", "Waist circumference"},
			{"Caloric intake", "Unsaturated fat", "Saturated fat", "Protein", "Fiber", "Carbohydrates"},
			{"Sleep quality", "Sleep length"}};
	private final String FIELD_UNITS[][] = {
			{null, "minutes", "kilocalories"},
			{"pounds", "inches", null, null, "inches"},
			{"kilocalories", "grams", "grams", "grams", "grams", "grams"},
			{"1-10", "hours"}};
	private final int FIELD_MAX_LENGTHS[][] = {
			{50, 3,  4            },
			{3,  3,  2,  3,  3    },
			{5,  3,  3,  3,  3,  3},
			{2,  2                }};
	private final String TABLE_NAMES[] = {"Activity", "BodyMeasurements", "Nutrition", "Sleep"};
	
	/*** Class variables ***/
	
	private Connection database = null;
	
	//Main window, button panel, buttons and timer
	private JFrame logWindow, mainMenu, signWindow, displayWindow, submit;
	private Button log, back, sign, submitB, submitUser, display;
	private Button submitBack, submitEnter, displayB;
	private Button signBack;
	private JTextField user;
	private JTextField firstName, middleName, lastName, location, email, user1;
	private JPasswordField pass, pass1, pass2;
	private JSpinner date;
	private JTextField submissionFields[][] = new JTextField[FIELD_NAMES.length][];
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
		submitB = new Button("SUBMIT DATA");
		back = new Button("LOG OUT");
		display = new Button("DISPLAY DATA");

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
		// date parameters for date input; arguments are:
		//     the default date (today),
		//     the earliest date enterable (January 1, 2000),
		//     the latest date enterable (today),
		//     the increment executed by clicking the spinner buttons (one day)
		
		SpinnerDateModel dateModel = new SpinnerDateModel(new Date(),
				                                          new Date(946684800000L),
				                                          new Date(),
				                                          0);
		JLabel submissionLabels[][] = new JLabel[FIELD_NAMES.length][];
		for (int i = 0; i < submissionLabels.length; i++) {
			submissionLabels[i] = new JLabel[FIELD_NAMES[i].length];
			submissionFields[i] = new JTextField[FIELD_NAMES[i].length];
		}
		
		// Instantiate submission window
		
		submit = new JFrame("Data Submission");
		submit.setSize(500, 800);
		submit.setLocationRelativeTo(null);
		submit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		submit.setLayout(new GridLayout(0, 2));

		// Instantiate date selection
		
		date = new JSpinner(dateModel);
		
		// Instantiate all submission fields
		
		for (int table = 0; table < FIELD_MAX_LENGTHS.length; table++) {
			for (int attribute = 0; attribute < FIELD_MAX_LENGTHS[table].length; attribute++) {
				submissionLabels[table][attribute] = new JLabel(this.getSubmissionFieldLabelText(table, attribute) + ":  ", JTextField.RIGHT);
				submissionFields[table][attribute] = new JTextField(null);
				submissionFields[table][attribute].addFocusListener(this);
			}
		}
		
		// Instantiate buttons
		
		submitEnter = new Button("SUBMIT DATA");
		submitBack = new Button("MAIN MENU");
		submitEnter.addActionListener(this);
		submitBack.addActionListener(this);

		// Add data entry fields, using empty JLabels and JSeparators for visual adjustments

		submit.add(new JLabel());
		submit.add(new JLabel());
		submit.add(new JLabel("Date: ", JTextField.RIGHT));
		submit.add(date);
		for (int table = 0; table < submissionFields.length; table++) {
			submit.add(new JLabel());
			submit.add(new JLabel());
			submit.add(new JSeparator(JSeparator.HORIZONTAL));
			submit.add(new JSeparator(JSeparator.HORIZONTAL));
			for (int attribute = 0; attribute < submissionFields[table].length; attribute++) {
				submit.add(submissionLabels[table][attribute]);
				submit.add(submissionFields[table][attribute]);
			}
		}
		submit.add(new JLabel());
		submit.add(new JLabel());
		
		// Add buttons

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
		
		calorie1 = new JTextField("Caloric Intake: ", 20);
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
		database = FitnessBuddy.connect(GUI.DEFAULT_CONNECTION_URL);
		
		logWindow();
		signWindow();
		mainMenu();
		submitWindow();
		displayWindow();
		logWindow.setVisible(true);
	}

	/*
	 * Returns the default text of a submission field given its index in submissionFields.
	 * If index >= submissionFields.length, then @return == "".
	 */
	private String getSubmissionFieldLabelText(int table, int attribute) {
		
		String defaultText = "";
		
		if (table < FIELD_NAMES.length && attribute < FIELD_NAMES[table].length) {
			defaultText = FIELD_NAMES[table][attribute];
			if (FIELD_UNITS[table][attribute] != null) {
				defaultText = defaultText + " (" + FIELD_UNITS[table][attribute] + ")";
			}
		}
		
		return defaultText;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == log)
		{
			if (!(f.isItemInDB(database, user.getText())))
			{
				displayError("We cannot find your username");
			}
			else if (f.isItemInDB(database, user.getText()) && !f.getPasswordInDB(database, user.getText()).equals(pass.getText()))
			{
				displayError("Wrong password");
			}
			else if (f.isItemInDB(database, user.getText()) && f.getPasswordInDB(database, user.getText()).equals(pass.getText()))
			{
				logWindow.setVisible(false);
				mainMenu.setVisible(true);
			}
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
				this.submitData();
				this.displaySuccess("Data successfully submitted.");
				submit.setVisible(false);
				mainMenu.setVisible(true);
			}
			catch (Exception ex) {
				this.displayError("Data entry error", "Data not submitted. " + ex.toString());
			}
		}
		if (e.getSource() == submitUser)
		{
			signWindow.setVisible(false);
			logWindow.setVisible(true);
		}
		if (e.getSource() == display)
		{
			displayAll();
			mainMenu.setVisible(false);
			displayWindow.setVisible(true);
		}
		if (e.getSource() == displayB)
		{
			displayWindow.setVisible(false);
			mainMenu.setVisible(true);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		
		Object caller = e.getSource();
		int table = 0;
		int attribute = 0;
		
		// check if this is a JTextField from the submissionFields array
		
		while (table < submissionFields.length && !submissionFields[table][attribute].equals(caller)) {
			attribute++;
			if (attribute >= submissionFields[table].length) {
				table++;
				attribute = 0;
			}
		}
		
		// if table is still less than the length of submissionFields, the caller is a text field
		
		if (table < submissionFields.length) {
			this.clearError(submissionFields[table][attribute]);
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		Object caller = e.getSource();
		int table = 0;
		int attribute = 0;
		
		// check if this is a JTextField from the submissionFields array
		
		while (table < submissionFields.length && !submissionFields[table][attribute].equals(caller)) {
			attribute++;
			if (attribute >= submissionFields[table].length) {
				table++;
				attribute = 0;
			}
		}
		
		// if table is still less than the length of submissionFields, the caller is a text field
		
		if (table < submissionFields.length) {
			
			// trim, then truncate input to no longer than its maximum length
			
			String formattedInput = submissionFields[table][attribute].getText().trim();
			formattedInput = formattedInput.substring(0, Math.min(FIELD_MAX_LENGTHS[table][attribute], formattedInput.length()));
			submissionFields[table][attribute].setText(formattedInput);
		}
		
	}
	
	private void setError(JComponent caller) {
		
		caller.setBackground(new Color(255, 127, 127)); // light red
		
	}
	
	private void clearError(JComponent caller) {
		
		caller.setBackground(Color.WHITE);
		
	}
	
	private void submitData() throws SQLException {
		
		for (int table = 0; table < submissionFields.length; table++) {
			
			// instantiate
			
			PreparedStatement statement = null;
			StringBuilder insertStatement = new StringBuilder("INSERT INTO " + TABLE_NAMES[table] + " (");
			StringBuilder attributeNames = new StringBuilder();
			StringBuilder attributeValues = new StringBuilder();
			
			// add the UserID and Date to the attributes
			
			attributeNames.append("UserID, Date");	
			attributeValues.append("1, ?");
			
			// add any attributes which were input into the insert statements
			
			for (int attribute = 0; attribute < submissionFields[table].length; attribute++) {
				String input = submissionFields[table][attribute].getText();
				if (input.length() > 0) {
					attributeNames.append(", " + this.getAttributeName(table, attribute));
					attributeValues.append(", " + input);
				}
			}
			
			// create the full statement

			insertStatement.append(attributeNames);
			insertStatement.append(") VALUES (");
			insertStatement.append(attributeValues);
			insertStatement.append(");");
			
			// turn it into a prepared statement
			
			statement = database.prepareStatement(insertStatement.toString());
			statement.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			
			// execute statement
			
			statement.executeUpdate();
			
		}
		
	}
	
	private String getAttributeName(int table, int attribute) {
		
		StringBuilder attributeName = new StringBuilder();
		String attributeWords[] = FIELD_NAMES[table][attribute].split(" ");
		
		// Capitalize the first letter of each word
		for (String word : attributeWords) {
			attributeName.append(word.substring(0, 1) + word.substring(1));
		}
		
		return attributeName.toString();
		
	}
	
	private void displaySuccess(String message) {
		
		this.displaySuccess("Success!", message);
		
	}
	
	private void displaySuccess(String title, String message) {
		
		this.displayPopup(false, title, message);
		
	}
	
	private void displayError(String message) {
		
		this.displayError("Error!", message);
		
	}
	
	private void displayError(String title, String message) {
		
		this.displayPopup(true, title, message);
		
	}
	
	private void displayPopup(boolean isError, String title, String message) {
		
		JOptionPane.showMessageDialog(null, message, title, (isError ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE));
		
	}
	private void displayAll()
	{
		FitnessBuddy f = new FitnessBuddy();
		int userID = Integer.parseInt(f.getUserIDInDB(database, user.getText()));
		String date = year1.getText() + "-" + month1.getText() + "-" + day1.getText();
		String queryStr = "SELECT * FROM Sleep WHERE UserID = ? AND `Date` = ?";
		ResultSet resultSet = null;

		PreparedStatement query;
		try {
			query = database.prepareStatement(queryStr);
			query.setInt(1, userID);
			query.setDate(2,new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));


			resultSet = query.executeQuery();

			if (resultSet.next()) 
			{
				quality1.setText(quality1.getText() + resultSet.getString(4));
				hour1.setText(hour1.getText() + resultSet.getString(5));
			}

		} catch (SQLException | ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		queryStr = "SELECT * FROM Nutrition  WHERE UserID = ? AND `Date` = ?";
		try {
			query = database.prepareStatement(queryStr);
			query.setInt(1, userID);
			query.setDate(2,new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));


			resultSet = query.executeQuery();

			if (resultSet.next()) 
			{
				calorie1.setText(calorie1.getText() + resultSet.getString(4));
				unsaturatedFat1.setText(unsaturatedFat1.getText() + resultSet.getString(5));
				saturatedFat1.setText(saturatedFat1.getText() + resultSet.getString(6));
				protein1.setText(protein1.getText() + resultSet.getString(7));
				fiber1.setText(fiber1.getText() + resultSet.getString(8));
				carbohydrate1.setText(carbohydrate1.getText() + resultSet.getString(9));
			}

		} catch (SQLException | ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		queryStr = "SELECT * FROM BodyMeasurements  WHERE UserID = ? AND `Date` = ?";
		try {
			query = database.prepareStatement(queryStr);
			query.setInt(1, userID);
			query.setDate(2,new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));


			resultSet = query.executeQuery();

			if (resultSet.next()) 
			{
				weight1.setText(weight1.getText() + resultSet.getString(4));
				height1.setText(height1.getText() + resultSet.getString(5));
				bodyFatPercentage1.setText(bodyFatPercentage1.getText() + resultSet.getString(6));
				bmi1.setText(bmi1.getText() + resultSet.getString(7));
				waistSize1.setText(waistSize1.getText() + resultSet.getString(8));
			}

		} catch (SQLException | ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		queryStr = "SELECT * FROM Activity  WHERE UserID = ? AND `Date` = ?";
		try {
			query = database.prepareStatement(queryStr);
			query.setInt(1, userID);
			query.setDate(2,new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));


			resultSet = query.executeQuery();

			if (resultSet.next()) 
			{
				activity1.setText(activity1.getText() + resultSet.getString(4));
				duration1.setText(duration1.getText() + resultSet.getString(5));
				caloriesBurned1.setText(caloriesBurned1.getText() + resultSet.getString(6));
			}

		} catch (SQLException | ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
