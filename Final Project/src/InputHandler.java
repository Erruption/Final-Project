import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

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
			Menu.I.setVisible(true);
			
		}
		
	
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
		

		
	}
	/* possible shooting if mouse pressed
	  public void mousePressed(MouseEvent e) {
		 Menu.G.shooting = true;
		  }

	    public void mouseReleased(MouseEvent e) {
	     Menu.G.shooting = false;
	    }
	    
	public void keyTyped(KeyEvent e) {

	}
*/

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
