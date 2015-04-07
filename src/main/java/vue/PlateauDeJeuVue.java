package vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

import javax.swing.JPanel;

import modele.*;


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
		
		paintPiece(graphics, (Piece) this.plateauDeJeuModele.getCases()[0][1]);
			
	}
	
	public void paintPiece(Graphics graphics, Piece piece)
	{
		//1. On dessine les quartiers
		for(int i = 0; i < 4; i++)
		{
			graphics.setColor(piece.getQuartiers()[i].getCouleurFond());
			graphics.fillPolygon(piece.getQuartiers()[i]);
			graphics.setColor(Color.BLACK);
			graphics.drawPolygon(piece.getQuartiers()[i]);
		}
		
		
		//2. On dessine les symboles
	}
	
	private void paintTriangle(Graphics graphics)
	{
		int x1 = 25, x2 = 50, x3 = 75;
		int y1 = 0, y2 = 25, y3 = 0;
		int x[] = new int[3];
		int y[] = new int[3];
		
		x[0] = x1;
		x[1] = x2;
		x[2] = x3;
		
		y[0] = y1;
		y[1] = y2;
		y[2] = y3;
		
		graphics.setColor(Color.BLUE);
		graphics.fillPolygon(x, y, 3);
		graphics.setColor(Color.BLACK);

		graphics.fillPolygon(x, y, 3);
		graphics.drawPolygon(x, y, 3);
	}
	
	private void paintCarre(Graphics graphics)
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
	
	private void paintLine(Graphics graphics)
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
}