
import java.awt.Container;

//public class Menu {
	//MAKE THIS NEW MAIN SO IT STARTS BEFORE GUI DOES
	import javax.swing.*;
	public class Menu extends JFrame{
		//hello is this working
		public Menu () {

			JLabel background = new JLabel(new ImageIcon("back.jpg"));
			setContentPane(background);
			setTitle("Game");
			setSize(400,400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setResizable(false);
			Container cp = getContentPane();
			cp.setLayout(null); 
			JButton Start = new JButton("Start");
			Start.setActionCommand("Start");
			JButton Exit = new JButton("Exit");
			Exit.setActionCommand("Exit");
			Start.setBounds(100, 300, 20, 20);
			Exit.setBounds(300, 300, 20, 20);
			add(Start);
			add(Exit);
			setVisible (true);

		}
/*
		public void actionPerformed(ActionEvent e) {
			if ("Exit".equals(e.getActionCommand())) {
				System.exit();
			}
			if ("Start".equals(e.getActionCommand())) {
				new GUI();
				System.exit();
			}

			public static void main(String args[]){
				new Menu();
			}
		}
*/
	}
