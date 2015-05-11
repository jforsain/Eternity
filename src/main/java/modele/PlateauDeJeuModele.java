package modele;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import au.com.bytecode.opencsv.CSVWriter;

public class PlateauDeJeuModele extends Observable {
	/* Ne connait ni la VUE ni le CONTROLEUR */

	private Case cases[][]; // Modèle à obtenir
	private Case casesShuffle[][]; // Réserve
	private Case plateau[][]; // Plateau
	private boolean partieEnCours = false;
	private boolean partieEnPause = false;
	private final int taillePlateau = 4;
	private boolean aUtiliseAide = false;
	private boolean giveup = false;

	public void sauvegarderPartie(String nameFile) {
		String solutionPieces[][];
		String plateauJeuPieces[][] = new String[16][5];
		String plateauChoisirPieces[][] = new String[16][5];
		try {
			
			CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/"+nameFile+".csv"), ';');
			solutionPieces = conversionStringPiece();
			
			for(String elem[]: solutionPieces)
	     		writer.writeNext(elem);
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String[][] conversionStringPiece()
	{
		String idPiece;
		String positionX;
		String positionY;
		String orientation;
		int indice = 0;
		
		String[][] casesModele = new String[16][5];
		String[] check = new String[5];
		
		for (int i = 0; i < cases.length; i++)
		{
			for (int j = 0; j < cases[i].length; j++) 
			{
				// 1. On récupère la case
				Case c = cases[i][j];
				
				// 2. On convertit en String
				positionX = Integer.toString(c.getPosX());
				positionY = Integer.toString(c.getPosY());
				
				if(c instanceof Piece)
				{
					orientation = "" + ((Piece) c).getOrientation();
					idPiece = Integer.toString(((Piece) c).getIdPiece());
				}
				else
				{
					orientation = "";
					idPiece = "-1";
				}
				
				
				if(c instanceof Piece)
					check[0] = "P";
				else
					check[0] = "V";
				
				check[1] = idPiece;
				check[2] = positionX;
				check[3] = positionY;
				check[4] = orientation;
							
				casesModele[indice][0] = check[0];
				casesModele[indice][1] = check[1];
				casesModele[indice][2] = check[2];
				casesModele[indice][3] = check[3];
				casesModele[indice][4] = check[4];
				
				indice++;
			}
		}
		return casesModele;
	}
	
	public void chargerPartie(String nameFile) {

	}

	public void miseAJour() {
		setChanged();
		notifyObservers();
	}

	/*----- Cette méthode s'occupe de générer un terrain aléatoire et qui servira de modèle à représenter  ----- */
	public void nouvellePartie() {
		int idPiece = 0;
		cases = new Case[4][4];

		/*----- 1. On récupère la liste des quartier ----- */
		QuartierDao quartierDao = new QuartierDao();
		List<Quartier> quartiers = quartierDao.findQuartiers();

		/*----- 2.1 On crée les pièces coins ------- */
		this.cases[0][0] = new Piece(++idPiece, quartiers.get(0),
				getRandomQuartier(quartierDao), getRandomQuartier(quartierDao),
				quartiers.get(0));
		this.cases[0][taillePlateau - 1] = new Piece(++idPiece,
				quartiers.get(0), quartiers.get(0),
				getRandomQuartier(quartierDao), getRandomQuartier(quartierDao));
		this.cases[taillePlateau - 1][0] = new Piece(++idPiece,
				getRandomQuartier(quartierDao), getRandomQuartier(quartierDao),
				quartiers.get(0), quartiers.get(0));
		this.cases[taillePlateau - 1][taillePlateau - 1] = new Piece(++idPiece,
				getRandomQuartier(quartierDao), quartiers.get(0),
				quartiers.get(0), getRandomQuartier(quartierDao));

		/* ----- 2.2 On crée les pièces avec 1 face noire ------- */
		for (int i = 1; i < taillePlateau - 1; i++)
			// Ligne nord
			this.cases[0][i] = new Piece(++idPiece, quartiers.get(0),
					getRandomQuartier(quartierDao),
					getRandomQuartier(quartierDao),
					getRandomQuartier(quartierDao));
		for (int i = 1; i < taillePlateau - 1; i++)
			// Ligne Ouest
			this.cases[i][0] = new Piece(++idPiece,
					getRandomQuartier(quartierDao),
					getRandomQuartier(quartierDao),
					getRandomQuartier(quartierDao), quartiers.get(0));
		for (int i = 1; i < taillePlateau - 1; i++)
			// Ligne sud
			this.cases[taillePlateau - 1][i] = new Piece(++idPiece,
					getRandomQuartier(quartierDao),
					getRandomQuartier(quartierDao), quartiers.get(0),
					getRandomQuartier(quartierDao));
		for (int i = 1; i < taillePlateau - 1; i++)
			// Ligne Est
			this.cases[i][taillePlateau - 1] = new Piece(++idPiece,
					getRandomQuartier(quartierDao), quartiers.get(0),
					getRandomQuartier(quartierDao),
					getRandomQuartier(quartierDao));

		/* ----- 2.3 On crée les pièces restantes avec aucune face noire */
		for (int i = 1; i < taillePlateau - 1; i++) {
			for (int j = 1; j < taillePlateau - 1; j++) {
				this.cases[i][j] = new Piece(++idPiece,
						getRandomQuartier(quartierDao),
						getRandomQuartier(quartierDao),
						getRandomQuartier(quartierDao),
						getRandomQuartier(quartierDao));
			}
		}

		/* ----- 3.1 On s'occupe des liaisons EST-OUEST entre pièces ----- */
		for (int i = 0; i < taillePlateau; i++) {
			for (int j = 1; j < taillePlateau; j++) {
				((Piece) this.cases[i][j])
						.setQuartierOuest(((Piece) this.cases[i][j - 1])
								.getQuartierEst());
			}
		}

		/* ----- 3.2 On s'occupe des liasons NORD-SUD entre pièces ----- */
		for (int i = 1; i < taillePlateau; i++) {
			for (int j = 0; j < taillePlateau; j++) {
				((Piece) this.cases[i][j])
						.setQuartierNord(((Piece) this.cases[i - 1][j])
								.getQuartierSud());
			}
		}

		/* ----- 3.3 On notifie à la vue nos changements ----- */
		miseAJour();
	}

	/* ----- Cette méthode s'occupe de mélanger notre matrice ----- */
	public void randomShuffleArray() {
		int indice = 0;
		casesShuffle = new Case[4][4];

		/* 1. On crée un array list qui contiendra toutes les pièces */
		ArrayList<Case> arrayList = new ArrayList<Case>();
		for (int i = 0; i < cases.length; i++)
			for (int j = 0; j < cases[i].length; j++)
				arrayList.add(cases[i][j]);

		/* 2. On utilise la méthode shuffle qui mélangera l'ArrayList */
		Collections.shuffle(arrayList);

		/*
		 * 3. On complète le tableau casesShuffle qui contient les cases
		 * mélangées
		 */
		for (int i = 0; i < casesShuffle.length; i++) {
			for (int j = 0; j < casesShuffle[i].length; j++) {
				casesShuffle[i][j] = arrayList.get(indice);
				indice++;
			}
		}
	}

	/* ---- Cette méthode pemet d'initialiser notre terrain qui est vide ----- */
	public void initialiserTerrain() {
		plateau = new Case[4][4];
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[i].length; j++) {
				plateau[i][j] = new Vide();
			}
		}
	}

	private Quartier getRandomQuartier(QuartierDao quartierDao) {
		List<Quartier> quartiers = quartierDao.findQuartiers();
		Random random = new Random();
		int nbreAleatoire = 1 + random.nextInt(quartiers.size() - 1);
		return quartiers.get(nbreAleatoire);
	}

	// public void inverser(Piece a, Piece b) {
	//
	// Piece tmpA = new Piece(a);
	// Piece tmpB = new Piece(b);
	// pieces[a.getPosY()][a.getPosX()] = tmpB.inverse(a);
	// pieces[b.getPosY()][b.getPosX()] = tmpA.inverse(b);
	// this.miseAJour();
	// }

	/* ---- Méthode vérifiant si le jeu est terminé ---- */
	public boolean isGameEnded() {
		boolean endGame = true;
		for (int i = 0; i < casesShuffle.length; i++) {
			for (int j = 0; j < casesShuffle[i].length; j++) {
				if (!((Piece) plateau[i][j]).equals((Piece) cases[i][j])) {
					endGame = false;
					break;
				}
			}
			if (!endGame)
				break;
		}
		return endGame;
	}

	/* ----- ACCESSEURS ----- */
	public boolean getPartieEnCours() {
		return partieEnCours;
	}

	public boolean getPartieEnPause() {
		return partieEnPause;
	}

	public void setPartieEnPause(boolean partieEnPause) {
		this.partieEnPause = partieEnPause;
	}

	public void setPartieEnCours(boolean partieEnCours) {
		this.partieEnCours = partieEnCours;
	}

	public Case[][] getCasesShuffle() {
		return casesShuffle;
	}

	public Case[][] getCases() {
		return cases;
	}

	public Case[][] getPlateau() {
		return plateau;
	}

	public void setCases(Case[][] cases) {
		this.cases = cases;
	}

	public void setCasesShuffle(Case[][] casesShuffle) {
		this.casesShuffle = casesShuffle;
	}

	public void setPlateau(Case[][] plateau) {
		this.plateau = plateau;
	}

	public boolean getAUtiliseAide() {
		return aUtiliseAide;
	}

	public void setAUtiliseAide(boolean aUtiliseAide) {
		this.aUtiliseAide = aUtiliseAide;
	}

	public boolean getGiveup() {
		return giveup;
	}

	public void setGiveup(boolean giveup) {
		this.giveup = giveup;
	}
}
