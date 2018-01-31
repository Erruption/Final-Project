import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class InputHandler implements KeyListener {

	public void keyPressed(KeyEvent e) {

		int KeyCode = e.getKeyCode();
		                    
//basic controls for game
		if(KeyCode == e.VK_A) {
			//Menu.G.player.takeDamage(100);
			Game.left = true;
		}
		if(KeyCode == e.VK_D) {
			Game.right = true;
		}
		if(KeyCode == e.VK_S) {
			Game.down = true;
		}
		if(KeyCode == e.VK_W) {
			Game.up = true;
		}
		

		
		
		//pulling up the menu
		if(KeyCode == e.VK_ESCAPE) {
			Game.pause();
			Menu.I.setVisible(true);
			
		}
		
/* slows down timer notably
		//toggle the grid 
		if(KeyCode == e.VK_E) {
			if (Game.togGrid == true){
				Game.togGrid = false;
			}else{
				Game.togGrid = true;
			}
		}
	*/	
				
	}

	public void keyReleased(KeyEvent e) {
		int KeyCode = e.getKeyCode();

		
		if(KeyCode == e.VK_A) {
			Game.left = false;
		}
		if(KeyCode == e.VK_D) {
			Game.right = false;
		}
		if(KeyCode == e.VK_S) {
			Game.down = false;
		}
		if(KeyCode == e.VK_W) {
			Game.up = false;
		}
		if(KeyCode == e.VK_E) {
			
		}
		
		
		
/*
		if(KeyCode == e.VK_LEFT) {
			Game.sleft = false;
		}
		if (KeyCode == e.VK_RIGHT) {
			Game.sright = false;
		}
		if(KeyCode == e.VK_DOWN) {
			Game.sdown = false;
		}
		if(KeyCode == e.VK_UP) {
			Game.sup = false;
		}
	*/
		
	}
	
	  public void mousePressed(MouseEvent e) {
		 Menu.G.shooting = true;
		  }

	    public void mouseReleased(MouseEvent e) {
	     Menu.G.shooting = false;
	    }
	    
	public void keyTyped(KeyEvent e) {

	}

}
