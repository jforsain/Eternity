package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/* ---- Cette classe s'occupe de charger une partie  ------ */
public class CasesQuartierDao {
	
	private final static String RESOURCES_PATH = "src/main/resources/fichiers_jeu/";
    private String pieces_file_name;
    private final static char SEPARATOR = ';';
    
    
    public CasesQuartierDao(String pNameFile)
    {
    	this.pieces_file_name = pNameFile;
    }
    
    /* ---- Cette méthode charge le fichier pieces-01.csv ----- */
    public List<Case> findCases()
    {
    	File file = new File(RESOURCES_PATH + pieces_file_name);
        FileReader fr;
        List<String[]> data = new ArrayList<String[]>();
        List<Case> cases = new ArrayList<Case>();
        
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
	        	Case p_case;
	        	
	        	if(oneData[0].charAt(0) == 'P')
	        	{
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
	        	
	        	
		        	/* ---- Instanciation de piece ----- */
		        	p_case = new Piece(idPiece, quartierNord, quartierEst, quartierSud, quartierOuest);
	        	}
	        	else
	        	{
	        		p_case = new Vide();
	        	}
	        	cases.add(p_case);
	        }
	        
	        /* ----- 3. Retour de la liste 50% complète ----- */
	        return cases;
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return cases;
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
