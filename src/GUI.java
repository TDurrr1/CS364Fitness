import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
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
import java.sql.ResultSet;

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
	
	public static final int MINIMUM = 0;
	public static final int MAXIMUM = 1;
	
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
     *         - FIELD_LIMITS: The minimum and maximum value allowed for each field. If the corresponding value in FIELD_UNITS
     *           is null, then maximum stores the max LENGTH of the String allowed.
	 *           
	 *     Thus, this format is the way in which data is accessed:
	 *         FIELD_NAMES[<table>][<attribute>]
	 *         
	 *     For example, to find the name for the unit of the second attribute in the BodyMeasurements table, use...
	 *         FIELD_UNITS[BODYMEASUREMENTS][1]
	 *         
	 *     To find the maximum allowed value for the above attribute, use...
	 *         FIELD_LIMITS[BODYMEASUREMENTS][1][MAXIMUM]
	 */
	private final String FIELD_NAMES[][] = {
			{"Activity name", "Activity duration", "Calories burned"},
			{"Weight", "Height", "Body fat percentage", "Waist circumference"},
			{"Caloric intake", "Unsaturated fat", "Saturated fat", "Protein", "Fiber", "Carbohydrates"},
			{"Sleep quality", "Sleep length"}};
	private final String FIELD_UNITS[][] = {
			{null, "minutes", "kilocalories"},
			{"pounds", "inches", "%", "inches"},
			{"kilocalories", "grams", "grams", "grams", "grams", "grams"},
			{"1-10", "hours"}};
	private final int FIELD_LIMITS[][][] = {
			{{-1, 50},   {1, 600},  {1, 1000}},
			{{25, 999}, {36, 108}, {2, 50}, {15, 99}},
			{{1, 9999}, {0, 310},  {0, 80}, {0, 280}, {0, 150}, {0, 1625}},
			{{1, 10},   {0, 24}}};
	private final String TABLE_NAMES[] = {"Activity", "BodyMeasurements", "Nutrition", "Sleep"};
	
	/*** Class variables ***/
	
	private Connection database = null;
	
	//Main window, button panel, buttons and timer
	private JFrame logWindow, mainMenu, signWindow, displayWindow, submitWindow;
	private Button log, back, sign, submitB, submitUser, display;
	private Button submitBack, submitEnter, displayB;
	private Button signBack;
	private JTextField user;
	private JTextField firstName, middleName, lastName, location, email, user1;
	private JPasswordField pass, pass1, pass2;
	private JSpinner date;
	private JLabel displayFields[][] = new JLabel[FIELD_NAMES.length][];
	private JLabel totalCaloriesBurnedField = new JLabel("", JLabel.CENTER);
	private JLabel BMIField = new JLabel("", JLabel.CENTER);
	private JLabel averageCaloricIntakeField = new JLabel("", JLabel.CENTER);
	private JLabel averageSleepQualityField = new JLabel("", JLabel.CENTER);
	private JTextField submissionFields[][] = new JTextField[FIELD_NAMES.length][];

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
		pass.setText(null);
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

		mainMenu.add(date);
		mainMenu.add(display);
		mainMenu.add(submitB);
		mainMenu.add(back);
	}

	public void submitWindow()
	{		
		for (int i = 0; i < FIELD_NAMES.length; i++) {
			submissionFields[i] = new JTextField[FIELD_NAMES[i].length];
		}
		
		// Instantiate submission window
		
		submitWindow = new JFrame("Data Submission");
		submitWindow.setSize(500, 800);
		submitWindow.setLocationRelativeTo(null);
		submitWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		submitWindow.setLayout(new GridLayout(0, 2));

		// Instantiate date selection
		
		date = new JSpinner(new SpinnerDateModel(new Date(), new Date(946684800000L), new Date(), 0));
		date.setEditor(new JSpinner.DateEditor(date, "MMMM dd, yyyy"));
		
		// Instantiate all submission fields
		
		for (int table = 0; table < FIELD_NAMES.length; table++) {
			for (int attribute = 0; attribute < FIELD_NAMES[table].length; attribute++) {
				submissionFields[table][attribute] = new JTextField("");
				submissionFields[table][attribute].addFocusListener(this);
			}
		}
		
		// Instantiate buttons
		
		submitEnter = new Button("SUBMIT DATA");
		submitBack = new Button("MAIN MENU");
		submitEnter.addActionListener(this);
		submitBack.addActionListener(this);

		// Add data entry fields, using empty JLabels and JSeparators for visual adjustments

		submitWindow.add(new JLabel());
		submitWindow.add(new JLabel());
		submitWindow.add(new JLabel("Date: ", JTextField.RIGHT));
		submitWindow.add(date);
		for (int table = 0; table < submissionFields.length; table++) {
			submitWindow.add(new JLabel());
			submitWindow.add(new JLabel());
			submitWindow.add(new JSeparator(JSeparator.HORIZONTAL));
			submitWindow.add(new JSeparator(JSeparator.HORIZONTAL));
			for (int attribute = 0; attribute < submissionFields[table].length; attribute++) {
				submitWindow.add(new JLabel(this.getFieldLabelText(table, attribute) + ":  ", JLabel.RIGHT));
				submitWindow.add(submissionFields[table][attribute]);
			}
		}
		submitWindow.add(new JLabel());
		submitWindow.add(new JLabel());
		
		// Add buttons

		submitWindow.add(submitEnter);
		submitWindow.add(submitBack);

	}

	public void displayWindow()
	{
		String formattedDate = new SimpleDateFormat("MMMM dd, yyyy").format(date.getModel().getValue());
		
		// Instantiate submission window
		
		displayWindow = new JFrame("Data Submission");
		displayWindow.setSize(500, 800);
		displayWindow.setLocationRelativeTo(null);
		displayWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		displayWindow.setLayout(new GridLayout(0, 2));
		
		// Instantiate button
		
		displayB = new Button("MAIN MENU");
		displayB.addActionListener(this);

		// Add display fields, using empty JLabels and JSeparators for visual adjustments

		displayWindow.add(new JLabel());
		displayWindow.add(new JLabel());
		displayWindow.add(new JLabel("Date: ", JTextField.RIGHT));
		displayWindow.add(new JLabel(formattedDate, JLabel.CENTER));
		for (int table = 0; table < submissionFields.length; table++) {
			displayWindow.add(new JLabel());
			displayWindow.add(new JLabel());
			displayWindow.add(new JSeparator(JSeparator.HORIZONTAL));
			displayWindow.add(new JSeparator(JSeparator.HORIZONTAL));
			for (int attribute = 0; attribute < submissionFields[table].length; attribute++) {
				displayWindow.add(new JLabel(this.getFieldLabelText(table, attribute) + ":  ", JTextField.RIGHT));
				displayWindow.add(displayFields[table][attribute]);
			}
			
			// Add complex query fields
			
			if (table == ACTIVITY) {
				displayWindow.add(new JLabel("Total Calories Burned:  ", JLabel.RIGHT));
				displayWindow.add(totalCaloriesBurnedField);
			}
			else if (table == BODYMEASUREMENTS) {
				displayWindow.add(new JLabel("Body Mass Index:  ", JLabel.RIGHT));
				displayWindow.add(BMIField);
			}
			else if (table == NUTRITION) {
				displayWindow.add(new JLabel("Average Caloric Intake:  ", JLabel.RIGHT));
				displayWindow.add(averageCaloricIntakeField);
			}
			else if (table == SLEEP) {
				displayWindow.add(new JLabel("Average Sleep Quality:  ", JLabel.RIGHT));
				displayWindow.add(averageSleepQualityField);
			}			
		}
		displayWindow.add(new JLabel());
		displayWindow.add(new JLabel());
		
		displayWindow.add(displayB);
	}

	public void makeGUI()
	{
		database = FitnessBuddy.connect(GUI.DEFAULT_CONNECTION_URL);
		
		// instantiate date

		date = new JSpinner(new SpinnerDateModel(new Date(), new Date(946684800000L), new Date(), 0));
		date.setEditor(new JSpinner.DateEditor(date, "MMMM dd, yyyy"));
		
		// Instantiate displayFields
		
		for (int table = 0; table < FIELD_NAMES.length; table++) {
			displayFields[table] = new JLabel[FIELD_NAMES[table].length];
			for (int attribute = 0; attribute < FIELD_NAMES[table].length; attribute++) {
				displayFields[table][attribute] = new JLabel("", JLabel.CENTER);
			}
		}
		
		// form each window
		
		logWindow();
		signWindow();
		mainMenu();
		submitWindow();
		displayWindow();
		
		logWindow.setVisible(true);
	}

	private String getFieldLabelText(int table, int attribute) {
		
		String defaultText = "";
		
		if (table < FIELD_NAMES.length && attribute < FIELD_NAMES[table].length) {
			defaultText = FIELD_NAMES[table][attribute];
			if (FIELD_UNITS[table][attribute] != null) {
				defaultText = defaultText + " (" + FIELD_UNITS[table][attribute] + ")";
			}
		}
		
		return defaultText;
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == log)
		{
			if (!(FitnessBuddy.isItemInDB(database, user.getText())))
			{
				displayError("We cannot find your username");
			}
			else if (FitnessBuddy.isItemInDB(database, user.getText()) && !FitnessBuddy.getPasswordInDB(database, user.getText()).equals(pass.getText()))
			{
				displayError("Wrong password");
			}
			else if (FitnessBuddy.isItemInDB(database, user.getText()) && FitnessBuddy.getPasswordInDB(database, user.getText()).equals(pass.getText()))
			{
				pass.setText(null);
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
			submitWindow.setVisible(true);
		}
		if (e.getSource() == submitBack)
		{
			submitWindow.setVisible(false);
			mainMenu.setVisible(true);
		}
		if (e.getSource() == submitEnter)
		{
			try {
				this.submitData();
				this.displaySuccess("Data successfully submitted.");
				submitWindow.setVisible(false);
				mainMenu.setVisible(true);
			}
			catch (Exception ex) {
				this.displayError("Data entry error", "Data not submitted. " + ex.getMessage());
			}
		}
		if (e.getSource() == submitUser)
		{
			signWindow.setVisible(false);
			logWindow.setVisible(true);
		}
		if (e.getSource() == display)
		{
			this.displayAll();
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
			int maxInputLength;
			
			if (FIELD_UNITS[table][attribute] == null) {
				maxInputLength = Math.min(FIELD_LIMITS[table][attribute][MAXIMUM], formattedInput.length());
			}
			else {
				maxInputLength = Math.min(String.valueOf(FIELD_LIMITS[table][attribute][MAXIMUM]).length(), formattedInput.length());
			}			
			formattedInput = formattedInput.substring(0, maxInputLength);
			submissionFields[table][attribute].setText(formattedInput);
		}
		
	}
	
	private void setError(JComponent caller) {
		
		caller.setBackground(new Color(255, 127, 127)); // light red
		
	}
	
	private void clearError(JComponent caller) {
		
		caller.setBackground(Color.WHITE);
		
	}
	
	private void submitData() throws Exception {
		
		for (int table = 0; table < submissionFields.length; table++) {
			
			// instantiate
			
			PreparedStatement statement = null;
			StringBuilder insertStatement = new StringBuilder("INSERT INTO " + TABLE_NAMES[table] + " (");
			StringBuilder attributeNames = new StringBuilder();
			StringBuilder attributeValues = new StringBuilder();
			boolean valuesWereEntered = false;
			
			// add the UserID and Date to the attributes
			
			attributeNames.append("UserID, Date");	
			attributeValues.append("\"" + FitnessBuddy.getUserID(database, user.getText()) + "\", ?");
			
			// validate input
			
			this.validateAllInput();
			
			// add any attributes which were input into the insert statements
			
			for (int attribute = 0; attribute < submissionFields[table].length; attribute++) {
				String input = submissionFields[table][attribute].getText();
				
				if (input.length() > 0) {
					attributeNames.append(", " + this.getAttributeName(table, attribute));
					if (FIELD_UNITS[table][attribute] == null) {
						attributeValues.append(", \"" + input + "\"");
					}
					else {
						attributeValues.append(", " + input);
					}
					valuesWereEntered = true;
				}
			}
			
			// this only needs to be called if some values have been entered
			
			if (valuesWereEntered) {
				
				// create the full statement

				insertStatement.append(attributeNames);
				insertStatement.append(") VALUES (");
				insertStatement.append(attributeValues);
				insertStatement.append(");");
				
				// turn it into a prepared statement
				
				java.sql.Date dateEntered = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(date.getModel().getValue())).getTime());
				
				statement = database.prepareStatement(insertStatement.toString());
				statement.setDate(1, dateEntered);
				
				// execute statement
				
				statement.executeUpdate();
			}
		}
		
	}
	
	private void validateAllInput() throws Exception {
		
		for (int table = 0; table < submissionFields.length; table++) {
			for (int attribute = 0; attribute < submissionFields[table].length; attribute++) {
				if (FIELD_UNITS[table][attribute] != null) {
					String input = submissionFields[table][attribute].getText();
					if (input.length() > 0) {
						if (this.isValidInteger(input, FIELD_LIMITS[table][attribute][MINIMUM], FIELD_LIMITS[table][attribute][MAXIMUM])) {
						// good to go!
						}
						else {
						// not within range; mark invalid
						this.setError(submissionFields[table][attribute]);
						throw new Exception(FIELD_NAMES[table][attribute] + " must be between " + 
						                    FIELD_LIMITS[table][attribute][MINIMUM] + " and " +
								            FIELD_LIMITS[table][attribute][MAXIMUM] + ", inclusive.");
						}
					}
					
				}
			}
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
	
	public boolean isValidInteger(String input) {
		
		boolean isValid = false;
		
			try {
				Integer.parseInt(input);
				isValid = true;
			}
			catch (Exception e) {}
			
		return isValid;
		
	}
	
	public boolean isValidInteger(String input, int minimum, int maximum) {
		
		boolean isValid = false;
		
		if (isValidInteger(input) && valueIsInRange(new Double(input), minimum, maximum)) {
			isValid = true;
		}
			
		return isValid;
		
	}
	
	public boolean valueIsInRange(double input, double minimum, double maximum) {
		
		boolean isInRange = false;
		
		if (input >= minimum && input <= maximum) {
			isInRange = true;
		}
		
		return isInRange;
		
	}

	private void displayAll()
	{
		FitnessBuddy f = new FitnessBuddy();
		int userID = Integer.parseInt(f.getUserID(database, user.getText()));
		String date = new SimpleDateFormat("yyyy-MM-dd").format(this.date.getModel().getValue());
		
		for (int table = 0; table < TABLE_NAMES.length; table++)
		{
			try {
				String queryStr = "SELECT * FROM " + TABLE_NAMES[table] + " WHERE `UserID` = ? AND `Date` = ?";
				PreparedStatement query = database.prepareStatement(queryStr);
				ResultSet resultSet = null;

				query.setInt(1, userID);
				query.setDate(2, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));

				resultSet = query.executeQuery();

				if (resultSet.next())
				{
					for (int attribute = 0; attribute < displayFields[table].length; attribute++)
					{
						displayFields[table][attribute].setText(resultSet.getString(attribute + 4));
					}
				}
				this.evaluateComplexQueries(table);
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
		
	}

	private void evaluateComplexQueries(int table) throws Exception {

		int userID = Integer.parseInt(FitnessBuddy.getUserID(database, user.getText()));
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date.getModel().getValue());
		java.sql.Date sqlDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(dateString).getTime());
		String query = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (table == ACTIVITY) {

			// Calculate total calories burned

			query = "SELECT sum(CaloriesBurned) FROM Activity WHERE UserID = ?;";
			statement = database.prepareStatement(query);
			statement.setInt(1, userID);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				totalCaloriesBurnedField.setText("" + resultSet.getInt(1));
			}
		}
		else if (table == BODYMEASUREMENTS) {

			// Calculate BMI

			query = "SELECT (Weight * POWER(Height, 2) * .000028125) AS `BMI` FROM BodyMeasurements WHERE `UserID` = ? AND `Date` = ?;";
			statement = database.prepareStatement(query);
			statement.setInt(1, userID);
			statement.setDate(2, sqlDate);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				BMIField.setText(String.format("%.2f", resultSet.getDouble(1)));
			}
		}
		else if (table == NUTRITION) {

			// Calculate average caloric intake

			query = "SELECT avg(CaloricIntake) FROM Nutrition WHERE UserID = ?;";
			statement = database.prepareStatement(query);
			statement.setInt(1, userID);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				averageCaloricIntakeField.setText("" + resultSet.getInt(1));
			}
		}
		else if (table == SLEEP) {

			// Calculate average sleep quality

			query = "SELECT avg(SleepQuality) FROM Sleep WHERE UserID = ?;";
			statement = database.prepareStatement(query);
			statement.setInt(1, userID);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				averageSleepQualityField.setText("" + resultSet.getInt(1));
			}
		}
		
	}
	
}
