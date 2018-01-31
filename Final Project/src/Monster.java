

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

	int x,y;   //Calculated X Value
	int oX,oY; //Original Location
	int mX,mY; //Distance Moved
	int dropUpChance;
	int dropHPChance;
	int HP;
	int MaxHP;
	boolean Alive = false;
	int monSpd;

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
		g.fillRect(x + 1, y- 12, (int )(46 * HP/MaxHP), 8);



	}


	/**
	 * Rolls for drops and spawns them in Game
	 */
	public void dropLoot() {

		//Rolls an integer
		int roll = (int) ((Math.random() * 100) + 1);	

		//Checks if integer is within drop chance
		if(roll <= dropUpChance) {

			Menu.G.Items.add(new ItemPickup(x - Menu.G.xOffset ,y - Menu.G.yOffset , Menu.G));

		} else if(roll <= dropUpChance + dropHPChance) {
			Menu.G.HealthPickups.add(new HealthPickup(x - Menu.G.xOffset ,y - Menu.G.yOffset , Menu.G));
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

		//Checks if the monster is dead
		if(HP < 1) {
			dropLoot();
			Menu.G.Runners.remove(index);
		}
	}


}