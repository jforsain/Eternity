package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class CasesPositionDao {
	
	private final static String RESOURCES_PATH = "src/main/resources/fichiers_jeu/fichiers_csv/";
    private String pieces_file_name;
    private final static char SEPARATOR = ';';
    private CasesQuartierDao casesQuartierDao;
    
    
    public CasesPositionDao(String pNameFile, CasesQuartierDao pCasesQuartierDao)
    {
    	this.pieces_file_name = pNameFile;
    	casesQuartierDao = pCasesQuartierDao;
    }
    
    public List<Case> findCases()
    {
    	File file = new File(RESOURCES_PATH + pieces_file_name);
        FileReader fr;
        List<String[]> data = new ArrayList<String[]>();
        List<Case> cases = this.casesQuartierDao.findCases();
        
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
	        
	        /*----- 2. Transformation des données en type adéquat ----- */
	        for(String[] oneData : data)
	        {	      
	        	int posX;
	        	int posY;
	        	char orientation = ' ';
	        	int i = 0;
	        	
	        	String posxStr = oneData[2];
	        	String posyStr = oneData[3];
	        	String orientationStr = oneData[4];
	        	
	        	posX = Integer.parseInt(posxStr);
	        	posY = Integer.parseInt(posyStr);
	        	if(oneData[0].charAt(0) == 'P')
	        		orientation = orientationStr.charAt(0);
	        	
	        	/* ------ 3. Recherche de l'idPiece à mettre à jour --------*/
	        	
    			cases.get(i).setPosX(posX);
    			cases.get(i).setPosY(posY);
    			if(cases.get(i) instanceof Piece)
    				((Piece)cases.get(i)).setOrientation(orientation);
	        		
	        	i++;
	        }
	        
	        /* ----- 4. Retour de la liste 100% complète ----- */
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
}