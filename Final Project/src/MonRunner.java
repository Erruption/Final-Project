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
		
		try {
			MonsterIcon = ImageIO.read(new File("Resources/MonsterFiles/Monster-Runner.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void calcMove() {
		double deltaX = (Menu.G.player.x + Menu.G.player.play.getWidth()/2) - (this.x + this.MonsterIcon.getWidth()/2);
		double deltaY = (Menu.G.player.y + Menu.G.player.play.getHeight()/2) - (this.y + this.MonsterIcon.getHeight()/2);
		double hyp = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
		

		mX += (int) 4 * deltaX/hyp;
		mY += (int) 4 * deltaY/hyp;

	}

}
