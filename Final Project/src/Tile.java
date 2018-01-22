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

	
	BufferedImage gTile = null;
	try {
		gTile = ImageIO.read(new File("Resources/TileSet/GrassTile.jpg"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	g.drawImage( gTile,  x, y, null);


	//g.setColor(Color.GREEN);
	//g.fillRect(x, y, 32, 32);

	g.setColor(Color.BLACK);
	g.drawRect(x, y, 31, 31);
}

}




