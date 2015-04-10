package vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import modele.*;


@SuppressWarnings("serial")
public class PlateauDeJeuVue extends JPanel implements Observer{
	
	private Case cases[][];
	private String fileName;
	private int rectSize = 100;
	private int currentRow = -1;
	private int currentCol = -1;
	
	/* La VUE connait le MODELE */
	private PlateauDeJeuModele plateauDeJeuModele;
	
	public PlateauDeJeuVue(PlateauDeJeuModele pPlateauDeJeuModele)
	{
		this.plateauDeJeuModele = pPlateauDeJeuModele;
		
		this.plateauDeJeuModele.addObserver(this);
	}
	
	public int getRectSize()
	{
		return rectSize;
	}
	
	public int getSelectedRow() {
		return currentRow;
	}

	public int getSelectedCol() {
		return currentCol;
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
	
	/* C'est cette m�thode qui s'occupe de dessiner le plateau de jeu */
	
	public void paint(Graphics graphics)
	{
		
		super.paint(graphics);
//		for(int i = 0; i < this.plateauDeJeuModele.getCases()[i].length; i++)
//			for(int j = 0; j < this.plateauDeJeuModele.getCases()[i]; i++)
//				paintPiece(graphics, (Piece) this.plateauDeJeuModele.getCases()[0][0]);
		
			
	}
	
	// Cette méthode s'occupe de dessiner les pièces avec leurs syboles respectifs.
	public void paintPiece(Graphics graphics, Piece piece)
	{
		//1. On dessine les quartiers
		for(int i = 0; i < 4; i++)
		{
//			graphics.setColor(piece.getQuartiers()[i].getCouleurFond());
//			graphics.fillPolygon(piece.getQuartiers()[i]);
//			graphics.setColor(Color.BLACK);
//			graphics.drawPolygon(piece.getQuartiers()[i]);
			paintQuartier(graphics, piece.getQuartiers()[i], i, piece.getPosX(), piece.getPosY());
		}
		
		//2. On dessine les symboles
		for(int j = 0; j < 4; j++)
		{
			switch(piece.getQuartiers()[j].getSymbole())
			{

				case TRIANGLE:
					paintTriangle(graphics, piece.getPosX() * 100, piece.getPosY() * 100, piece.getPosX() * 100 + 50, piece.getPosY() * 100 + 50, j, piece.getQuartiers()[j].getCouleurForme());
					break;
				case CARRE:
					paintCarre(graphics, piece.getPosX() * 100, piece.getPosY() * 100, piece.getPosX() * 100 + 50, piece.getPosY() * 100 + 50, j, piece.getQuartiers()[j].getCouleurForme());
					break;
				case TRAIT:
					paintLine(graphics, piece.getPosX() * 100, piece.getPosY() * 100, piece.getPosX() * 100 + 50, piece.getPosY() * 100 + 50, j, piece.getQuartiers()[j].getCouleurForme());
					break;
				case SUN:
					paintSun(graphics, piece.getPosX() * 100, piece.getPosY() * 100, piece.getPosX() * 100 + 50, piece.getPosY() * 100 + 50, j, piece.getQuartiers()[j].getCouleurForme());
					break;
				case DOUBLELINEHEIGHT:
					paintDoubleHeightLine(graphics, piece.getPosX() * 100, piece.getPosY() * 100, piece.getPosX() * 100 + 50, piece.getPosY() * 100 + 50, j, piece.getQuartiers()[j].getCouleurForme());
					break;
				case DOUBLELINEWIDTH:
					paintDoubleWidthLine(graphics, piece.getPosX() * 100, piece.getPosY() * 100, piece.getPosX() * 100 + 50, piece.getPosY() * 100 + 50, j, piece.getQuartiers()[j].getCouleurForme());
					break;
				case KING:
					paintCouronne(graphics, piece.getPosX() * 100, piece.getPosY() * 100, piece.getPosX() * 100 + 50, piece.getPosY() * 100 + 50, j, piece.getQuartiers()[j].getCouleurForme());
					break;
			}
			
		}
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
		graphics.setColor(quartier.getCouleurFond());
		graphics.fillPolygon(xT, yT, 3);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT, yT, 3);
		
	}
	// DONE
	private void paintCouronne(Graphics graphics, int posX, int posY, int xCentre, int yCentre, int indice, Color couleurSymbole)
	{
		int xT[] = {45, 35, 45, 50, 55, 65, 55};
		int yT[] = {0, 10, 10, 15, 10, 10, 0};
		graphics.setColor(Color.WHITE);
		graphics.fillPolygon(xT, yT, 7);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT, yT, 7);
	}
	
	// DONE
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
	
	// DONE
	private void paintCarre(Graphics graphics, int posX, int posY, int xCentre, int yCentre, int indice, Color couleurSymbole)
	{
		int x1 = 40, x2 = 40, x3 = 60, x4 = 60;
		int y1 = 0, y2 = 20, y3 = 20, y4 = 0;
		int x[] = new int[4];
		int y[] = new int[4];
		
		x[0] = x1;
		x[1] = x2;
		x[2] = x3;
		x[3] = x4;
		
		y[0] = y1;
		y[1] = y2;
		y[2] = y3;
		y[3] = y4;
		
		graphics.setColor(Color.YELLOW);
		graphics.fillPolygon(x, y, 4);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(x, y, 4);
	}
	
	// DONE
	private void paintLine(Graphics graphics, int posX, int posY, int xCentre, int yCentre, int indice, Color couleurSymbole)
	{
		int x1 = 40, x2 = 40, x5 = 50, x3 = 60, x4 = 60;
		int y1 = 0, y2 = 40, y5 = 50, y3 = 40, y4 = 0;
		int x[] = new int[5];
		int y[] = new int[5];
		
		x[0] = x1;
		x[1] = x2;
		x[2] = x5;
		x[3] = x3;
		x[4] = x4;
		
		y[0] = y1;
		y[1] = y2;
		y[2] = y5;
		y[3] = y3;
		y[4] = y4;
		
		graphics.setColor(Color.YELLOW);
		graphics.fillPolygon(x, y, 5);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(x, y, 5);
		
		
		for(int i = 0; i < 5; i++)
		{
			System.out.println(y[i]);
		}
		
		/* ---- TEST PIVOT ---- */
		 //GET THE ANGLE IN RADIANS
        double angle = Math.toRadians(90);

        for(int i = 0; i < 5; i++)
        {
        	
	        //FIRST TRANSLATE THE DIFFERENCE
	        int xtmp = x[i] - 50;
	        int ytmp = y[i] - 50;
	
	        //APPLY ROTATION
	        double xT = ((double) xtmp * Math.cos(angle) - ytmp * Math.sin(angle));
	        double yT = ((double) xtmp * Math.sin(angle) + ytmp * Math.cos(angle));
	
	        //TRANSLATE BACK
	        x[i] = (int)Math.round(xT) + 50;
	        y[i] = (int)Math.round(yT) + 50;
	        
        }
        
        graphics.setColor(Color.BLACK);
		graphics.fillPolygon(x, y, 5);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(x, y, 5);
        
		for(int i = 0; i < 5; i++)
		{
			System.out.println(y[i]);
		}
	}
	
	// A compléter
	private void paintZigZag(Graphics graphics)
	{
		int xT[] = {50, 40, 45, 40, 45, 40, 45, 40, 45, 40, 60, 65, 60, 65, 60, 65, 60, 65, 60};
		int yT[] = {50, 40, 35, 30, 25, 20, 15, 10, 5 , 0,  0,  5,  10, 15, 20, 25, 30, 35, 40};
		graphics.setColor(Color.WHITE);
		graphics.fillPolygon(xT, yT, 19);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT, yT, 19);
		
	}
	
	// DONE
	private void paintSun(Graphics graphics, int posX, int posY, int xCentre, int yCentre, int indice, Color couleurSymbole)
	{
		int xT[] = {35, 40, 40, 45, 50, 55, 60, 60, 65};
		int yT[] = {0, 5, 10, 10 , 15, 10, 10, 5, 0};
		graphics.setColor(Color.WHITE);
		graphics.fillPolygon(xT, yT, 9);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT, yT, 9);
	}
	
	// DONE
	private void paintDoubleWidthLine(Graphics graphics, int posX, int posY, int xCentre, int yCentre, int indice, Color couleurSymbole)
	{
		int xT[] = {35, 50, 65};
		int yT[] = {35, 50, 35};
		graphics.setColor(Color.YELLOW);
		graphics.fillPolygon(xT, yT, 3);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT, yT, 3);
		
		
		int xT2[] = {15, 25, 75, 85};
		int yT2[] = {15, 25, 25, 15};
		graphics.setColor(Color.YELLOW);
		graphics.fillPolygon(xT2, yT2, 4);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT2, yT2, 4);
	}
	
	// DONE
	private void paintDoubleHeightLine(Graphics graphics, int posX, int posY, int xCentre, int yCentre, int indice, Color couleurSymbole)
	{
		int xT[] = {30, 30, 40 ,40};
		int yT[] = {0, 30, 40, 0};
		
		int xT2[] = {60, 60, 70, 70};
		int yT2[] = {0,40, 30, 0};
		
		graphics.setColor(Color.YELLOW);
		graphics.fillPolygon(xT, yT, 4);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT, yT, 4);
		graphics.setColor(Color.YELLOW);
		graphics.fillPolygon(xT2, yT2, 4);
		graphics.setColor(Color.BLACK);
		graphics.drawPolygon(xT2, yT2, 4);
	}
	
	public void update(String str) {
		// TODO Auto-generated method stub
		
		this.repaint();
		 
	}
	
	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public int getCurrentCol() {
		return currentCol;
	}

	public void setCurrentCol(int currentCol) {
		this.currentCol = currentCol;
	}
	
	public void paintCircle(Graphics g, int x, int y, int diameter) 
	{
		   x = x - diameter/2;
		   Graphics2D g2d = (Graphics2D)g;
		   Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter);
		   g2d.setColor(Color.BLUE);
		   g2d.fill(circle);   
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.repaint();
		
	}
	
	
}