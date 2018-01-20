import java.awt.Color;
import java.awt.Graphics;
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
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 32, 32);
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 31, 31);
	}

}


