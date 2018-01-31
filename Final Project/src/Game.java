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
	//offset for display of objects


	InputHandler IH = new InputHandler();

	Player player = new Player(0, 0, this);


	


	JFrame frame;

	static BufferedImage play;
	static BufferedImage gTile;
	static BufferedImage fire;
	static BufferedImage project;
	static BufferedImage UpgradeIcon;


	public static boolean running = false;


	//set all the frame visuals
	public final String TITLE = "Void of the Sane Mortal";
	public final int WIDTH = 900;
	public final int HEIGHT = 900;
	public final Dimension gamDim = new Dimension(WIDTH, HEIGHT); 
	Thread thread;
	
	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	//dim in tiles
	public int tileWidth = 150;
	public int tileHeight = 150;


	Tile tileArray[][] = new Tile[tileWidth][tileHeight]; 

	//Array for pickups
	ArrayList<ItemPickup> Items = new ArrayList<ItemPickup>();
	ArrayList<HealthPickup> HealthPickups = new ArrayList<HealthPickup>();
	ArrayList<Projectile> Projectiles = new ArrayList<Projectile>();
	ArrayList<MonRunner> Runners = new ArrayList<MonRunner>();


	//Monster Spawn Timer
	int MonTimer = 150;
	int MonTimerMax = 1500;

	//Key Controls
	public static boolean left, right, up, down, shooting;
	public static boolean sleft, sright, sup, sdown;
    final int basespd = 5;
	int spd = 5;


	int xOffset = -((WIDTH + tileWidth *16)/2);
	int yOffset = -((HEIGHT + tileHeight *16)/2);

	int xMin = 0;
	int xMax = - tileWidth * 32 + 900;
	int yMin = 0;
	int yMax = - tileHeight * 32 + 900;

	int shotTimer = 0;
	int z = 0;




	/**
	 * while running tick and render frames
	 */
	public void run() {
		while(running) {
			tick();
			render();

			try {
				Thread.sleep(10); //slows ticks to milliseconds

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
		//spawnMonster();
		//Runners.add(new MonRunner(1200,400,this));

		running = true; //starts the game
		thread = new Thread(this);
		thread.start(); 

	}

	/**
	 * toggle pause game
	 */
	public synchronized static void pause() {
		if (running == true){
			running = false;	
		}else {
		}
	}


	/**
	 * sets run to false and exits everything
	 */
	public synchronized static void stop() {

		running = false;
		Menu.G.setEnabled(false);
		Menu.G.setVisible(false);
		Menu.G = null;

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
	private void init() {//calls all the images and initializes the tile array

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
			fire = ImageIO.read(new File("Resources/Fireball.png"));
		} catch (IOException e) {
			fire = null;
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
	public void tick() { //ticks the entire game
//Checks for collisions andand removes the projectiles
		if(Projectiles.size() > 0){
			for(int X = 0; X < Projectiles.size(); X++){
				if(Runners.size() > 0){
					for(int z = 0; z < Runners.size(); z++){
						if(Projectiles.get(X).collidesWithMonster(Runners.get(z).getBounds())) {
							Runners.get(z).takeDamage(100 + Menu.I.getUpDamage(),z);
							Projectiles.remove(X);
							break;
						}
					}
				}
			}
		}

		
		
		checkShoot(); //checks if shooting, will always be on

		if(Projectiles.size() > 0){//ticks the projectiles and checks if it has travelled too far
			for(int x = 0; x < Projectiles.size(); x++){
				Projectiles.get(x).tick(this);

				if(Projectiles.get(x).isTooFar()){ 
					Projectiles.remove(x);
				}
			}
		}
		//ticks all of the tiles
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
					if(player.HP < 900) {
						player.HP += 200;
					} else {
						player.HP = 1000;
					}
					HealthPickups.remove(x);
				}
			}
		}

		
		//Spawns the bois
		MonTimer -= 3;
		if(MonTimer <= 0) {

			spawnMonster();

			if(MonTimerMax >= 100) {
				MonTimerMax -= 50;
			}
			
			MonTimer = MonTimerMax;
		}


		
		//Monster collision and movement
		if(Runners.size() > 0){
			for(int x = 0; x < Runners.size(); x++){
				if(Runners.get(x).Alive = false){
					Runners.remove(x);
				}
				Runners.get(x).tick(this);
				if(Runners.get(x).collidesWithItem(player.getBounds())){
					player.takeDamage((int)(Math.random()*60)+70);
				}
			}
		}





		
	

		moveMap(); //calls movement
		player.tick(this);//ticks the player invincibility frames and placement, 
		


	}


	
	/**
	 * monster gets spawned when called
	 */
	private void spawnMonster() {

		int monX; //mosnter x
		int monY; //monster y

		while(true) {
			
			monX = (int)(Math.random() * (WIDTH + 400)) - 200;//Generates an x
			monY = (int)(Math.random() * (HEIGHT + 400)) - 200;//Generates a y
			
			//Checks to make sure the location is off screen
			if((monX > WIDTH || monX < -47 || monY > HEIGHT || monY < -63)){
				//if( monX < -47) {
				if(Runners.size() < 20) {
					//creates a new monster if there are less than 20 already in the game
					Runners.add(new MonRunner(monX-xOffset,monY-yOffset,this));
				}
				break;
				}
				
		}
	}


	/**
	 * initiates speed and if inputs are given will move
	 */
	private void moveMap() {
		if(left) {//if moving, add the speeds
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

		if (xOffset >=  xMin) { //sets the walls and forces player to stay within bounds
			xOffset = xMin;
		}
		if (xOffset <= xMax) {
			xOffset = xMax;
		}
		if (yOffset >=  yMin) {
			yOffset = yMin;
		}
		if (yOffset <= yMax) {
			yOffset = yMax;
		}

	}

	/**
	 * initiates speed and if inputs are given will move
	 */
	private void checkShoot() { //shoots every 100 ticks
		shotTimer ++; //adds one per tick
		if (shotTimer > 100) {//if reached 101
			shotTimer = 0;

			int mouse_x = MouseInfo.getPointerInfo().getLocation().x - this.getLocationOnScreen().x; //gets the x mouse position
			int mouse_y = MouseInfo.getPointerInfo().getLocation().y - this.getLocationOnScreen().y; //gets the y mouse position




			Projectiles.add( new Projectile(mouse_x, mouse_y, null)); //fires the projectile towards the mouse

		}
	}



	/**
	 * renders what the user will see using double or triple buffering 
	 */
	public void render() {//renders the buffering and everything 

		BufferStrategy buffStrat= getBufferStrategy();



		if (buffStrat == null){ //if there is no buffer strategy it makes one

			createBufferStrategy(2);//how many layers of buffering
			return;

		}



		Graphics g = buffStrat.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null); //draws the image

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
		player.render(g); //renders the player
		Tile.renderWalls(g); //render the walls
		if(Items.size() > 0){ //checks if there are any items

			for(int x = 0; x < Items.size(); x++){
				Items.get(x).render(g); //renders the items that exist
			}

		}
		if(HealthPickups.size() > 0){//checks if there are any health pickups
			for(int x = 0; x < HealthPickups.size(); x++){
				HealthPickups.get(x).render(g);
			}
		}
		if(Projectiles.size() > 0){ //checks if there are any
			for(int x = 0; x < Projectiles.size(); x++){
				Projectiles.get(x).render(g);
			}
		}
		if(Runners.size() > 0){ //checks if there are any
			for(int x = 0; x < Runners.size(); x++){
				Runners.get(x).render(g);
			}
		}




		g.dispose();//gets rid of the screen
		buffStrat.show();//shows the next frame


	}



}