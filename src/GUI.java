import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener
{	
	//Main window, button panel, buttons and timer
	private JFrame logWindow, mainMenu, signWindow;
	private JFrame diet, sleep, act, body, submit;
	private Button log, back, sign, dietB, sleepB, actB, bodyB, submitB, submitUser;
	private Button dietBack, sleepBack, actBack, bodyBack, submitBack, submitEnter;
	private Button signBack;
	private JTextField user;
	private JTextField firstName, middleName, lastName, location, email, user1;
	private JPasswordField pass, pass1, pass2;
	private JTextField day, month, year, calorie, unsaturatedFat, saturatedFat, protein, fiber, carbohydrate,
	quality, hour, weight, height, bodyFatPercentage, bmi, waistSize, duration, caloriesBurned, activity;

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


		dietB.addActionListener(this);
		sleepB.addActionListener(this);
		actB.addActionListener(this);
		bodyB.addActionListener(this);
		submitB.addActionListener(this);
		back.addActionListener(this);

		mainMenu.add(dietB);
		mainMenu.add(sleepB);
		mainMenu.add(actB);
		mainMenu.add(bodyB);
		mainMenu.add(submitB);
		mainMenu.add(back);
	}

	public void diet()
	{
		//Create diet, sleep, activity, body windows
		diet = new JFrame("DIET");
		diet.setSize(600, 600);
		diet.setLocation(200, 20);
		diet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		diet.setLayout(new GridLayout(8, 8));

		//Diet function
		dietBack = new Button("MAIN MENU");
		dietBack.addActionListener(this);
		diet.add(dietBack);
	}

	public void activity()
	{
		//Activity window
		act = new JFrame("Main Menu");
		act.setSize(600, 600);
		act.setLocation(200, 20);
		act.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		act.setLayout(new GridLayout(8, 8));

		//Act function
		actBack = new Button("MAIN MENU");
		actBack.addActionListener(this);
		act.add(actBack);
	}

	public void sleep()
	{
		sleep = new JFrame("SLEEP");
		sleep.setSize(600, 600);
		sleep.setLocation(200, 20);
		sleep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sleep.setLayout(new GridLayout(8, 8));

		//Sleep function
		sleepBack = new Button("MAIN MENU");
		sleepBack.addActionListener(this);
		sleep.add(sleepBack);
	}

	public void body()
	{
		body = new JFrame("Main Menu");
		body.setSize(600, 600);
		body.setLocation(200, 20);
		body.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		body.setLayout(new GridLayout(8, 8));

		//Body function
		bodyBack = new Button("MAIN MENU");
		bodyBack.addActionListener(this);
		body.add(bodyBack);
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

	public void makeWindow()
	{
		logWindow();
		signWindow();
		mainMenu();
		diet();
		activity();
		sleep();
		body();
		submitWindow();
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
		if (e.getSource() == dietB)
		{
			diet.setVisible(true);
			mainMenu.setVisible(false);
		}
		if (e.getSource() == actB)
		{
			act.setVisible(true);
			mainMenu.setVisible(false);
		}
		if (e.getSource() == bodyB)
		{
			body.setVisible(true);
			mainMenu.setVisible(false);
		}
		if (e.getSource() == sleepB)
		{
			sleep.setVisible(true);
			mainMenu.setVisible(false);
		}
		if (e.getSource() == signBack)
		{
			logWindow.setVisible(true);
			signWindow.setVisible(false);
		}	
		if (e.getSource() == dietBack)
		{
			diet.setVisible(false);
			mainMenu.setVisible(true);
		}
		if (e.getSource() == actBack)
		{
			act.setVisible(false);
			mainMenu.setVisible(true);
		}
		if (e.getSource() == bodyBack)
		{
			body.setVisible(false);
			mainMenu.setVisible(true);
		}
		if (e.getSource() == sleepBack)
		{
			sleep.setVisible(false);
			mainMenu.setVisible(true);
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

		}
		if (e.getSource() == submitUser)
		{
			signWindow.setVisible(false);
			logWindow.setVisible(true);
		}

	}
}
