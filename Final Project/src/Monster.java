

import java.awt.Color;
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
	int HP;
	int MaxHP;
	boolean Alive = false;
	Game game;


	public void tick(Game game) {
		x = mX + oX + game.xOffset;
		y = mY + oY + game.yOffset;
		calcMove();
	}

	//To calculate monsters movement
	public abstract void calcMove();

	public void render(Graphics g) {

		//Drawing Monster
		g.drawImage(MonsterIcon,  x, y, null);

		//Drawing HP bar
		g.setColor(Color.BLACK);
		g.fillRect( x, y - 13, 48, 10);
		g.setColor(Color.RED);
		g.fillRect(x + 1, y- 12, (int )(47 * HP/MaxHP), 9);



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
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,48,64);
	}
	
	
	/**
	 * Subtracts damage from HP, and calls kill if HP drops below 1
	 * @param Damage
	 */
	public void takeDamage(int Damage, int index) {
			HP -= Damage;

		if(HP < 1) {
			dropLoot();
			Menu.G.Runners.remove(index);
		}
	}


}