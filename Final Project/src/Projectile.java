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
	int proX, proY;
	
/**
 * 
 * @param x	x pos of mouse in canvas
 * @param y	y pos of mouse in canvas
 * @param game
 */
	Projectile(int x, int y, Game game) {

		double deltaX = -((Menu.G.player.x + Menu.G.player.play.getWidth()/2) - x); //
		double deltaY = -((Menu.G.player.y + Menu.G.player.play.getHeight()/2) - y);
		double hyp = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

		
		
		
		velX = (int) (6 * (deltaX/hyp));
		velY = (int) (6 * (deltaY/hyp));
		




		distanceX = 0;
		distanceY = 0;
		xStart = Menu.G.getWidth()/2 - Menu.G.xOffset;
		yStart = Menu.G.getHeight()/2 - Menu.G.yOffset;


		this.game = game;

	}


	public void tick(Game game ) {
		this.game = game;

		proX = xStart + Menu.G.xOffset + distanceX;
		proY = yStart + Menu.G.yOffset + distanceY;
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


		g.drawImage(Game.getProjectile(), proX, proY, null);

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