package modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextFileWriter {

	public static void append(String text) {
        BufferedWriter bufWriter = null;
        BufferedReader bufReader = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/main/resources/fichiers_jeu/fichier_principal/liste_noms.txt", true);
            bufReader = new BufferedReader(new FileReader("src/main/resources/fichiers_jeu/fichier_principal/liste_noms.txt"));
            bufWriter = new BufferedWriter(fileWriter);
            //Insérer un saut de ligne
            if(bufReader.readLine() != null)
            	bufWriter.newLine();
            bufWriter.write(text);
            bufWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(TextFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufWriter.close();
                fileWriter.close();
                bufReader.close();
            } catch (IOException ex) {
                Logger.getLogger(TextFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }  
	
	public static boolean iSNameExistOnFile(String filename)
	{
		BufferedReader bufReader= null;
        FileReader fileReader = null;
        boolean trouve = false;
        String line;
        try {
        	fileReader = new FileReader("src/main/resources/fichiers_jeu/fichier_principal/liste_noms.txt");
            bufReader= new BufferedReader(fileReader);
            
            while(!trouve && (line = bufReader.readLine()) != null)
            {
            	if(line.equals(filename))
            		trouve = true;
            }
            bufReader.close();
        } catch (IOException ex) {
            Logger.getLogger(TextFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufReader.close();
                fileReader.close();
            } catch (IOException ex) {
                Logger.getLogger(TextFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return trouve;
	}
	
	public static String[] listeNomFichiers()
	{
		String[] nomFichiers = new String[10];
		BufferedReader bufReader= null;
        FileReader fileReader = null;
        String line;
        int i = 0;
        try {
        	fileReader = new FileReader("src/main/resources/fichiers_jeu/fichier_principal/liste_noms.txt");
            bufReader= new BufferedReader(fileReader);
            
            while((line = bufReader.readLine()) != null)
            {
            	nomFichiers[i] = line;
            	i++;
            }
            bufReader.close();
        } catch (IOException ex) {
            Logger.getLogger(TextFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufReader.close();
                fileReader.close();
            } catch (IOException ex) {
                Logger.getLogger(TextFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return nomFichiers;
	}
}
