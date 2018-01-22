import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;

/**
 * Nick Agnew
 *GUI serving as pause menu and visual representation of Upgrades
 */
public class Inventory extends JFrame{

	//Stores Values representing items in inventory
	private int[] items = new int[10];
	private JLabel[] itemLabels = new JLabel[10];
	private ImageIcon[] itemImages = new ImageIcon[5];

	public Inventory(){

		super();

		JLabel background = new JLabel(new ImageIcon("Resources/ItemFiles/inventory-grid.png"));
		setContentPane(background);
		Container Pane = getContentPane();
		Pane.setLayout(null);
		
		//Frame
		setTitle("Inventory");
		setSize(517,328);
		setResizable(false);
		setLocation(200,200);
		setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
		
		//Resume Button (Resumes Game)
		JButton btnResume = new JButton("Resume");
		btnResume.setBounds(7, 231, 244, 60);
		btnResume.setFont(new Font("Dialog", Font.BOLD, 20));
		btnResume.addActionListener(new btnListener()); 
		btnResume.setActionCommand("Resume");
		Pane.add(btnResume);

		//Quit Button (Return to main menu)
		JButton btnQuit = new JButton("Return to Menu");
		btnQuit.setBounds(262, 231, 240, 60);
		btnQuit.setFont(new Font("Dialog", Font.BOLD, 20));
		btnQuit.addActionListener(new btnListener()); 
		btnQuit.setActionCommand("Quit");
		Pane.add(btnQuit);

		//Adds all images to an array
		itemImages[0] = new ImageIcon();
		itemImages[1] = new ImageIcon("Resources/ItemFiles/Item-1-Damage.png");
		itemImages[2] = new ImageIcon("Resources/ItemFiles/Item-2-Speed.png");
		itemImages[3] = new ImageIcon("Resources/ItemFiles/Item-3-Resistance.png");
		itemImages[4] = new ImageIcon("Resources/ItemFiles/Item-4-FireRate.png");

		//Builds icons for each slot in the inventory
		for(int x = 0; x < 10; x++){

			itemLabels[x] = new JLabel();

			//Positions each icon properly on the background
			if(x < 5){
				itemLabels[x].setBounds( 23 + (x * 98), 23, 70,70);
			} else {
				itemLabels[x].setBounds( 23 + ((x - 5) * 98) , 122, 70,70);
			}

			//Adds proper images to JLabels
			if(items[x] != 0){
				itemLabels[x].setIcon(itemImages[items[x]]);
			}

			Pane.add(itemLabels[x]);
			itemLabels[x].setVisible(true);

		}

		setVisible(false);

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
				Menu.G.start();
				setVisible(false);
			}
			else if (e.getActionCommand().equals("Quit")) { //Checks for an action from btnQuit
				Menu.M.setVisible(true);
				//Menu.G.stop();
				Menu.I.dispose();
				
			}
		}
	}

}
