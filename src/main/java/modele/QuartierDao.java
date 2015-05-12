package modele;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;

public class QuartierDao {
	
	private final static String RESOURCES_PATH = "src/main/resources/fichiers_jeu/fichiers_csv/";
    private final static String ELEVES_FILE_NAME = "faces.csv";
    private final static char SEPARATOR = ';';
        
    /* ---- Cette méthode charge le fichier faces.csv ----- */
    public List<Quartier> findQuartiers()
    {
    
    	File file = new File(RESOURCES_PATH + ELEVES_FILE_NAME);
        FileReader fr;
        List<String[] > data = new ArrayList<String[] >();
        List<Quartier> quartiers = new ArrayList<Quartier>();
        
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
	        	Quartier quartier;
	      
	        	String typeStr = oneData[0];
	        	String idQuartierStr = oneData[1];
	        	String couleurFond = oneData[2];
	        	
	        	char type = typeStr.charAt(0);
	        	int idQuartier = Integer.parseInt(idQuartierStr);
	        	
	        	if(oneData.length > 2)
	        	{
	        		String symbole = oneData[3];
		        	String couleurForme = oneData[4];
		        	quartier = new Quartier(type, idQuartier, couleurFond, couleurForme, symbole);
	        	}
	        	
	        	else
	        	{
	        		quartier = new Quartier(type, idQuartier, couleurFond);
	        	}
	        	
	        	quartiers.add(quartier);
	        }
	        
	        /* ----- 3. Retour de la liste ----- */
	        return quartiers;
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return quartiers;
    }
}
