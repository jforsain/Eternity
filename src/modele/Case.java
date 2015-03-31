package modele;
import java.awt.Polygon;


public abstract class Case extends Polygon {
	
	protected int posX;
	protected int posY;
	
	protected int getPosX() {
		return posX;
	}
	protected int getPosY() {
		return posY;
	}
}
