import java.awt.Color;
import java.awt.Graphics;

public class Tile {
	int x, y;
	GUI game;

	public Tile(int x, int y, GUI game) {
		this.x = x;
		this.y = y;
		
		this.game = game;
		
		
		
	}

	public void tick(GUI game){
		this.game=game;
		
	
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 32, 32);
	}

}


