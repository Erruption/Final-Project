import java.awt.Graphics;
import java.awt.PointerInfo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile {

	Game game;
	int xStart = (400+(game.getHeight())/2);
	int yStart = ((game.getWidth())/2);



	
	@SuppressWarnings("unused")
	public void tick(Game game ) {
		this.game = game;
		



	}




	public static void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}