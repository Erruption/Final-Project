import java.awt.Color;
import java.awt.Graphics;

public  class HealthBars extends Player{
	

	int PHP = 100;
	int MHP = 10;
	int BHP = 500;

	public HealthBars(int x, int y, Game game) {
		super(x, y, game);
		// TODO Auto-generated constructor stub
	}

	public void Kill (Graphics g) {

		renderHP(g);

	}







	}

