import java.awt.*;
import javax.swing.*;

public class Inventory extends JFrame{
	
	public Inventory(){
		super();
		
		JLabel background = new JLabel(new ImageIcon("inventory-grid.png"));
		setContentPane(background);
		Container Pane = getContentPane();
		Pane.setLayout(null);
		
		//Frame
		setTitle("Inventory");
		setSize(520,250);
		setResizable(false);
		setLocation(200,200);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		
	}
	
	//testing purposes only
	public static void main (String [] args){
		new Inventory();
	}

}
