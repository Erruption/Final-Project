import java.awt.*;
import javax.swing.*;

public class Inventory extends JFrame{
	
	public Inventory(){
		super();
		
		
		Container Pane = getContentPane();
		Pane.setLayout(null);

		//Frame
		setTitle("Inventory");
		setSize(350,135);
		setResizable(false);
		setLocation(200,200);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	//testing purposes only
	public static void main (String [] args){
		new Inventory();
	}

}
