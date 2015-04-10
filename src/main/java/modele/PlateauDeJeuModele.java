package modele;

import java.util.Observable;
import java.util.Observer;

public class PlateauDeJeuModele extends Observable {
	
	public static int plateauTaille = 2;
	/* Ne connait ni la VUE ni le CONTROLEUR */
	
	private Case cases[][];
	private String fileName;
	private int rectSize = 100;
	private int currentRow;
	private int currentCol;
	
	
	public PlateauDeJeuModele()
	{
		cases = new Case[plateauTaille][plateauTaille];
		
		for(int i=0; i<plateauTaille;i++)
			for(int j=0; j<plateauTaille;j++)
				cases[i][j] = new Piece(i, j, 'N',this);
	}
	
	public Case[][] getCases() {
		return cases;
	}

	public void save()
	{
		
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
	
	public void inverser(Piece a, Piece b) {
		
		cases[a.posX][a.posY] = b.inverse(a);
		cases[b.posX][b.posY] = a.inverse(b);
		this.miseAJour();
	}
	
	public void selectionnerPiece(int row, int col)
	{
		
	}
	
	public void pivoterPiece()
	{
		
	}
	
	public void enleverPiece()
	{
		
	}
	
	public void miseAJour()
	{
		setChanged();
		notifyObservers();
	}
}