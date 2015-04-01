import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CsvFile01 implements CsvFile{

	public final static char SEPARATOR = ',';
    private File file;
    private List<String> lines;
    private List<String[] > data;
    private String[] titles;
    
    private CsvFile01() {
    }

    public CsvFile01(File file) throws FileNotFoundException, IOException{
        this.file = file;

        // Init
        init();
    }
	
    private void init() throws IOException{
        lines = CsvFileHelper.readFile(file);

        data = new ArrayList<String[] >(lines.size());
        String regex = new Character(SEPARATOR).toString();
        boolean first = true;
        for (String line : lines) {
            // Suppression des espaces de fin de ligne
            line = line.trim();

            // On saute les lignes vides
            if (line.length() == 0) {
                continue;
            }

            // On saute les lignes de commentaire
            if (line.startsWith("#")) {
                continue;
            }

            String[] oneData = line.split(regex);

            if (first) {
                titles = oneData;
                first = false;
            } else {
                data.add(oneData);
            }
        }
    }
    
	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getTitles() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getLines() {
		return lines;
	}
}
