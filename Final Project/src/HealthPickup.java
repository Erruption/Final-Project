import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Nick Agnew
 * Object that adds to player health on collision
 */
public class HealthPickup {

	BufferedImage HealthIcon;
	int x;
	int y;
	int oX;
	int oY;
	Game game;

	/**
	 * Constructs an health pickup at location x
	 * @param x
	 */
	public HealthPickup(int x,int y, Game game) {

		try{
			HealthIcon = ImageIO.read(new File("Resources/ItemFiles/Pickup-Health.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.oX = x;
		this.oY = y;
		this.game = game;
	}
	
	public void tick(Game game) {
		x = oX + game.xOffset;
		y = oY + game.yOffset;
	}


	public void render(Graphics g) {

		g.drawImage(HealthIcon,  x, y, null);

	}


	/**
	 * Checks if a rectangle collides with an ItemPickup
	 * @param sizeX
	 * @return
	 */
	public boolean collidesWithItem(Rectangle Rec) {

		if(Rec.intersects(x , y , 40,25)) {
			return true;
		} else {
			return false;
		}	
	}

}
