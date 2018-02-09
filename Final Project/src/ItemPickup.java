import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Nick Agnew
 *Object thats added to inventory on collision with player
 */
public class ItemPickup {

	BufferedImage UpgradeIcon;
	private int type;
	int x;
	int y;
	int oX;
	int oY;
	BufferedImage Damage, Speed, Resistance, AttackSpd;
	
	Game game;

	/**
	 * Constructs an item pickup at location x
	 * @param x
	 */
	public ItemPickup(int x,int y, Game game) {

		try{
			Damage = ImageIO.read(new File("Resources/ItemFiles/Item-1-Damage.png"));
			Speed = ImageIO.read(new File("Resources/ItemFiles/Item-2-Speed.png"));
			Resistance =  ImageIO.read(new File("Resources/ItemFiles/Item-3-Resistance.png"));
			AttackSpd = ImageIO.read(new File("Resources/ItemFiles/Item-4-AttackSpd.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Decides the type of item
		type = (int) ((Math.random() * 4) + 1);
		this.oX = x;
		this.oY = y;
		this.game = game;
	}
	
	public void tick(Game game) {
		x = oX + game.xOffset;
		y = oY + game.yOffset;
	}


	public void render(Graphics g) {

			if(type == 1){
				UpgradeIcon = Damage;
			} else if(type == 2){
				UpgradeIcon = Speed;
			} else if(type == 3) {
				UpgradeIcon = Resistance;
			} else if (type == 4) {
				UpgradeIcon = AttackSpd;
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

	public int getType() {
		return type;
	}


}
