package modele;

public class PlateauDeJeuModele implements Observable{
	
	/* Ne connait ni la VUE ni le CONTROLEUR */
	
	private Case cases[][];
	private String fileName;
	private int rectSize = 100;
	private int currentRow;
	private int currentCol;
	
	
	public PlateauDeJeuModele()
	{
		cases = new Case[4][4];
		for(int i = 0; i < cases.length; i++)
		{
			for(int j = 0; j < cases[i].length; j++)
			{
				cases[i][j] = new Vide(i * 100, j * 100);
			}
		}
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
	
	public void selectionnerPiece(int row, int col)
	{
		
	}
	
	public void pivoterPiece()
	{
		
	}
	
	public void enleverPiece()
	{
		
	}
	
	
	
	public void addObserver(modele.Observer observer) {
		// TODO Auto-generated method stub
		
	}

	
	public void removeObserver() {
		// TODO Auto-generated method stub
		
	}

	
	public void notifyObserver(String str) {
		// TODO Auto-generated method stub
		
	}
}