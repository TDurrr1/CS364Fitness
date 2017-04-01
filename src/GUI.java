import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener
{	
	//Main window, button panel, buttons and timer
	private JFrame logWindow, mainMenu, signWindow;
	private JFrame diet, sleep, act, body;
	private Button log, back, sign, dietB, sleepB, actB, bodyB;
	private Timer t;
	
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
		log = new Button("Log In");
		sign = new Button("NEW USER? SIGN UP!");
		
		logWindow.add(user);
		logWindow.add(pass);
		logWindow.add(log);
		logWindow.add(sign);
		
		//Create a sign in window
		
		//Create a main menu window
		mainMenu = new JFrame("Main Menu");
		mainMenu.setSize(600, 600);
		mainMenu.setLocation(200, 20);
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenu.setLayout(new GridLayout(8, 8));
		
		//All buttons we need
		JButton dietB = new JButton("DIET");
		JButton sleepB = new JButton("SLEEP");
		JButton actB = new JButton("ACTIVITY");
		JButton bodyB = new JButton("BODY");
		JButton back = new JButton("MAIN MENU");
		
		
		mainMenu.add(dietB);
		mainMenu.add(sleepB);
		mainMenu.add(actB);
		mainMenu.add(bodyB);
		
		//Create diet, sleep, activity, body windows
		diet = new JFrame("Main Menu");
		diet.setSize(600, 600);
		diet.setLocation(200, 20);
		diet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		diet.setLayout(new GridLayout(8, 8));

		//Diet function
		diet.add(back);
		
		sleep = new JFrame("Main Menu");
		sleep.setSize(600, 600);
		sleep.setLocation(200, 20);
		sleep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sleep.setLayout(new GridLayout(8, 8));
		
		//Sleep function
		sleep.add(back);
		
		act = new JFrame("Main Menu");
		act.setSize(600, 600);
		act.setLocation(200, 20);
		act.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		act.setLayout(new GridLayout(8, 8));
		
		//Act function
		act.add(back);
		
		body = new JFrame("Main Menu");
		body.setSize(600, 600);
		body.setLocation(200, 20);
		body.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		body.setLayout(new GridLayout(8, 8));
		
		//Body function
		body.add(back);
		
		//Create Timer
		t = new Timer(500, this);
		t.start();
		
		logWindow.setVisible(true);
		//mainMenu.setVisible(true);
	}

	
	

	


	//Main method
	public static void main(String[] args) 
	{
		GUI g = new GUI();
		g.makeWindow();
	}







	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == log)
		{
			logWindow.setVisible(false);
			mainMenu.setVisible(true);
			//diet.setVisible(true);
			//sleep.setVisible(true);
			//act.setVisible(true);
			//body.setVisible(true);
			System.out.println("OK");
		}
		if (e.getSource() == back)
		{
			logWindow.setVisible(true);
			//mainMenu.setVisible(true);
			//diet.setVisible(true);
			//sleep.setVisible(true);
			//act.setVisible(true);
			//body.setVisible(true);
		}
		if (e.getSource() == sign)
		{
			
		}
		if (e.getSource() == dietB)
		{
			
		}
		if (e.getSource() == actB)
		{
			
		}
		if (e.getSource() == bodyB)
		{
			
		}
		if (e.getSource() == sleepB)
		{
			
		}
		
		
	}

}