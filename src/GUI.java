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
	private JFrame diet, sleep, act, body;
	private Button log, back, sign, dietB, sleepB, actB, bodyB;
	private Button dietBack, sleepBack, actBack, bodyBack;
	private Button signBack;
	
	public void makeWindow()
	{
		//Create a login window
		logWindow = new JFrame("FITNESS BUDDY");
		logWindow.setSize(600, 600);
		logWindow.setLocation(200, 20);
		logWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logWindow.setLayout(new GridLayout(8, 8));
		
		//Create text field and button
		JTextField user = new JTextField("Username", 20);
		JPasswordField pass = new JPasswordField("Password", 20);
		log = new Button("LOG IN");
		sign = new Button("NEW USER? SIGN UP!");
		log.addActionListener(this);
		sign.addActionListener(this);
		
		logWindow.add(user);
		logWindow.add(pass);
		logWindow.add(log);
		logWindow.add(sign);
		
		//Create a sign in window
		signWindow = new JFrame("SIGN UP");
		signWindow.setSize(600, 600);
		signWindow.setLocation(200, 20);
		signWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signWindow.setLayout(new GridLayout(8, 8));
		
		//Create text file for sign up window
		JTextField firstName = new JTextField("First Name", 20);
		JTextField lastName = new JTextField("Last Name", 20);
		JTextField email = new JTextField("Email Address", 20);
		JTextField user1 = new JTextField("Username", 20);
		JPasswordField pass1 = new JPasswordField("Password", 20);
		JPasswordField pass2 = new JPasswordField("Password", 20);
		signBack = new Button("Login Window");
		signBack.addActionListener(this);
		
		signWindow.add(firstName);
		signWindow.add(lastName);
		signWindow.add(email);
		signWindow.add(user1);
		signWindow.add(pass1);
		signWindow.add(pass2);
		signWindow.add(signBack);
		
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
		back = new Button("LOG OUT");
		
		dietB.addActionListener(this);
		sleepB.addActionListener(this);
		actB.addActionListener(this);
		bodyB.addActionListener(this);
		back.addActionListener(this);
		
		mainMenu.add(dietB);
		mainMenu.add(sleepB);
		mainMenu.add(actB);
		mainMenu.add(bodyB);
		mainMenu.add(back);
		
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
		
		sleep = new JFrame("SLEEP");
		sleep.setSize(600, 600);
		sleep.setLocation(200, 20);
		sleep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sleep.setLayout(new GridLayout(8, 8));
		
		//Sleep function
		sleepBack = new Button("MAIN MENU");
		sleepBack.addActionListener(this);
		sleep.add(sleepBack);
		
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
		
		body = new JFrame("Main Menu");
		body.setSize(600, 600);
		body.setLocation(200, 20);
		body.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		body.setLayout(new GridLayout(8, 8));
		
		//Body function
		bodyBack = new Button("MAIN MENU");
		bodyBack.addActionListener(this);
		body.add(bodyBack);
		
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
	}
	//Main method
		public static void main(String[] args) 
		{
			GUI g = new GUI();
			g.makeWindow();
		}
}
