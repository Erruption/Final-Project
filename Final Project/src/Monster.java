

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Nick Agnew
 *Abstract class for enemies
 */
public abstract class Monster {

	BufferedImage MonsterIcon;
	int x,y;
	int oX,oY;
	int mX,mY;
	int dropUpChance;
	int dropHPChance;
	Game game;


	public void tick(Game game) {
		x = mX + oX + game.xOffset;
		y = mY + oY + game.yOffset;
		calcMove();
	}

	//To calculate monsters movement
	public abstract void calcMove();
	
	public void render(Graphics g) {

		g.drawImage(MonsterIcon,  x, y, null);

	}


	/**
	 * Rolls for drops and spawns them in Game
	 */
	public void dropLoot() {

		int roll = (int) Math.random() * 100 + 1;	

		if(roll <= dropUpChance) {
			Menu.G.Items.add(new ItemPickup(x ,y , Menu.G));
		} else if(roll <= dropUpChance + dropHPChance) {
			Menu.G.HealthPickups.add(new HealthPickup(x ,y , Menu.G));
		}

	}

	/**
	 * Checks if a rectangle collides with a Monster
	 * @param sizeX
	 * @return
	 */
	public boolean collidesWithItem(Rectangle Rec) {

		if(Rec.intersects(x , y , 48,64)) {
			return true;
		} else {
			return false;
		}	
	}

}