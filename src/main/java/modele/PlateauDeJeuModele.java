package modele;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * Cette classe représente le MODELE du projet Eternity.
 * Elle représente le coeur algorithmique de l'application (traitements des données, intéractions avec les fichiers csv)
 * @author Jean-Luc FORSAIN
 * */

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

	/**
	 * Sauvegarde la partie en cours.
	 * @return Retourne le résultat de la sauvegarde
	 * */
	public boolean sauvegarderPartie(String nomFichier) {
		
		boolean sauvegardeOK = false;
		String solutionPieces[][];
		
		if(!TextFileWriter.iSNameExistOnFile(nomFichier))
		{
			try {
				
				/* 1. Sauvegarde des positions des pièces S */
				CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/fichiers_jeu/fichiers_csv/"+nomFichier+"_position_solution.csv"), ';');
				solutionPieces = conversionStringPositionPiece(cases);
				for(String elem[]: solutionPieces)
		     		writer.writeNext(elem);
				writer.close();
				
				/* 2. Sauvegarde des quartiers des pièces S */
				CSVWriter writer2 = new CSVWriter(new FileWriter("src/main/resources/fichiers_jeu/fichiers_csv/"+nomFichier+"_quartier_solution.csv"), ';');
				solutionPieces = conversionStringQuartierPiece(cases);
				for(String elem[]: solutionPieces)
		     		writer2.writeNext(elem);
				writer2.close();
				
				/* 3. Sauvegarde des positions des pièces R */
				CSVWriter writer3 = new CSVWriter(new FileWriter("src/main/resources/fichiers_jeu/fichiers_csv/"+nomFichier+"_position_reserve.csv"), ';');
				solutionPieces = conversionStringPositionPiece(casesShuffle);
				for(String elem[]: solutionPieces)
		     		writer3.writeNext(elem);
				writer3.close();
				
				/* 4. Sauvegarde des quartiers des pièces R */
				CSVWriter writer4 = new CSVWriter(new FileWriter("src/main/resources/fichiers_jeu/fichiers_csv/"+nomFichier+"_quartier_reserve.csv"), ';');
				solutionPieces = conversionStringQuartierPiece(casesShuffle);
				for(String elem[]: solutionPieces)
		     		writer4.writeNext(elem);
				writer4.close();
				
				/* 5. Sauvegarde des positions des pièces P */
				CSVWriter writer5 = new CSVWriter(new FileWriter("src/main/resources/fichiers_jeu/fichiers_csv/"+nomFichier+"_position_plateau.csv"), ';');
				solutionPieces = conversionStringPositionPiece(plateau);
				for(String elem[]: solutionPieces)
		     		writer5.writeNext(elem);
				writer5.close();
				
				/* 6. Sauvegarde des quartiers des pièces P */
				CSVWriter writer6 = new CSVWriter(new FileWriter("src/main/resources/fichiers_jeu/fichiers_csv/"+nomFichier+"_quartier_plateau.csv"), ';');
				solutionPieces = conversionStringQuartierPiece(plateau);
				for(String elem[]: solutionPieces)
		     		writer6.writeNext(elem);
				writer6.close();
				sauvegardeOK = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sauvegardeOK;
	}
	
	/** 
	 * */
	private String[][] conversionStringPositionPiece(Case[][] pCases)
	{
		String idPiece;
		String positionX;
		String positionY;
		String orientation;
		int indice = 0;
		
		String[][] casesModele = new String[16][5];
		String[] check = new String[5];
		
		for (int i = 0; i < pCases.length; i++)
		{
			for (int j = 0; j < pCases[i].length; j++) 
			{
				// 1. On récupère la case
				Case c = pCases[i][j];
				
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
	
	/**
	 * */
	private String[][] conversionStringQuartierPiece(Case[][] pCases)
	{
		String idPiece;
		String idQuartierNord;
		String idQuartierEst;
		String idQuartierSud;
		String idQuartierOuest;
		int indice = 0;
		
		String[][] casesModele = new String[16][6];
		String[] check = new String[6];
		
		for (int i = 0; i < pCases.length; i++)
		{
			for (int j = 0; j < pCases[i].length; j++) 
			{
				// 1. On récupère la case
				Case c = pCases[i][j];
				
				// 2. On convertit en String
				if(c instanceof Piece)
				{
					idPiece = Integer.toString(((Piece) c).getIdPiece());
					idQuartierNord = Integer.toString(((Piece) c).getQuartierNord().getIdQuartier());
					idQuartierEst = Integer.toString(((Piece) c).getQuartierEst().getIdQuartier());
					idQuartierSud = Integer.toString(((Piece) c).getQuartierSud().getIdQuartier());
					idQuartierOuest = Integer.toString(((Piece) c).getQuartierOuest().getIdQuartier());
					
					check[0] = "P";
					check[1] = idPiece;
					check[2] = idQuartierNord;
					check[3] = idQuartierEst;
					check[4] = idQuartierSud;
					check[5] = idQuartierOuest;
					
					casesModele[indice][0] = check[0];
					casesModele[indice][1] = check[1];
					casesModele[indice][2] = check[2];
					casesModele[indice][3] = check[3];
					casesModele[indice][4] = check[4];
					casesModele[indice][5] = check[5];
					
				}
				else
				{
					check[0] = "V";
					
					casesModele[indice][0] = check[0];
					casesModele[indice][1] = "";
					casesModele[indice][2] = "";
					casesModele[indice][3] = "";
					casesModele[indice][4] = "";
					casesModele[indice][5] = "";
				}
				indice++;
			}
		}
		return casesModele;
	}
	
	/**
	 * */
	public boolean chargerPartie(String nameFile) {
		
		boolean chargementPartieOK = false;
		if(TextFileWriter.iSNameExistOnFile(nameFile))
		{
			CasesQuartierDao casesQuartierDaoSolution = new CasesQuartierDao(nameFile+"_quartier_solution.csv");
			CasesPositionDao casesPositionDaoSolution= new CasesPositionDao(nameFile+"_position_solution.csv", casesQuartierDaoSolution);
			this.setCases(convertArraytoMatrix(casesPositionDaoSolution.findCases()));
			
			
			CasesQuartierDao casesQuartierDaoReserve = new CasesQuartierDao(nameFile+"_quartier_reserve.csv");
			CasesPositionDao casesPositionDaoReserve= new CasesPositionDao(nameFile+"_position_reserve.csv", casesQuartierDaoReserve);
			this.setCasesShuffle(convertArraytoMatrix(casesPositionDaoReserve.findCases()));
			
			CasesQuartierDao casesQuartierDaoPlateau = new CasesQuartierDao(nameFile+"_quartier_plateau.csv");
			CasesPositionDao casesPositionDaoPlateau= new CasesPositionDao(nameFile+"_position_plateau.csv", casesQuartierDaoPlateau);
			this.setPlateau(convertArraytoMatrix(casesPositionDaoPlateau.findCases()));
			
			chargementPartieOK = true;
			
			miseAJour();
		}
		return chargementPartieOK;
	}
	
	/**
	 * */
	private Case[][] convertArraytoMatrix(List<Case> pListecases)
	{
		Case[][] cases = new Case[4][4];
		int indice = 0;
		for(int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++) 
			{
				cases[i][j] = pListecases.get(indice);
				indice++;
			}
		}
		return cases;
	}
	
	/**
	 * */
	public void miseAJour() {
		setChanged();
		notifyObservers();
	}
	
	/***
	 * /
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

	/**
	 * */
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
	
	/**
	 * */
	/* ---- Cette méthode pemet d'initialiser notre terrain qui est vide ----- */
	public void initialiserTerrain() {
		plateau = new Case[4][4];
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[i].length; j++) {
				plateau[i][j] = new Vide();
			}
		}
	}
	
	/**
	 * */
	private Quartier getRandomQuartier(QuartierDao quartierDao) {
		List<Quartier> quartiers = quartierDao.findQuartiers();
		Random random = new Random();
		int nbreAleatoire = 1 + random.nextInt(quartiers.size() - 1);
		return quartiers.get(nbreAleatoire);
	}
	
	/**
	 * */
	/* ---- Méthode vérifiant si le jeu est terminé ---- */
	public boolean isGameEnded() {
		
		boolean endGame = true;
		
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[i].length; j++) {
				if (plateau[i][j] instanceof Vide || (!((Piece)plateau[i][j]).equals((Piece)cases[i][j]))) {
					endGame = false;
				}
			
			}
			if (!endGame)
				break;
		}
		
		return endGame;
	}
	
	public void inverser(Case a, Case b,String vueA, String vueB) {
		try
		{
			a = (Piece) a;
			b = (Piece) b;
		}
		catch (Exception exeption)
		{
			//pas de Catch
		}
		
		
		if(a instanceof Piece && b instanceof Piece){//entre deux piece
			Piece tmpA = new Piece((Piece)a);
			Piece tmpB = new Piece((Piece)b);
			
			tmpB.setPosX(a.getPosX());
			tmpB.setPosY(a.getPosY());
			if(vueA == "vue.ChoisirPiecesVue")
				casesShuffle[a.getPosY()][a.getPosX()] = tmpB;
			else
				plateau[a.getPosY()][a.getPosX()] = tmpB;
				
			
			tmpA.setPosX(b.getPosX());
			tmpA.setPosY(b.getPosY());
			if(vueB == "vue.ChoisirPiecesVue")
				casesShuffle[b.getPosY()][b.getPosX()] = tmpA;
			else
				plateau[b.getPosY()][b.getPosX()] = tmpA;
			
		}
		else if(a instanceof Piece || b instanceof Piece)//1vide et 1piece
		{
			if(a instanceof Piece){
				Piece tmpA = new Piece((Piece)a);

				tmpA.setPosX(b.getPosX());
				tmpA.setPosY(b.getPosY());
				
				Vide tmpB = new Vide();
				tmpB.setPosX(a.getPosX());
				tmpB.setPosY(a.getPosY());
				
				if(vueA == "vue.ChoisirPiecesVue")
					casesShuffle[a.getPosY()][a.getPosX()] = tmpB;
				else
					plateau[a.getPosY()][a.getPosX()] = tmpB;
				
				if(vueB == "vue.ChoisirPiecesVue")
					casesShuffle[b.getPosY()][b.getPosX()] = tmpA;
				else
					plateau[b.getPosY()][b.getPosX()] = tmpA;
				
			}
			else if (b instanceof Piece){
				Piece tmpB = new Piece((Piece)b);

				tmpB.setPosX(a.getPosX());
				tmpB.setPosY(a.getPosY());
				
				Vide tmpA = new Vide();
				tmpA.setPosX(b.getPosX());
				tmpA.setPosY(b.getPosY());
				
				if(vueB == "vue.ChoisirPiecesVue")
					casesShuffle[b.getPosY()][b.getPosX()] = tmpA;
				else
					plateau[b.getPosY()][b.getPosX()] = tmpA;
				
				if(vueA == "vue.ChoisirPiecesVue")
					casesShuffle[a.getPosY()][a.getPosX()] = tmpB;
				else
					plateau[a.getPosY()][a.getPosX()] = tmpB;
			}
				
		}
		else{//cas 2 cases vide
			//pas de changement
		}
			
		this.miseAJour();
	}
	
	public void clearPlateauDeJeuVue()
	{
		for(int i = 0; i < plateau.length; i++)
		{
			for(int j = 0; j < plateau[i].length; j++)
			{
				if(this.casesShuffle[i][j] instanceof Vide)
				{
					echangeCases(this.casesShuffle[i][j], i, j);
				}
			}
		}
		miseAJour();
	}
	
	public void echangeCases(Case a, int pI, int pJ)
	{
		Case temp;
		int i = 0;
		int j = 0;
		boolean echangeOK = false;
		while(!echangeOK)
		{
			if(this.plateau[i][j] instanceof Piece)
			{
				temp = this.plateau[i][j];
				this.plateau[i][j] = this.casesShuffle[pI][pJ];
				this.casesShuffle[pI][pJ] = temp;
				
				echangeOK = true;
			}
			if(j == 3)
			{
				i++;
				j = 0;
			}
			else
				j++;
		}
	}
	
	
	
	/**
	 * ACCESSEURS
	 * */

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
