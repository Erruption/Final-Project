import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Author Lewis Zettler
 * Create a game where you run from a a spookyy grim reaper
 * 
 *
 */
public class GUI  extends JFrame{
	private JLabel player;
	private Timer t;  //timer for scytheman movement
	private boolean moveUp;
	private boolean moveDown;
	private boolean moveLeft;
	private boolean moveRight;
	public GUI()
	{
		//JLabel background = new JLabel(new ImageIcon("Resources/map.png")); //creates a background image
		setTitle("SMOEBODIE TUCH MY SPAGUETTE");
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		addKeyListener(new KListener()); //add key listener directly to frame
		//setContentPane(new JLabel(new ImageIcon("back.jpg")));  //add to frame
		Container cp = getContentPane();
		cp.setLayout(null);  //absolute referencing to Jframe
		
		
		ImageIcon im = new ImageIcon("Resources/map.jpg");
		player = new JLabel (im);
		player.setSize(100,100);
		player.setLocation(450,400);
		cp.add (player);
		
		
		ImageIcon ii = new ImageIcon("Resources/player.jpg");
		player = new JLabel (ii);
		player.setSize(100,100);
		player.setLocation(450,400);
		cp.add (player);
		
		t = new Timer(20, new actTimer()); //define ghost movement at 200ms interval
		t.start(); //start timer
		setVisible (true);
		
	}
/**
 * a timer to work 
 * @author 85622zet
 *
 */
	private class actTimer implements ActionListener{
		int xP; //player location
		int yP; 
		int xG; //ghost location
		int yG;
		
		
		
		
		/**
		 * moves ghost towards scytheman
		 * 
		 */
		public void actionPerformed(ActionEvent e) {
			xP = player.getX();//gets player and ghost current location
			yP = player.getY();
			
			if (moveUp == true){//moves for when player is pressing a button on a timer so that it doesnt act jerky
				yP -= 5;
				player.setLocation(xP , yP);
			}
			if (moveDown == true){
				yP += 5;
				player.setLocation(xP , yP);
			}
			if (moveLeft == true){
				xP -= 5;
				player.setLocation(xP , yP);
			}
			if (moveRight == true){
				xP += 5;
				player.setLocation(xP , yP);
			}
			//if (intersects(player,monster)){
				//health goes down
			//}
		}
	}
	/**
	 * function to make an infobox
	 * @param infoMessagea
	 * @param titleBar
	 */
	public static void infoBox(String infoMessage, String titleBar)
	{
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * this is a test function to see if the hitboxes work
	 * 
	 * @param testa
	 * @param testb
	 * @return boolean
	 */
	public boolean intersects(JLabel testa, JLabel testb){
		Area areaA = new Area(testa.getBounds());
		Area areaB = new Area(testb.getBounds());
		return areaA.intersects(areaB.getBounds2D());//returs the answer
	}
	/**
	 * listents for keypresses and sets a variable so that the timer can react to it
	 * @author Lewiz settker
	 * listens to key movement on form to correspond to scytheman movement using arrow keys
	 */
	private class KListener implements KeyListener
	{
		public void keyPressed(KeyEvent e)//checks for keypresses
		{
			if (e.getKeyCode() == KeyEvent.VK_RIGHT){
				moveRight = true;
			}
			else if (e.getKeyCode() == KeyEvent.VK_LEFT){
				moveLeft = true;
			}
			else if (e.getKeyCode() == KeyEvent.VK_UP){
				moveUp = true;
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN){
				moveDown = true;
			}
		}
		public void keyTyped(KeyEvent e)
		{
			System.out.print(e.getKeyChar());
		}
		public void keyReleased(KeyEvent e)//checks when a key is released
		{
			if (e.getKeyCode() == KeyEvent.VK_RIGHT){
				moveRight = false;
			}
			else if (e.getKeyCode() == KeyEvent.VK_LEFT){
				moveLeft = false;
			}
			else if (e.getKeyCode() == KeyEvent.VK_UP){
				moveUp = false;
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN){
				moveDown = false;
			}
		}
	}
	public static void main(String args[]){
		new GUI();
	}
}
