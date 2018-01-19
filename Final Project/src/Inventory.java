import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;

public class Inventory extends JFrame{

	//Stores Values representing items in inventory
	private int[] items = new int[10];
	private JLabel[] itemLabels = new JLabel[10];
	private ImageIcon[] itemImages = new ImageIcon[5];

	private Inventory(){

		super();

		//JLabel background = new JLabel(new ImageIcon("Resources/inventory-grid.png"));
		//setContentPane(background);
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

		itemImages[0] = new ImageIcon();
		itemImages[1] = new ImageIcon("Resources/Item-1-Damage.png");
		itemImages[2] = new ImageIcon();
		itemImages[3] = new ImageIcon();
		itemImages[4] = new ImageIcon();
		
		//test----------------------------
		refreshInventory();
		
		setVisible(true);

	}

	/**
	 * Adds an item to the first open inventory spot
	 * @param type
	 */
	public void addItem(int type){

		for(int x = 0; x < 10; x++){

			//Checks if the spot is empty
			if(items[x] == 0){
				//Assigns the new item to a spot
				items[x] = type;
				break;
			}
		}
	}

	public void refreshInventory(){

		for(int x = 0; x < 10; x++){
			
			//test---------------------------
			items[x] = 1;
			
			itemLabels[x] = new JLabel();
			
			if(x < 5){
				itemLabels[x].setBounds( 20 + (x * 50), 20, 70,70);
			} else {
				itemLabels[x].setBounds( 20 + ((x - 5) * 50) , 120, 70,70);
			}
			
			if(items[x] != 0){
					itemLabels[x].setIcon(itemImages[items[x]]);
			}
			
			Pane.add //------------
			itemLabels[x].setVisible(true);
			
		}
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
