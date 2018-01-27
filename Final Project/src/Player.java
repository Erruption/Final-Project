import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player{
	BufferedImage play;
	BufferedImage bar;
	int x,y;
	int MaxHP;
	int HP;
	Game game;
	int IFrames;

	public Player(int x,int y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;

		 MaxHP = 1000;
		 HP = 1000;
		
		try {
			play = ImageIO.read(new File("Resources/TileSet/front1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void tick(Game game) {
		x = (game.getWidth() / 2 ) - 32;
		y = (game.getHeight() / 2 ) - 32;
		
		if(IFrames > 0) {
			IFrames--;
		}
	}

	
	/**
	 * renders Player and Healthbar
	 * @param g
	 */
	public void render(Graphics g) {
		
		//Drawing Player
		g.drawImage( play,  x, y, null);
		
		//Drawing HP Bar
		g.fillRect( x,  y -11, 64, 10);
		g.setColor(Color.GREEN);
		g.fillRect(x + 1, y- 10, (int )(63 * HP/1000), 9);
		
	}
	
	
	/**
	 * Pauses game and creates new GameOver GUI
	 */
	public void kill() {
		
		Menu.G.pause();
		new GameOver();
	}
	
	
	/**
	 *  Returns the bounds of Player
	 * @return Rectangle
	 */
	public Rectangle getBounds(){
			return new Rectangle(x,y,play.getWidth(),play.getHeight());
	}

	
	/**
	 * Subtracts damage taken off of HP after accounting for resistance. Kills the
	 * player if HP is reduced to 0
	 * @param Damage
	 */
	public void takeDamage(int Damage) {
		if(IFrames < 1) {
			HP -= (Damage - Menu.I.getUpResistance());
			IFrames = 100;
		}

		if(HP < 1) {
			kill();
		}
	}
	

}
