import java.awt.Graphics;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile {

	Game game;

	int velX;
	int velY;
	int distanceX;
	int distanceY;
	int xStart;
	int yStart;
	int x, y;


	Projectile(int x, int y, Game game) {

		int velX = 2;
		int velY = 0;
		int distanceX = 0;
		int distanceY = 0;
		int xStart = 100;
		int yStart = 100;



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
	 * Checks if a rectangle collides with a projectile
	 * @param sizeX
	 * @return
	 */
	public boolean collidesWithMonster(Rectangle Rec) {

		if(Rec.intersects(x , y , 60,60)) {
			return true;
		} else {
			return false;
		}	
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		

			g.drawImage(Game.getProjectile(), x, y, null);
			
		}

	

	/**
	 * Checks if the projectile has travelled its full limit
	 * @param sizeX
	 * @return
	 */
	public boolean isTooFar() {
		if (distanceX+distanceY >= 500) {
			return true;
		}else{
			return false;
		}

	}
}