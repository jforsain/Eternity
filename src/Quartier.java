
public class Quartier {
	
	private char type;
	private int idQuartier;
	private String couleurFond;
	private String couleurForme;
	private String symbole;
	
	public Quartier(char type, int idQuartier, String couleurFond, String couleurForme, String symbole) {
		
		this.type = type;
		this.idQuartier = idQuartier;
		this.couleurFond = couleurFond;
		this.couleurForme = couleurForme;
		this.symbole = symbole;
	}
	
	public String getSymbole() {
		return symbole;
	}

	public char getType() {
		return type;
	}
	
	public int getIdQuartier() {
		return idQuartier;
	}
	
	public String getCouleurFond() {
		return couleurFond;
	}
	
	public String getCouleurForme() {
		return couleurForme;
	}
}
