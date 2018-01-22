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

	//offset for display of objects
	int xOffset = 0;
	int yOffset = 0;

	InputHandler IH = new InputHandler();

	Player player = new Player(0, 0, this);

	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	JFrame frame;
	
	
	
	public static boolean running = false;


	//set all the frame visuals
	public static final String TITLE = "Realm of the Sane God";
	public static final int WIDTH = 900;
	public static final int HEIGHT = 900;
	public static final Dimension gamDim = new Dimension(WIDTH, HEIGHT); 
	Thread thread;

	//dim in tiles
	public int tileWidth = 150;
	public int tileHeight = 150;

	Tile tileArray[][] = new Tile[tileWidth][tileHeight]; 

	//Key Controls
	public static boolean left, right, up, down;




	/**
	 * while running tick and render frames
	 */
	public void run() {
		while(running) {
			tick();
			render();

			try {
				Thread.sleep(5); //slows ticks to milliseconds

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * sets run to true and starts the thread
	 */
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();

	}

	/**
	 * toggle pause game
	 */
	public synchronized static void pause() {
		if (running == true){
			Menu.I.setVisible(true);
			running = false;	
		}else {
		}
	}


	/**
	 * sets run to false
	 */
	public synchronized static void stop() {
		
		running = false;
		//Make this close the game and not the entire application
		System.exit(0);

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


		this.addKeyListener(IH);


		init(); 

		requestFocus();


	}

	/**
	 * initiates the screen placements by rendering all tiles on screen
	 */
	private void init() {
		for (int x = 0 ; x < tileWidth; x++) {
			for (int y = 0 ; y< tileHeight; y++) { 
				tileArray[x][y] =new Tile(x * 32, y * 32, this);
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
		moveMap();
		player.tick(this);
	}

	private void moveMap() {
		if(left) {
			xOffset++;
		}
		if(right) {
			xOffset--;
		}
		if(up) {
			yOffset++;
		}
		if(down) {
			yOffset--;
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

		//checks if the tile will be on screen and only renders if it will
		for (int x = 0 ; x < tileWidth; x++) { //for width of the screen
			for (int y = 0 ; y< tileHeight; y++) { //for height of the screen
				if (tileArray[x][y].x >= 0 - 32 && //if within left side of the screen
						tileArray[x][y].x <= getWidth() & //if within right side of the screen
						tileArray[x][y].y>= 0 - 32  && //if within the top of the screen
						tileArray[x][y].y <= getHeight() + 32 //if within the bottom of the screen
						){		
					tileArray[x][y].render(g);//render the tile if on screen
				}
			}
		}

		player.render(g);

		g.dispose();
		buffStrat.show();


	}



}