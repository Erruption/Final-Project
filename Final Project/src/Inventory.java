import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Inventory extends JFrame{
	
	
	public Inventory(){
		
		super();
		
		JLabel background = new JLabel(new ImageIcon("Resources/inventory-grid.png"));
		setContentPane(background);
		Container Pane = getContentPane();
		Pane.setLayout(null);
		
		//Frame
		setTitle("Inventory");
		setSize(517,328);
		setResizable(false);
		setLocation(200,200);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		//Resume Button (Resumes Game)
		JButton btnResume = new JButton("Resume");
		btnResume.setBounds(7, 231, 244, 60);
		btnResume.setFont(new Font("Dialog", Font.BOLD, 20));
		btnResume.addActionListener(new btnListener()); 
		btnResume.setActionCommand("Resume");
		Pane.add(btnResume);
		
		//Quit Button (Return to main menu) not implemented
		JButton btnQuit = new JButton("Return to Menu");
		btnQuit.setBounds(262, 231, 240, 60);
		btnQuit.setFont(new Font("Dialog", Font.BOLD, 20));
		btnQuit.addActionListener(new btnListener()); 
		btnQuit.setActionCommand("Quit");
		Pane.add(btnQuit);
		

		setVisible(true);
		
		
	}
	
	private class btnListener implements ActionListener {

		public void actionPerformed (ActionEvent e){

			if (e.getActionCommand().equals("Resume")){ //Checks for an action from btnResume

		
			}
			else if (e.getActionCommand().equals("Quit")) { //Checks for an action from btnQuit

				
			}
		}
	}
	
	//testing purposes only
	public static void main (String [] args){
		new Inventory();
	}
}
