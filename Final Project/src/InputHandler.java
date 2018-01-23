import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

	public void keyPressed(KeyEvent e) {

		int KeyCode = e.getKeyCode();

//basic controls for game
		if(KeyCode == e.VK_A) {
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
		}
		
		/*
		//toggle the grid 
		if(KeyCode == e.VK_E) {
			if (Game.togGrid = true){
				Game.togGrid = false;
			}else {
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
		
	}
	public void keyTyped(KeyEvent e) {

	}

}
