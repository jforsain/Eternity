package modele;

import java.awt.Color;
import java.awt.Polygon;

public class Quartier extends Polygon{
	
	private char type;
	private int idQuartier;
	private Color couleurFond;
	private Color couleurForme;
	private Symbole symbole;
	
	public Quartier(char type, int idQuartier, Color couleurFond, Color couleurForme, Symbole symbole) {
		
		this.type = type;
		this.idQuartier = idQuartier;
		this.couleurFond = couleurFond;
		this.couleurForme = couleurForme;
		this.symbole = symbole;
	}
	
	public Symbole getSymbole() {
		return symbole;
	}

	public char getType() {
		return type;
	}
	
	public int getIdQuartier() {
		return idQuartier;
	}
	
	public Color getCouleurFond() {
		return couleurFond;
	}
	
	public Color getCouleurForme() {
		return couleurForme;
	}
}