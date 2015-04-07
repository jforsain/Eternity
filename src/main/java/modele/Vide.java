package modele;

import java.awt.Color;

public class Vide extends Case{
	
	private Color couleur = Color.GRAY;
	
	public Vide(int pPosX, int pPosY)
	{
		super(pPosX, pPosY);
		this.addPoint(posX, posY);
		this.addPoint(posX + 100, posY);
		this.addPoint(posX + 100, posY + 100);
		this.addPoint(posX, posY + 100);
	}
}