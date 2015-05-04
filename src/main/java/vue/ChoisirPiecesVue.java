package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import modele.C;
import modele.Piece;
import modele.PlateauDeJeuModele;
import modele.Quartier;
import modele.S;

import java.util.Observable;
import java.util.Observer;

public class ChoisirPiecesVue extends JPanel implements Observer{

	/* La VUE connait le MODELE */
	private PlateauDeJeuModele plateauDeJeuModele;
	
	public ChoisirPiecesVue(PlateauDeJeuModele pPlateauDeJeuModele)
	{
		this.plateauDeJeuModele = pPlateauDeJeuModele;
		this.plateauDeJeuModele.addObserver(this);
	}
	
	public int getWidth()
	{
		return 400;
	}
		
	public int getHeight()
	{
		return 400;
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(getWidth(), getHeight());
	}
	
	/* C'est cette méthode qui s'occupe de dessiner le plateau de jeu */
	public void paint(Graphics graphics)
	{
		super.paint(graphics);
		if(this.plateauDeJeuModele.getPartieEnCours()) /* Si le jeu a démarré */
		{	
			/* ---- TEST des positions ---- */
			for(int i = 0; i < this.plateauDeJeuModele.getCasesShuffle().length; i++)
			{
				for(int j = 0; j < this.plateauDeJeuModele.getCasesShuffle()[i].length; j++)
				{
					this.plateauDeJeuModele.getCasesShuffle()[i][j].setPosX(j);
					this.plateauDeJeuModele.getCasesShuffle()[i][j].setPosY(i);
				}
			}
					
			for(int i = 0; i < this.plateauDeJeuModele.getCasesShuffle().length; i++)
				for(int j = 0; j < this.plateauDeJeuModele.getCasesShuffle()[i].length; j++)
					paintPiece(graphics, (Piece) this.plateauDeJeuModele.getCasesShuffle()[i][j]);
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
	

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.repaint();
		
	}
	
	// Cette méthode s'occupe de dessiner les pièces avec leurs syboles respectifs.
	public void paintPiece(Graphics graphics, Piece piece)
	{	
		paintQuartier(graphics, piece.getQuartierNord(), 0, piece.getPosX(), piece.getPosY());
		paintQuartier(graphics, piece.getQuartierEst(), 1, piece.getPosX(), piece.getPosY());
		paintQuartier(graphics, piece.getQuartierSud(), 2, piece.getPosX(), piece.getPosY());
		paintQuartier(graphics, piece.getQuartierOuest(), 3, piece.getPosX(), piece.getPosY());
		
		paintSymbole(graphics, piece.getQuartierNord(), 0, piece.getPosX(), piece.getPosY());
		paintSymbole(graphics, piece.getQuartierEst(), 1,  piece.getPosX(), piece.getPosY());
		paintSymbole(graphics, piece.getQuartierSud(), 2,  piece.getPosX(), piece.getPosY());
		paintSymbole(graphics, piece.getQuartierOuest(), 3,  piece.getPosX(), piece.getPosY());
	}
	
	private void paintQuartier(Graphics graphics, Quartier quartier, int indice, int pPosX, int pPosY)
	{
		int tab[] = new int[2];
		
		int x1 = pPosX * 100, xCentre = pPosX * 100 + 50, x3 = pPosX * 100 + 100;
		int y1 = pPosY * 100, yCentre = pPosY * 100 + 50, y3 = pPosY * 100;
		
		int xT[] = {x1, xCentre, x3};
		int yT[] = {y1, yCentre, y3};
		
		
		// 2. On pivote si nécéssaire la figure par rapport à l'indice
		if(indice != 0)
		{
			for(int i = 0; i < 3; i++)
			{
				tab = pivot(90 * indice, xT[i], yT[i], xT[1], yT[1]);
				xT[i] = tab[0];
				yT[i] = tab[1];
			}
		}
				
		// 3. On dessine la figure
		graphics.setColor(C.get(quartier.getCouleurFond()));
		graphics.fillPolygon(xT, yT, 3);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT, yT, 3);
		
	}
	
	private void paintSymbole(Graphics graphics, Quartier pQuartier, int indice, int pPosX, int pPosY)
	{
		if(!pQuartier.getSymbole().isEmpty())
		{
			switch(S.valueOf(pQuartier.getSymbole()))
			{
			case triangle:
				paintTriangle(graphics, pPosX * 100, pPosY * 100, pPosX * 100 + 50, pPosY * 100 + 50, indice, C.get(pQuartier.getCouleurForme()));
				break;
			case carre:
				paintCarre(graphics, pPosX * 100, pPosY * 100, pPosX * 100 + 50, pPosY * 100 + 50, indice, C.get(pQuartier.getCouleurForme()));
				break;
			case ligne:
				paintLine(graphics, pPosX * 100, pPosY * 100, pPosX * 100 + 50, pPosY * 100 + 50, indice, C.get(pQuartier.getCouleurForme()));
				break;
			case soleil:
				paintSun(graphics, pPosX * 100, pPosY * 100, pPosX * 100 + 50, pPosY * 100 + 50, indice, C.get(pQuartier.getCouleurForme()));
				break;
			case lignes_verticales:
				paintDoubleHeightLine(graphics, pPosX * 100, pPosY * 100, pPosX * 100 + 50, pPosY * 100 + 50, indice, C.get(pQuartier.getCouleurForme()));
				break;
			case lignes_horizontales:
				paintDoubleWidthLine(graphics, pPosX * 100, pPosY * 100, pPosX * 100 + 50, pPosY * 100 + 50, indice, C.get(pQuartier.getCouleurForme()));
				break;
			case couronne:
				paintCouronne(graphics, pPosX * 100, pPosY * 100, pPosX * 100 + 50, pPosY * 100 + 50, indice, C.get(pQuartier.getCouleurForme()));
				break;
					
			}
		}
	}
	
	/* ------ METHODES DESSINS SYMBOLES ------ */
	
	private void paintCouronne(Graphics graphics, int posX, int posY, int xCentre, int yCentre, int indice, Color couleurSymbole)
	{
		int tab[];
		
		int xT[] = {45, 35, 45, 50, 55, 65, 55};
		int yT[] = {0, 10, 10, 15, 10, 10, 0};
		
		// 1. On additionne toutes ces coordonées par posX et posY
		for(int i = 0; i < xT.length; i++)
		{
			xT[i] = xT[i] + posX;
			yT[i] = yT[i] + posY;
		}
		// 2. On pivote si nécéssaire la figure par rapport à l'indice
		if(indice != 0)
		{
			for(int i = 0; i < xT.length; i++)
			{
				tab = pivot(90 * indice, xT[i], yT[i], xCentre, yCentre);
				xT[i] = tab[0];
				yT[i] = tab[1];
			}
		}
		
		graphics.setColor(couleurSymbole);
		graphics.fillPolygon(xT, yT, 7);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT, yT, 7);
	}
	
	private void paintTriangle(Graphics graphics, int posX, int posY, int xCentre, int yCentre, int indice, Color couleurSymbole)
	{
		int tab[];
		
		// Coordonées de départ
		int xT[] = {25, 50, 75};
		int yT[] = {0, 25, 0};
		
		// 1. On additionne toutes ces coordonées par posX et posY
		for(int i = 0; i < xT.length; i++)
		{
			xT[i] = xT[i] + posX;
			yT[i] = yT[i] + posY;
		}
		// 2. On pivote si nécéssaire la figure par rapport à l'indice
		if(indice != 0)
		{
			for(int i = 0; i < 3; i++)
			{
				tab = pivot(90 * indice, xT[i], yT[i], xCentre, yCentre);
				xT[i] = tab[0];
				yT[i] = tab[1];
			}
		}
		
		// 3. On dessine la figure
		graphics.setColor(couleurSymbole);
		graphics.fillPolygon(xT, yT, 3);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT, yT, 3);
		
	}

	private void paintCarre(Graphics graphics, int posX, int posY, int xCentre, int yCentre, int indice, Color couleurSymbole)
	{

		int tab[];
		
		int xT[] = {40, 40, 60, 60};
		int yT[] = {0, 20, 20, 0};
		
		
		// 1. On additionne toutes ces coordonées par posX et posY
		for(int i = 0; i < xT.length; i++)
		{
			xT[i] = xT[i] + posX;
			yT[i] = yT[i] + posY;
		}
		// 2. On pivote si nécéssaire la figure par rapport à l'indice
		if(indice != 0)
		{
			for(int i = 0; i < 4; i++)
			{
				tab = pivot(90 * indice, xT[i], yT[i], xCentre, yCentre);
				xT[i] = tab[0];
				yT[i] = tab[1];
			}
		}
		
		// 3. On dessine la figure
		graphics.setColor(couleurSymbole);
		graphics.fillPolygon(xT, yT, 4);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT, yT, 4);
	}
	
	private void paintLine(Graphics graphics, int posX, int posY, int xCentre, int yCentre, int indice, Color couleurSymbole)
	{
		int tab[];
		
		int xT[] = {40, 40, 50, 60, 60};
		int yT[] = {0, 40, 50, 40, 0};
		
		
		// 1. On additionne toutes ces coordonées par posX et posY
		for(int i = 0; i < xT.length; i++)
		{
			xT[i] = xT[i] + posX;
			yT[i] = yT[i] + posY;
		}
		// 2. On pivote si nécéssaire la figure par rapport à l'indice
		if(indice != 0)
		{
			for(int i = 0; i < 5; i++)
			{
				tab = pivot(90 * indice, xT[i], yT[i], xCentre, yCentre);
				xT[i] = tab[0];
				yT[i] = tab[1];
			}
		}
		
		// 3. On dessine la figure
		graphics.setColor(couleurSymbole);
		graphics.fillPolygon(xT, yT, 5);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT, yT, 5);
	}
	
	private void paintSun(Graphics graphics, int posX, int posY, int xCentre, int yCentre, int indice, Color couleurSymbole)
	{
		int tab[];
		
		int xT[] = {35, 40, 40, 45, 50, 55, 60, 60, 65};
		int yT[] = {0, 5, 10, 10 , 15, 10, 10, 5, 0};
		
		// 1. On additionne toutes ces coordonées par posX et posY
		for(int i = 0; i < xT.length; i++)
		{
			xT[i] = xT[i] + posX;
			yT[i] = yT[i] + posY;
		}
		// 2. On pivote si nécéssaire la figure par rapport à l'indice
		if(indice != 0)
		{
			for(int i = 0; i < xT.length; i++)
			{
				tab = pivot(90 * indice, xT[i], yT[i], xCentre, yCentre);
				xT[i] = tab[0];
				yT[i] = tab[1];
			}
		}
		
		graphics.setColor(couleurSymbole);
		graphics.fillPolygon(xT, yT, 9);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT, yT, 9);
	}
	
	private void paintDoubleWidthLine(Graphics graphics, int posX, int posY, int xCentre, int yCentre, int indice, Color couleurSymbole)
	{
		int tab[];
		
		/* ---- PART 1 ----- */
		int xT[] = {35, 50, 65};
		int yT[] = {35, 50, 35};
		
		// 1. On additionne toutes ces coordonées par posX et posY
		for(int i = 0; i < xT.length; i++)
		{
			xT[i] = xT[i] + posX;
			yT[i] = yT[i] + posY;
		}
		// 2. On pivote si nécéssaire la figure par rapport à l'indice
		if(indice != 0)
		{
			for(int i = 0; i < 3; i++)
			{
				tab = pivot(90 * indice, xT[i], yT[i], xCentre, yCentre);
				xT[i] = tab[0];
				yT[i] = tab[1];
			}
		}
		
		// 3. On dessine la figure
		graphics.setColor(couleurSymbole);
		graphics.fillPolygon(xT, yT, 3);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT, yT, 3);
		
		
		
		/* ---- PART 2 ----- */
		
		int xT2[] = {15, 25, 75, 85};
		int yT2[] = {15, 25, 25, 15};
		
		
		// 1. On additionne toutes ces coordonées par posX et posY
		for(int i = 0; i < xT2.length; i++)
		{
			xT2[i] = xT2[i] + posX;
			yT2[i] = yT2[i] + posY;
		}
		// 2. On pivote si nécéssaire la figure par rapport à l'indice
		if(indice != 0)
		{
			for(int i = 0; i < 4; i++)
			{
				tab = pivot(90 * indice, xT2[i], yT2[i], xCentre, yCentre);
				xT2[i] = tab[0];
				yT2[i] = tab[1];
			}
		}
		
		
		graphics.setColor(couleurSymbole);
		graphics.fillPolygon(xT2, yT2, 4);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT2, yT2, 4);
	}
	
	private void paintDoubleHeightLine(Graphics graphics, int posX, int posY, int xCentre, int yCentre, int indice, Color couleurSymbole)
	{
		int tab[];
		
		
		/* --- PART 1 ---*/
		int xT[] = {30, 30, 40 ,40};
		int yT[] = {0, 30, 40, 0};
		
		
		// 1. On additionne toutes ces coordonées par posX et posY
		for(int i = 0; i < xT.length; i++)
		{
			xT[i] = xT[i] + posX;
			yT[i] = yT[i] + posY;
		}
		// 2. On pivote si nécéssaire la figure par rapport à l'indice
		if(indice != 0)
		{
			for(int i = 0; i < 4; i++)
			{
				tab = pivot(90 * indice, xT[i], yT[i], xCentre, yCentre);
				xT[i] = tab[0];
				yT[i] = tab[1];
			}
		}
		
		
		graphics.setColor(couleurSymbole);
		graphics.fillPolygon(xT, yT, 4);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT, yT, 4);
		
		
		/* --- PART 2 ---*/
		
		int xT2[] = {60, 60, 70, 70};
		int yT2[] = {0,40, 30, 0};
		
		
		// 1. On additionne toutes ces coordonées par posX et posY
		for(int i = 0; i < xT2.length; i++)
		{
			xT2[i] = xT2[i] + posX;
			yT2[i] = yT2[i] + posY;
		}
		// 2. On pivote si nécéssaire la figure par rapport à l'indice
		if(indice != 0)
		{
			for(int i = 0; i < 4; i++)
			{
				tab = pivot(90 * indice, xT2[i], yT2[i], xCentre, yCentre);
				xT2[i] = tab[0];
				yT2[i] = tab[1];
			}
		}
		
		graphics.setColor(couleurSymbole);
		graphics.fillPolygon(xT2, yT2, 4);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT2, yT2, 4);
	}
}
