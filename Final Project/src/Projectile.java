import java.awt.Graphics;
import java.awt.PointerInfo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile {

	Game game;
	static int xStart;
	static int yStart;
	static int x;
	static int y;
	
	public Projectile(int x, int y, Game game) {
		

		int xStart = (64+(game.getHeight())/2);
		int yStart = (64+(game.getWidth())/2);
		
		
		
		this.game = game;

	}
	
	
	@SuppressWarnings("unused")
	public void tick(Game game ) {
		this.game = game;
		
		x = xStart + game.xOffset;
		y = yStart + game.yOffset;





	}

	
	


	public static void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Game.getPlay(), x, y, null);
		
	}
}