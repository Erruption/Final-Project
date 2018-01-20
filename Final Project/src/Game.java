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
 * Reference to ImeratorRyan
 * The visual implementation and tick system of the game
 * 
 *
 */
public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L ;

	//offset for dispay of objects
	int xOffset = 0;
	int yOffset = 0;
	
	
	
	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	JFrame frame;

	public static boolean running = false;


	//set all the frame visuals
	public static final String TITLE = "Realm of the Sane God";
	public static final int WIDTH = 900;
	public static final int HEIGHT = 900;
	public static final Dimension gamDim = new Dimension(WIDTH, HEIGHT); 

	
	//dim in tiles
	public int tileWidth = (WIDTH/32+2);
	public int tileHeight = (HEIGHT/32+2);

	Tile tileArray[][] = new Tile[tileWidth][tileHeight]; 
	
	//Key Controls
	boolean left, right, up, down;
	
	
	

	/**
	 * while running tick and render frames
	 */
	public void run() {
		while(running) {
			tick();
			render();
		}
	}


	/**
	 * sets run to true and starts the thread
	 */
	public synchronized void start() {
		running = true;
		Thread thread = new Thread(this);
		thread.start();

	}
	/**
	 * sets run to false
	 */
	public synchronized void stop() {

		running = false;

	}

	public Game() {


		setMaximumSize(gamDim);
		setMinimumSize(gamDim);
		setPreferredSize(gamDim);


		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack(); //sets preferred size 
		frame.setResizable(true); //disallows user to resize
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); //allows user to see

		init(); 

		requestFocus();


	}

	/**
	 * initiates the screen placements by rendering all tiles on screen
	 */
	private void init() {
		for (int x = 0 ; x < tileWidth; x++) {
			for (int y = 0 ; y< tileHeight; y++) { 
				tileArray[x][y] =new Tile(x * tileWidth, y * tileHeight, this);
			}
		}
	}



		/**
		 * Author: Lewis Zettler 
		 * Ticks game system runs all actions at the same time
		 * 
		 */
		public void tick() {
			
				for (int x = 0 ; x < tileWidth; x++) {
					for (int y = 0 ; y< tileHeight; y++) { 
						tileArray[x][y].tick(this);
					}
				}

		}
		/**
		 * renders what the user will see using triple buffering 
		 */
		public void render() {

			BufferStrategy buffStrat= getBufferStrategy();



			if (buffStrat == null){

				createBufferStrategy(3);
				return;

			}



			Graphics g = buffStrat.getDrawGraphics();

			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);


			for (int x = 0 ; x < tileWidth; x++) {
				for (int y = 0 ; y< tileHeight; y++) { 
					tileArray[x][y].render(g);
				}
			}
			
			
			
			g.dispose();
			buffStrat.show();


		}



	}