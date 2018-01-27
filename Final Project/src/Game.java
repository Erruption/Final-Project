import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
	HealthBars HP;
	//offset for display of objects
	int xOffset = 0;
	int yOffset = 0;
	int shotTimer = 0;
	int z = 0;

	InputHandler IH = new InputHandler();

	Player player = new Player(0, 0, this);


	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);


	JFrame frame;

	static BufferedImage play;
	static BufferedImage gTile;
	static BufferedImage fire;
	static BufferedImage project;
	static BufferedImage UpgradeIcon;


	public static boolean running = false;


	//set all the frame visuals
	public static final String TITLE = "Void of the Sane Mortal";
	public static final int WIDTH = 900;
	public static final int HEIGHT = 900;
	public static final Dimension gamDim = new Dimension(WIDTH, HEIGHT); 
	Thread thread;

	//dim in tiles
	public int tileWidth = 150;
	public int tileHeight = 150;


	Tile tileArray[][] = new Tile[tileWidth][tileHeight]; 

	//Array for pickups
	ArrayList<ItemPickup> Items = new ArrayList<ItemPickup>();
	ArrayList<HealthPickup> HealthPickups = new ArrayList<HealthPickup>();
	ArrayList<Projectile> Projectiles = new ArrayList<Projectile>();



	//Key Controls
	public static boolean left, right, up, down, shooting;
	public static boolean  togGrid = false;
	public static boolean sleft, sright, sup, sdown;
	int spd = 5;




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

		//For testing
		//HealthPickups.add(new HealthPickup(600,300, this));
		//Items.add(new ItemPickup(500,300, this));

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
	 * sets run to false and exits everything
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
		frame.setResizable(false); //disallows user to resize
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); //allows user to see


		this.addKeyListener(IH);



		init(); 

		requestFocus();


	}


	public static BufferedImage getPlay(){
		return play;
	}
	public static BufferedImage getgTile(){
		return gTile;
	}
	public static BufferedImage getProjectile(){
		return fire;
	}

	/**
	 * initiates the screen placements by rendering all tiles on screen
	 */
	private void init() {

		/**
		 * gets the tile resource and buffers it
		 */
		try {
			gTile = ImageIO.read(new File("Resources/TileSet/GrassTile.jpg"));
		} catch (IOException e) {
			gTile = null;
			e.printStackTrace();
		}

		/**
		 * gets the pic for player and buffers it
		 */
		try {
			play = ImageIO.read(new File("Resources/TileSet/player.jpg"));
		} catch (IOException e) {
			play = null;
			e.printStackTrace();
		}
		/**
		 * gets the pic for projectile and buffers it
		 */
		try {
			play = ImageIO.read(new File("Resources/Fireball.jpg"));
		} catch (IOException e) {
			play = null;
			e.printStackTrace();
		}


		for (int x = 0 ; x < tileWidth; x++) {
			for (int y = 0 ; y< tileHeight; y++) { 
				tileArray[x][y] = new Tile(x * 32, y * 32, this);
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

		//Upgrade item collision and pickup
		if(Items.size() > 0){
			for(int x = 0; x < Items.size(); x++){
				Items.get(x).tick(this);
				if(Items.get(x).collidesWithItem(player.getBounds())){
					Menu.I.addItem(Items.get(x).getType());
					Items.remove(x);
				}
			}
		}

		//Health collision and pickup
		if(HealthPickups.size() > 0){
			for(int x = 0; x < HealthPickups.size(); x++){
				HealthPickups.get(x).tick(this);
				if(HealthPickups.get(x).collidesWithItem(player.getBounds())){
					//Add to player health-------------------------
					HealthPickups.remove(x);
				}
			}
		}



		if(Projectiles.size() > 0){
			for(int x = 0; x < Projectiles.size(); x++){
				Projectiles.get(x).tick(this);

				if(Projectiles.get(x).collidesWithMonster(player.getBounds()) || Projectiles.get(x).isTooFar()){
					//damage monster
					Projectiles.remove(x);

				}
			}
		}
		moveMap();
		player.tick(this);
		Shoot();


	}


	/**
	 * initiates speed and if inputs are given will move
	 */
	private void moveMap() {
		if(left) {
			xOffset += spd;
		}
		if(right) {
			xOffset += -spd;
		}
		if(up) {
			yOffset += spd;
		}
		if(down) {
			yOffset += -spd;
		}
	}
	/**
	 * projectile created if shooting
	 */
	private void Shoot() {
		shotTimer ++;
		Projectiles.add(new Projectile(player.x, player.y , this));

		if (shotTimer>= 100){
			if(sleft) {
				xOffset += spd;
			}
			if(sright) {
				xOffset += -spd;
			}
			if(sup) {
				yOffset += spd;
			}
			if(sdown) {
				yOffset += -spd;
			}
		}

	}

	/**
	 * renders what the user will see using double or triple buffering 
	 */
	public void render() {

		BufferStrategy buffStrat= getBufferStrategy();



		if (buffStrat == null){

			createBufferStrategy(3);//how many layers of buffering
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
		if(Items.size() > 0){
			for(int x = 0; x < Items.size(); x++){
				Items.get(x).render(g);
			}

		}
		if(HealthPickups.size() > 0){
			for(int x = 0; x < HealthPickups.size(); x++){
				HealthPickups.get(x).render(g);
			}
		}
		if(Projectiles.size() > 0){
			for(int x = 0; x < Projectiles.size(); x++){
				Projectiles.get(x).render(g);
			}
		}

		player.render(g);
		player.renderHP(g);


		g.dispose();
		buffStrat.show();


	}



}