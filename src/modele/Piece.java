package modele;
import java.io.Serializable;


public class Piece extends Case implements Serializable{
	
	public static int id;
	private Quartier quartiers[] = new Quartier[4];
	private char orientation;
	
	public Piece(int posX, int posY, Quartier[] quartiers, char orientation) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.quartiers = quartiers;
		this.orientation = orientation;
	}

	public char getOrientation() {
		return orientation;
	}

	public void setOrientation(char orientation) {
		this.orientation = orientation;
	}

	public static int getId() {
		return id;
	}

	public Quartier[] getQuartiers() {
		return quartiers;
	}
}
