import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameOver extends JFrame {

	
	//Unused Class
	
	public GameOver() {

		setTitle("Game Over");
		setBounds(600,250,400,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);

		JButton Exit = new JButton("Return to Main Menu");
		Exit.setBounds(250, 100, 100, 50);
		Exit.addActionListener(new btnListener());
		Exit.setActionCommand("Exit");
		cp.add(Exit);
		
		setVisible(true);
	}


	private class btnListener implements ActionListener //implements the Start and Exit buttons
	{
		public void actionPerformed(ActionEvent e) { //closes entire program
			if ("Exit".equals(e.getActionCommand())) {
				Menu.M.setVisible(true);
				Menu.G.stop();
				Menu.I.dispose();
				dispose();
			}
		}
	}	



}

