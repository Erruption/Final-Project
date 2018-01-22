import java.awt.*;
import javax.swing.*;


public class ItemPickup extends JLabel {
	
	private ImageIcon[] itemImages = new ImageIcon[5];
	public int type;
	
	/**
	 * Constructs an item pickup at location x
	 * @param x
	 */
	ItemPickup(Point x){

		//Adds all images to an array
		itemImages[0] = new ImageIcon();
		itemImages[1] = new ImageIcon("Resources/ItemFiles/Item-1-Damage.png");
		itemImages[2] = new ImageIcon("Resources/ItemFiles/Item-2-Speed.png");
		itemImages[3] = new ImageIcon("Resources/ItemFiles/Item-3-Resistance.png");
		itemImages[4] = new ImageIcon("Resources/ItemFiles/Item-4-FireRate.png");

		//Decides the type of item
		type = (int) ((Math.random() * 4) + 1);

		setSize(70,70);
		setLocation(x);
		setIcon(itemImages[type]);
	}
	
	/**
	 * Checks if a rectangle collides with an ItemPickup
	 * @param sizeX
	 * @return
	 */
	public boolean collidesWithItem(Rectangle x) {
		
		if(x.intersects(this.getBounds())) {
			return true;
		} else {
			return false;
		}	
	}
	
	
}
