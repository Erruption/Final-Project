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
	Projectile(int x, int y, int pVX, int pVY, Game game) {

		double deltaX = -((Menu.G.player.x + Menu.G.player.play.getWidth()/2) - x); // gets the direction X of the mouse
		double deltaY = -((Menu.G.player.y + Menu.G.player.play.getHeight()/2) - y);// gets the direction Y of the mouse
		double hyp = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

		
		
		
		velX = ((int) (10 * (deltaX/hyp)) + pVX); //gets the x component of the vector
		velY = ((int) (10 * (deltaY/hyp)) + pVY); //gets the y component of the vector
		




		distanceX = 0; //variable for how far its travelled. is equal to xvelocity*ticks
		distanceY = 0;//variable for how far its travelled. is equal to yvelocity*ticks
		xStart = Menu.G.getWidth()/2 - Menu.G.xOffset;	//finds the start of the projectile by finding the middle of the screen and sets offset
		yStart = Menu.G.getHeight()/2 - Menu.G.yOffset;


		this.game = game;

	}


	public void tick(Game game ) {
		this.game = game;

		proX = xStart + Menu.G.xOffset + distanceX - 16; //the projectile x. is equal to the start+ the offset of the game + the distance travelled + half of the picture for centering
		proY = yStart + Menu.G.yOffset + distanceY - 16;
		distanceX += velX; //adds the velocity to the distance travelled
		distanceY += velY;



	}


	/**
	 * Checks if a rectangle collides with a projectile
	 * @param sizeX
	 * @return
	 */
	public boolean collidesWithMonster(Rectangle Rec) {

		if(Rec.intersects(proX , proY , 30,30)) {
			return true; //return true if the projectile hits a rectangle
		} else {
			return false;
		}	
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub


		g.drawImage(Game.getProjectile(), proX, proY, null); //draw the projectile on the screen

	}



	/**
	 * Checks if the projectile has travelled its full limit
	 * @param 
	 * @return
	 */
	public boolean isTooFar() {
		if (distanceX+distanceY >= 500 || distanceX+distanceY <= -500) { //checks if the projectile travels too far and will despawn 
			return true;
		}else{
			return false;
		}

	}
}