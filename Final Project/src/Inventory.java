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

		//Adds all images to an array
		itemImages[0] = new ImageIcon();
		itemImages[1] = new ImageIcon("Resources/Item-1-Damage.png");
		itemImages[2] = new ImageIcon("Resources/Item-2-Speed.png");
		itemImages[3] = new ImageIcon("Resources/Item-3-Resistance.png");
		itemImages[4] = new ImageIcon("Resources/Item-4-FireRate.png");

		//Builds icons for each slot in the inventory
		for(int x = 0; x < 10; x++){

			itemLabels[x] = new JLabel();

			//Positions each icon properly on the background
			if(x < 5){
				itemLabels[x].setBounds( 23 + (x * 98), 23, 70,70);
			} else {
				itemLabels[x].setBounds( 23 + ((x - 5) * 98) , 122, 70,70);
			}

			if(items[x] != 0){
				itemLabels[x].setIcon(itemImages[items[x]]);
			}

			Pane.add(itemLabels[x]);
			itemLabels[x].setVisible(true);

		}

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

	/**
	 * Refreshed the JLabels to show acquired items
	 */
	public void refreshIcons(){

		for(int x = 0; x < 10; x++){

			//Checks for an item in the array
			if(items[x] != 0){
				
				//Adds the items image to its corresponding JLabel
				itemLabels[x].setIcon(itemImages[items[x]]);
			}
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
