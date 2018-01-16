import javax.swing.JLabel;

public class Monster {
	//janked code
	ghost = new JLabel (ii2);
	ghost.setSize(200,200);
	ghost.setLocation(500,500);
	cp.add(ghost);


	//janked some code from my old project

	xG = ghost.getX();
	yG = ghost.getY();
	//if ((xP > xG) && (xP < xG+200) && (yP > yG)){
	//System.exit(0);} //exits program			
	if (xG > xP){ //ghost is to right of scytheman
		xG-=3;
		ghost.setLocation(xG,yG);
	}
	else{ //ghost is left of scytheman
		xG+=3;
		ghost.setLocation(xG,yG);
	}
	if (yG > yP){ //ghost is below scytheman
		yG-=3;
		ghost.setLocation(xG,yG);
	}
	else{ //ghost is above scytheman
		yG+=3;
		ghost.setLocation(xG,yG);
	}


	public int getxG(){
		return xG;
	}



}
