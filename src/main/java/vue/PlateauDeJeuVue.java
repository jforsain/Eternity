package vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

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
		int x = 0;
		int y = 0;
		
		super.paint(graphics);
		
		/* Dessine le plateau de jeu */
		graphics.setColor(Color.GRAY);
		graphics.fillRect(0, 0, getWidth(), getHeight());
		
		
		dessinerFaceNord(graphics);
		dessinerFaceEst(graphics);
		dessinerFaceSud(graphics);
		dessinerFaceOuest(graphics);
		
		graphics.setColor(Color.BLACK);
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				graphics.drawRect(x, y, 100, 100);
				x += 100;
			}
			x = 0;
			y += 100;
		}
<<<<<<< Upstream, based on origin/master
		//dessinerSymboleTriangle(graphics);
		paintCircle(graphics,50,0,20);
=======
//		paintTriangle(graphics);
//		paintCarre(graphics);
//		paintLine(graphics);
		
>>>>>>> 9c230d6 Terminaison des 4 symboles
	}

	private void dessinerFaceOuest(Graphics graphics) {
		// TODO Auto-generated method stub
		graphics.setColor(Color.GREEN);
		int x1 = 0, x2 = 50, x3 = 0;
		int y1 = 0, y2 = 50, y3 = 100;
		
		int xP[] = new int[3];
		int yP[] = new int[3];
		
		xP[0] = x1;
		xP[1] = x2;
		xP[2] = x3;
		
		yP[0] = y1;
		yP[1] = y2;
		yP[2] = y3;
		
		for(int l = 0 ; l < 4; l++)
		{	
			for(int m = 0; m < 4; m++)
			{
				graphics.fillPolygon(xP, yP, 3);
				graphics.setColor(Color.BLACK);
				graphics.drawPolygon(xP, yP, 3);
				graphics.setColor(Color.GREEN);
				x1 += 100;
				x2 += 100;
				x3 += 100;
				
				xP[0] = x1;
				xP[1] = x2;
				xP[2] = x3;
			}
			
			x1 = 0;
			x2 = 50;
			x3 = 0;
			
			xP[0] = x1;
			xP[1] = x2;
			xP[2] = x3;
			
			y1 += 100;
			y2 += 100;
			y3 += 100;
			
			yP[0] = y1;
			yP[1] = y2;
			yP[2] = y3;
		}
	}

	private void dessinerFaceSud(Graphics graphics) {
		// TODO Auto-generated method stub
		graphics.setColor(Color.BLUE);
		int x1 = 0, x2 = 50, x3 = 100;
		int y1 = 100, y2 = 50, y3 = 100;
		
		int xP[] = new int[3];
		int yP[] = new int[3];
		
		xP[0] = x1;
		xP[1] = x2;
		xP[2] = x3;
		
		yP[0] = y1;
		yP[1] = y2;
		yP[2] = y3;
		
		for(int l = 0 ; l < 4; l++)
		{	
			for(int m = 0; m < 4; m++)
			{
				graphics.fillPolygon(xP, yP, 3);
				graphics.setColor(Color.BLACK);
				graphics.drawPolygon(xP, yP, 3);
				graphics.setColor(Color.BLUE);
				x1 += 100;
				x2 += 100;
				x3 += 100;
				
				xP[0] = x1;
				xP[1] = x2;
				xP[2] = x3;
			}
			
			x1 = 0;
			x2 = 50;
			x3 = 100;
			
			xP[0] = x1;
			xP[1] = x2;
			xP[2] = x3;
			
			y1 += 100;
			y2 += 100;
			y3 += 100;
			
			yP[0] = y1;
			yP[1] = y2;
			yP[2] = y3;
		}
	}

	private void dessinerFaceNord(Graphics graphics) {
		// TODO Auto-generated method stub
		graphics.setColor(Color.RED);
		int x1 = 0, x2 = 50, x3 = 100;
		int y1 = 0, y2 = 50, y3 = 0;
		
		int xP[] = new int[3];
		int yP[] = new int[3];
		
		xP[0] = x1;
		xP[1] = x2;
		xP[2] = x3;
		
		yP[0] = y1;
		yP[1] = y2;
		yP[2] = y3;
		
		for(int l = 0 ; l < 4; l++)
		{	
			for(int m = 0; m < 4; m++)
			{
				graphics.fillPolygon(xP, yP, 3);
				graphics.setColor(Color.BLACK);
				graphics.drawPolygon(xP, yP, 3);
				graphics.setColor(Color.RED);
				x1 += 100;
				x2 += 100;
				x3 += 100;
				
				xP[0] = x1;
				xP[1] = x2;
				xP[2] = x3;
			}
			
			x1 = 0;
			x2 = 50;
			x3 = 100;
			
			xP[0] = x1;
			xP[1] = x2;
			xP[2] = x3;
			
			y1 += 100;
			y2 += 100;
			y3 += 100;
			
			yP[0] = y1;
			yP[1] = y2;
			yP[2] = y3;
		}
	}
	
	private void dessinerFaceEst(Graphics graphics){
		graphics.setColor(Color.YELLOW);
		int x1 = 100, x2 = 50, x3 = 100;
		int y1 = 0, y2 = 50, y3 = 100;
		
		int xP[] = new int[3];
		int yP[] = new int[3];
		
		xP[0] = x1;
		xP[1] = x2;
		xP[2] = x3;
		
		yP[0] = y1;
		yP[1] = y2;
		yP[2] = y3;
		
		for(int l = 0 ; l < 4; l++)
		{	
			for(int m = 0; m < 4; m++)
			{
				graphics.fillPolygon(xP, yP, 3);
				graphics.setColor(Color.BLACK);
				graphics.drawPolygon(xP, yP, 3);
				graphics.setColor(Color.YELLOW);
				x1 += 100;
				x2 += 100;
				x3 += 100;
				
				xP[0] = x1;
				xP[1] = x2;
				xP[2] = x3;
			}
			
			x1 = 100;
			x2 = 50;
			x3 = 100;
			
			xP[0] = x1;
			xP[1] = x2;
			xP[2] = x3;
			
			y1 += 100;
			y2 += 100;
			y3 += 100;
			
			yP[0] = y1;
			yP[1] = y2;
			yP[2] = y3;
		}
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
<<<<<<< Upstream, based on origin/master
		graphics.fillPolygon(x, y, 3);
		
=======
		graphics.drawPolygon(x, y, 3);
>>>>>>> 9c230d6 Terminaison des 4 symboles
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
