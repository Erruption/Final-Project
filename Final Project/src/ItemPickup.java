import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class ItemPickup {
	
	BufferedImage UpgradeIcon;
	public int type;
	int x;
	int y;
	Game game;
	
	
	/**
	 * Constructs an item pickup at location x
	 * @param x
	 */
	public ItemPickup(int x,int y, Game game) {
		
		//Decides the type of item
		type = (int) ((Math.random() * 3) + 1);
		this.x = x;
		this.y = y;
		this.game = game;
	}
	public void tick(Game game) {
		x += game.xOffset;
		y += game.yOffset;
	}

	public void render(Graphics g) {

		BufferedImage play = null;
		try {
			if(type == 1){
				UpgradeIcon = ImageIO.read(new File("Resources/ItemFiles/Item-1-Damage.png"));
			} else if(type == 2){
				UpgradeIcon = ImageIO.read(new File("Resources/ItemFiles/Item-2-Speed.png"));
			} else {
				UpgradeIcon = ImageIO.read(new File("Resources/ItemFiles/Item-3-Resistance.png"));
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		g.drawImage( UpgradeIcon,  x, y, null);

	}
	
	
	/**
	 * Checks if a rectangle collides with an ItemPickup
	 * @param sizeX
	 * @return
	 */
	public boolean collidesWithItem(Rectangle Rec) {
		
		if(Rec.intersects(x , y , 70,70)) {
			return true;
		} else {
			return false;
		}	
	}
	
	
}
