import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MonRunner extends Monster {


	
	public MonRunner(int x, int y, Game game) {
		oX = x;
		oY = y;
		this.game = game;
		MaxHP = 600;
		HP = 600;
		dropUpChance = 20;
		dropHPChance = 30;
		monSpd = (int) (Math.random()*3)+3;
		
		try {
			MonsterIcon = ImageIO.read(new File("Resources/MonsterFiles/Monster-Runner.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Calculates the monster's movement
	 */
	@Override
	public void calcMove() {
		
		//Difference in X between player and monster
		double deltaX = (Menu.G.player.x + Menu.G.player.play.getWidth()/2) - (this.x + this.MonsterIcon.getWidth()/2);
		
		//Difference in Y between player and monster
		double deltaY = (Menu.G.player.y + Menu.G.player.play.getHeight()/2) - (this.y + this.MonsterIcon.getHeight()/2);
		
		//Calculates the hypotenuse
		double hyp = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
		
		//Calculates the x and y components based on the speed and direction of movement.
		mX += (int) monSpd * deltaX/hyp;
		mY += (int) monSpd * deltaY/hyp;

	}

}
