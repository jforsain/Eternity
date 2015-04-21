package modele;

import java.util.List;
import java.util.Observable;
import java.util.Random;

public class PlateauDeJeuModele extends Observable {
		/* Ne connait ni la VUE ni le CONTROLEUR */
	
	private Piece pieces[][];
	private boolean partieEnCours = false;
	
	public boolean isPartieEnCours() {
		return partieEnCours;
	}

	public void setPartieEnCours(boolean partieEnCours) {
		this.partieEnCours = partieEnCours;
	}

	private final int taillePlateau = 4;
	
	public Piece[][] getCases() {
		return this.pieces;
	}

	public void save()
	{
		
	}
	
	public void pivoterPiece()
	{
		
	}
	
	private void miseAJour()
	{
		setChanged();
		notifyObservers();
	}
	
	/*----- Cette méthode s'occupe de générer un terrain aléatoire*/
	public void nouvellePartie()
	{
		int idPiece = 0;
		
		pieces = new Piece[4][4];
		
		/*----- 1. On récupère la liste des quartier ----- */
		QuartierDao quartierDao = new QuartierDao();
		List<Quartier> quartiers = quartierDao.findQuartiers();

		/*----- 2.1 On crée les pièces coins ------- */
		this.pieces[0][0] = new Piece(++idPiece, quartiers.get(0), getRandomQuartier(quartierDao), getRandomQuartier(quartierDao), quartiers.get(0));
		this.pieces[0][taillePlateau - 1] = new Piece(++idPiece, quartiers.get(0), quartiers.get(0), getRandomQuartier(quartierDao), getRandomQuartier(quartierDao));
		this.pieces[taillePlateau - 1][0] = new Piece(++idPiece, getRandomQuartier(quartierDao), getRandomQuartier(quartierDao), quartiers.get(0), quartiers.get(0));
		this.pieces[taillePlateau - 1][taillePlateau - 1] = new Piece(++idPiece, getRandomQuartier(quartierDao), quartiers.get(0), quartiers.get(0), getRandomQuartier(quartierDao));
		
		
		/* ----- 2.2 On crée les pièces avec 1 face noire ------- */
		for(int i = 1; i < taillePlateau - 1; i++) // Ligne nord
			this.pieces[0][i] = new Piece(++idPiece, quartiers.get(0), getRandomQuartier(quartierDao), getRandomQuartier(quartierDao), getRandomQuartier(quartierDao));
		for(int i = 1; i < taillePlateau - 1; i++) // Ligne Ouest
			this.pieces[i][0] = new Piece(++idPiece, getRandomQuartier(quartierDao), getRandomQuartier(quartierDao), getRandomQuartier(quartierDao), quartiers.get(0));
		for(int i = 1; i < taillePlateau - 1; i++) // Ligne sud
			this.pieces[taillePlateau - 1][i] = new Piece(++idPiece, getRandomQuartier(quartierDao), getRandomQuartier(quartierDao), quartiers.get(0), getRandomQuartier(quartierDao));
		for(int i = 1; i < taillePlateau - 1; i++) // Ligne Est
			this.pieces[i][taillePlateau - 1] = new Piece(++idPiece, getRandomQuartier(quartierDao), quartiers.get(0), getRandomQuartier(quartierDao), getRandomQuartier(quartierDao));
		
		/* ----- 2.3 On crée les pièces restantes avec aucune face noire */
		for(int i =  1; i < taillePlateau - 1; i++)
		{
			for(int j = 1; j < taillePlateau - 1; j++)
			{	
				this.pieces[i][j] = new Piece(++idPiece, getRandomQuartier(quartierDao), getRandomQuartier(quartierDao), getRandomQuartier(quartierDao), getRandomQuartier(quartierDao));
			}
		}
		
		/* ----- 3.1 On s'occupe des liaisons EST-OUEST entre pièces ----- */
		for(int i = 0; i < taillePlateau; i++)
		{
			for(int j = 1; j < taillePlateau; j++)
			{
				this.pieces[i][j].setQuartierOuest(this.pieces[i][j - 1].getQuartierEst());
			}
		}
		
		/* ----- 3.2 On s'occupe des liasons NORD-SUD entre pièces ----- */
		for(int i = 1; i < taillePlateau; i++)
		{
			for(int j = 0; j < taillePlateau; j++)
			{
				this.pieces[i][j].setQuartierNord(this.pieces[i - 1][j].getQuartierSud());
			}
		}
		
		/* ----- 3.3 On notifie à la vue nos changements ----- */
		miseAJour();
	}
	
	public Piece[][] getPieces() {
		return pieces;
	}

	private Quartier getRandomQuartier(QuartierDao quartierDao)
	{
		List<Quartier> quartiers = quartierDao.findQuartiers();
		Random random = new Random();
		int nbreAleatoire = 1 + random.nextInt(quartiers.size() - 1);
		return quartiers.get(nbreAleatoire);
	}
}
