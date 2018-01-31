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
	static int wallOffset = 360;


	Game game;




	public Tile(int x, int y, Game game) {
		this.oX = x;//original x and y
		this.oY = y;

		this.game = game;



	}

	public void tick(Game game){
		this.game=game;
		 
		x = oX + game.xOffset; //finds where it actually is by calculating with the offset 
		y = oY + game.yOffset;


	}

	public static void renderWalls(Graphics g) {
		
		g.setColor(Color.GRAY); //sets the colour of the walls
		g.fillRect(Menu.G.xMin + Menu.G.xOffset + wallOffset, Menu.G.yMin + Menu.G.yOffset + wallOffset, 64, Menu.G.tileHeight*32 - wallOffset*2); //Sets the left wall
		g.fillRect(Menu.G.xMin + Menu.G.xOffset + wallOffset, Menu.G.yMin + Menu.G.yOffset + wallOffset, Menu.G.tileWidth*32 - wallOffset*2, 64); //Sets the top wall
		g.fillRect(-Menu.G.xMax + Menu.G.xOffset + wallOffset + 128 , Menu.G.yMin + Menu.G.yOffset + wallOffset, 64, Menu.G.tileHeight*32 - wallOffset*2 +12); //Sets the right wall
		g.fillRect(Menu.G.xMin + Menu.G.xOffset + wallOffset , -Menu.G.yMax + Menu.G.yOffset + wallOffset +128, Menu.G.tileWidth*32 - wallOffset*2+12, 64); //Sets the bottom wall
 
	}
	
	/**
	 * draws specified tiles when called
	 * @param g graphics component
	 */
	public void render(Graphics g) { 
		g.drawImage(Game.getgTile(),  x, y, null); //draws the tiles
		
		
		
		
	}

}




