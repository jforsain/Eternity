package modele;
import java.awt.Color;
import java.io.Serializable;

import vue.Symbole;


public class Piece extends Case implements Serializable{
	
	private int id;
	private Quartier quartiers[] = new Quartier[4];
	private char orientation;
	private PlateauDeJeuModele plateauDeJeuModele;
	
	public Piece(int id,int pPosX, int pPosY, char orientation, PlateauDeJeuModele pplateauDeJeuModele) {
		super(pPosX, pPosY);
		this.orientation = orientation;
		initialiserParametresQuartiers();
		initialiserPositionsQuartiers();
		plateauDeJeuModele = pplateauDeJeuModele;
		this.id = id;
	}
	
	public Piece(Piece a) 
	{
		super(a.getPosX(), a.getPosY());
		this.orientation = a.orientation;
		this.quartiers = a.quartiers;
		plateauDeJeuModele = a.plateauDeJeuModele;
		this.id = a.id;
	}
	// Cette méthode s'occupe d'initialiser les paramètres des 4 quartiers
	private void initialiserParametresQuartiers() {
		
		// A améliorer avec le fichier .csv
		quartiers[0] = new Quartier('P', 1, Color.YELLOW, Color.BLUE, Symbole.TRIANGLE);
		quartiers[1] = new Quartier('P', 2, Color.BLUE, Color.RED, Symbole.TRIANGLE);
		quartiers[2] = new Quartier('P', 3, Color.RED, Color.YELLOW, Symbole.TRIANGLE);
		quartiers[3] = new Quartier('P', 4, Color.PINK, Color.WHITE, Symbole.TRIANGLE);
		
	}

	// Cette méthode s'occupe d'initialiser les coordonées des 4 quartiers de la pièce de puzzle en fonction de sa position
	private void initialiserPositionsQuartiers() {
		
		int x1 = getPosX() * 100, xCentre = getPosX() * 100 + 50, x3 = getPosX() * 100 + 100;
		int y1 = getPosY() * 100, yCentre = getPosY() * 100 + 50, y3 = getPosY() * 100;
	}

	public char getOrientation() {
		return orientation;
	}

	public void setOrientation(char orientation) {
				
		this.orientation = orientation;
	}
	public void tournerDroite() {
		
		Quartier tmp =  quartiers[1];//rouge
		
		quartiers[0] = quartiers[1]; // rouge
		quartiers[1] = quartiers[3]; //blanc
		quartiers[2] = quartiers[0]; //bleu 
		quartiers[3] = tmp;
		
		plateauDeJeuModele.miseAJour();
	}

	public int getId() {
		return id;
	}

	public Quartier[] getQuartiers() {
		return quartiers;
	}
	
	public Piece inverse(Piece a)
	{
		this.id = a.id;
		this.quartiers = a.quartiers;
		this.orientation = a.orientation;
		
		return this;
	}
}
