package modele;
import java.awt.Color;
import java.io.Serializable;

import vue.Symbole;


public class Piece extends Case implements Serializable{
	
	public static int id;
	private Quartier quartiers[] = new Quartier[4];
	private char orientation;
	private PlateauDeJeuModele plateauDeJeuModele;
	
	public Piece(int pPosX, int pPosY, char orientation, PlateauDeJeuModele pplateauDeJeuModele) {
		super(pPosX, pPosY);
		this.orientation = orientation;
		initialiserParametresQuartiers();
		initialiserPositionsQuartiers();
		plateauDeJeuModele = pplateauDeJeuModele;
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
		
		int tabCoordonnes[] = new int[2];
		
		int x1 = getPosX() * 100, xCentre = getPosX() * 100 + 50, x3 = getPosX() * 100 + 100;
		int y1 = getPosY() * 100, yCentre = getPosY() * 100 + 50, y3 = getPosY() * 100;
		
		
		// Boucle permettant d'affecter les coordonées x,y à nos 4 quartiers
		for(int i = 0; i <= 3; i++)
		{
			
			// 1. On ajoute les points dans le Quartier
			quartiers[i].addPoint(x1, y1);
			quartiers[i].addPoint(xCentre, yCentre);
			quartiers[i].addPoint(x3, y3);
			
			// 2. On fait appel à notre fonction pivot sur (x1, y1) et (x3, y3) pour les faire pivoter de 90°
			tabCoordonnes = pivot(90, x1, y1, xCentre, yCentre);
			x1 = tabCoordonnes[0];
			y1 = tabCoordonnes[1];
			
			tabCoordonnes = pivot(90, x3, y3, xCentre, yCentre);
			x3 = tabCoordonnes[0];
			y3 = tabCoordonnes[1];
		}
	}
	
	// Cette méthode permet de pivoter un point par rapport à un centre fournis en arguments
	private int[] pivot(int angle, int pX, int pY, int pXCentre, int pYCentre)
	{
		int tabCoordonnees[] = new int[2];
		
		//FIRST TRANSLATE THE DIFFERENCE
        int xtmp = pX - pXCentre;
        int ytmp = pY - pYCentre;

        //APPLY ROTATION
        double xT = ((double) xtmp * Math.cos(Math.toRadians(angle)) - ytmp * Math.sin(Math.toRadians(angle)));
        double yT = ((double) xtmp * Math.sin(Math.toRadians(angle)) + ytmp * Math.cos(Math.toRadians(angle)));

        //TRANSLATE BACK
        tabCoordonnees[0] = (int)Math.round(xT) + pXCentre;
        tabCoordonnees[1] = (int)Math.round(yT) + pYCentre;
        
		return tabCoordonnees;
	}

	public char getOrientation() {
		return orientation;
	}

	public void setOrientation(char orientation) {
				
		this.orientation = orientation;
	}
	public void tournerDroite() {
		
		Quartier tmp =  quartiers[0];
		
		quartiers[0] = quartiers[1];
		quartiers[1] = quartiers[2]; 
		quartiers[2] = quartiers[3]; 
		quartiers[3] = tmp;
		
		plateauDeJeuModele.miseAJour();
		
		
	}

	public static int getId() {
		return id;
	}

	public Quartier[] getQuartiers() {
		return quartiers;
	}
}