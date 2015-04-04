package modele;

public class Vide extends Case{
	
	public Vide(int posX, int posY)
	{
		this.addPoint(posX, posY);
		this.addPoint(posX + 100, posY);
		this.addPoint(posX + 100, posY + 100);
		this.addPoint(posX, posY + 100);
	}
}
