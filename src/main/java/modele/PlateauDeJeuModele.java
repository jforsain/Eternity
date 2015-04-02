package modele;

public class PlateauDeJeuModele implements Observable{
	
	/* Ne connait ni la VUE ni le CONTROLEUR */
	
	private Case cases[][];
	private String fileName;
	private int rectSize = 100;
	private int currentRow;
	private int currentCol;
	
	
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
	
	@Override
	public void addObserver(modele.Observer observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserver(String str) {
		// TODO Auto-generated method stub
		
	}
}