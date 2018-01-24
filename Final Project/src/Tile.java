import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Graphics;




public class Tile {
	int x, y; //display x and y values
	int oX, oY; //original x and y values



	Game game;




	public Tile(int x, int y, Game game) {
		this.oX = x;
		this.oY = y;

		this.game = game;



	}

	public void tick(Game game){
		this.game=game;

		x = oX + game.xOffset;
		y = oY + game.yOffset;


	}

	public void render(Graphics g) { 
		g.drawImage(Game.getProjectile(), x+128, y+128, null);	

		g.drawImage(Game.getgTile(),  x, y, null);
		//System.out.println("yeet");

		//g.setColor(Color.GREEN);
		//g.fillRect(x, y, 32, 32);
		g.drawImage(Game.getProjectile(), x+128, y+128, null);	

		
		if (Game.togGrid == true){
			g.setColor(Color.WHITE);
			g.drawRect(x, y, 31, 31);
		}

	}

}




