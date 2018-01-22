
import java.awt.Container;
import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.awt.event.KeyListener;



import javax.swing.*;
public class Menu extends JFrame{
	
	//Static variables for referencing from other classes
	public static Game G;
	public static Menu M;
	public static Inventory I;
	
	public Menu () {
		//Setting the background (*not working atm because need good background in resource folder)
		JLabel background = new JLabel(new ImageIcon("map.png"));
		//general setting up of JFrame
		setContentPane(background);
		setTitle("Game");
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null); //layout

		//creating of the buttons and setting their action listeners locations and adding them to pane
		JButton Start = new JButton("Start");
		add(Start);
		Start.addActionListener(new btnListener());
		Start.setActionCommand("Start");
		JButton Exit = new JButton("Exit");
		add(Exit);
		Exit.addActionListener(new btnListener());
		Exit.setActionCommand("Exit");
		Start.setBounds(50, 300, 100, 50);
		Exit.setBounds(250, 300, 100, 50);

		// setting Jframe to Visible
		setVisible (true);

	}
	
	
	private class btnListener implements ActionListener //implements the Start and Exit buttons
	{
		public void actionPerformed(ActionEvent e) { //closes entire program
			if ("Exit".equals(e.getActionCommand())) {
				System.exit(0);
			}
			if ("Start".equals(e.getActionCommand())) { //disposes of current Menu class and Loads the GUI class
				M.setVisible(false);
				G = new Game();
				I = new Inventory();
				G.start();

			}
		}	

	}
	public static void main(String args[]){
		M = new Menu();

	}
}


