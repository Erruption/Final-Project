import java.awt.Graphics;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile {

	Game game;
	static int xStart;
	static int yStart;
	static int x;
	static int y;
	static int velX =2;
	static int velY = 0;
	static int distanceX = 0;
	static int distanceY = 0;
	
	
	public Projectile(int x, int y, Game game) {
		

		int xStart = (64+(game.getHeight())/2);
		int yStart = (64+(game.getWidth())/2);
		
		
		
		this.game = game;

	}
	

	public void tick(Game game ) {
		this.game = game;
		
		x = xStart + game.xOffset + distanceX;
		y = yStart + game.yOffset + distanceY;
		distanceX += velX;
		distanceY += velY;
	}


	/**
	 * Checks if a rectangle collides with an ItemPickup
	 * @param sizeX
	 * @return
	 */
	public boolean collidesWithMonster(Rectangle Rec) {

		if(Rec.intersects(x , y , 70,70)) {
			return true;
		} else {
			return false;
		}	
	}

	public static void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Game.getPlay(), x, y, null);
		
	}
}