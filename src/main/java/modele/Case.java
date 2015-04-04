package modele;
import java.awt.Polygon;


public abstract class Case extends Polygon {
	
	protected int posX;
	protected int posY;
	protected int weight = 100;
	protected int height = 100;
	
	protected int getPosX() {
		return posX;
	}
	protected int getPosY() {
		return posY;
	}
}
