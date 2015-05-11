package modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextFileWriter {

	public static void append(String filename, String text) {
        BufferedWriter bufWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/main/resources/fichiers_jeu/"+filename, true);
            bufWriter = new BufferedWriter(fileWriter);
            //Insérer un saut de ligne
            bufWriter.newLine();
            bufWriter.write(text);
            bufWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(TextFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufWriter.close();
                fileWriter.close();
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
        	fileReader = new FileReader("src/main/resources/fichiers_jeu/"+filename);
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
}
