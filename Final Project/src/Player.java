import java.awt.Color;
import java.awt.Graphics;

public class Player{
	
	int x,y;
	Game game;
	
	public Player(int x,int y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;
		
	}
	public void tick(Game game) {
		this.game = game;
		x = (game.getWidth() / 2 ) - 16;
		y = (game.getHeight() / 2 ) - 16;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 32, 32);
		
	}
}