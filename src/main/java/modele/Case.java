package modele;
import java.awt.Polygon;


public abstract class Case extends Polygon {
	
	protected int posX;
	protected int posY;
	
	public Case(int pPosX, int pPosY)
	{
		this.posX = pPosX;
		this.posY = pPosY;
	}
	
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
}