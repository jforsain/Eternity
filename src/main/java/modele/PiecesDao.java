package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/* ---- Cette classe s'occupe de charger une partie déjà comencé ------ */
public class PiecesDao {
	
	private final static String RESOURCES_PATH = "src/main/resources/";
    private final static String PIECES_FILE_NAME = "pieces-01.csv";
    private final static char SEPARATOR = ';';
    
    /* ---- Cette méthode charge le fichier pieces-01.csv ----- */
    public List<Piece> findPieces()
    {
    	File file = new File(RESOURCES_PATH + PIECES_FILE_NAME);
        FileReader fr;
        List<String[] > data = new ArrayList<String[] >();
        List<Piece> pieces= new ArrayList<Piece>();
        
        String[] nextLine = null;

		try {
			fr = new FileReader(file);
	        CSVReader csvReader = new CSVReader(fr, SEPARATOR);
	        
	        /* ----- 1. Lecture des données ----- */
	        while ((nextLine = csvReader.readNext()) != null) {
	            int size = nextLine.length;

	            // ligne vide
	            if (size == 0) {
	                continue;
	            }
	            String debut = nextLine[0].trim();
	            if (debut.length() == 0 && size == 1) {
	                continue;
	            }

	            // ligne de commentaire
	            if (debut.startsWith("#")) {
	                continue;
	            }
	            data.add(nextLine);
	        }
	        
	        /*----- 2. Transformation des données en objet ----- */
	        for(String[] oneData : data)
	        {	      
	        	Piece piece;
	        	
	        	Quartier quartierNord;
	        	Quartier quartierEst;
	        	Quartier quartierSud;
	        	Quartier quartierOuest;
	        	
	        	String idPieceStr = oneData[1];
	        	String idQuartierNordStr = oneData[2];
	        	String idQuartierEstStr = oneData[3];
	        	String idQuartierSudStr = oneData[4];
	        	String idQuartierOuestStr = oneData[5];
	        	
	        	int idPiece = Integer.parseInt(idPieceStr);
	        	int idQuartierNord = Integer.parseInt(idQuartierNordStr);
	        	int idQuartierEst = Integer.parseInt(idQuartierEstStr);
	        	int idQuartierSud = Integer.parseInt(idQuartierSudStr);
	        	int idQuartierOuest = Integer.parseInt(idQuartierOuestStr);
	        	
	        	/* ----- Utilisation de la classe quartierDAO pour get les quartiers grâce à leurs id ------ */
	        	quartierNord = getQuartier(idQuartierNord);
	        	quartierEst = getQuartier(idQuartierEst);
	        	quartierSud = getQuartier(idQuartierSud);
	        	quartierOuest = getQuartier(idQuartierOuest);
	        	
	        	
	        	/* ---- instanciation de piece ----- */
	        	piece = new Piece(idPiece, quartierNord, quartierEst, quartierSud, quartierOuest);
	        }
	        
	        /* ----- 3. Retour de le liste ----- */
	        return pieces;
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return pieces;
    }
    
    public Quartier getQuartier(int pIndice)
    {
    	Quartier quartier = null;
    	QuartierDao quartierDao = new QuartierDao();
    	List<Quartier> quartiers = quartierDao.findQuartiers();
    	
    	boolean trouve = false;
    	int i = 0;
    	while(!trouve && i < quartiers.size())
    	{
    		if(quartiers.get(i).getIdQuartier() == pIndice)
    		{
    			quartier = quartiers.get(i);
    			trouve = true;
    		}
    		i++;
    	}
    	return quartier;
    }
}
