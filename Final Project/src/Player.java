import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player{
	BufferedImage play;
	BufferedImage bar;
	HealthBars HP;
	int x,y;
	Game game;

	public Player(int x,int y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;

		try {
			play = ImageIO.read(new File("Resources/TileSet/front1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void tick(Game game) {
		x = (game.getWidth() / 2 ) - 32;
		y = (game.getHeight() / 2 ) - 32;
		
	}

	public void renderHP(Graphics g) {
		
		g.drawRect( x,  y -10, 60, 10);
		g.setColor(Color.RED);
		g.fillRect(x, y-10, 60, 10);
		
		
		
	}
	
	
	public void render(Graphics g) {


		g.drawImage( play,  x, y, null);

		//g.setColor(Color.RED);
		//g.fillRect(x, y, 32, 32);



	}
	
	public void kill() {
		//Put actual code here
		System.exit(0);
	}
	
	public Rectangle getBounds(){
			return new Rectangle(x,y,64,64);
	}

	

}
