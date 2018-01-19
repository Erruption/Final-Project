import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Author Lewis Zettler
 * The visual implementation 
 * 
 *
 */
public class GUI extends Canvas implements Runnable{
private static final long serialVersionUID = 1L ;
	
	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	JFrame frame;

	public static boolean running = false;
	
	public static final String TITLE = "OMAE WA";
	public static final int WIDTH = 900;
	public static final int HEIGHT = 900;
	final static final Dimension gamDim = new Dimension(WIDTH, HEIGHT); 
	
	Tile tileTest = new Tile(0 , 0 , this);
	
	
	public void run() {
		while(running) {
			tick();
			render();
		}
	}
	

	
	private synchronized void start() {
		running = true;
		Thread thread = new Thread(this);
		thread.start();
		
	}
	private synchronized void stop() {
		
		running = false;
		
	}
	public GUI() {
		
		
		setMaximumSize(gamDim);
		setMinimumSize(gamDim);
		
		
		
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack(); //sets preferred size 
		frame.setResizable(false); //disallows user to resize
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); //allows user to see
		
		
		requestFocus();
		
		
	}

	public void tick() {
		tileTest.tick(this);
		
	}
	
	public void render() {
		BufferStrategy buffStrat= getBufferStrategy();
		
		if (buffStrat == null){
			
			createBufferStrategy(3);
			return;
			
		}
	
	Graphics g = buffStrat.getDrawGraphics();
	
	g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	
	tileTest.render(g);
	
	g.dispose();
	buffStrat.show();
	
	
	}
	
	
	
}