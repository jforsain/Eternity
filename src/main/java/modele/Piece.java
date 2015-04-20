package modele;

public class Piece {
	
	private int idPiece;
	private Quartier quartierNord;
	private Quartier quartierEst;
	private Quartier quartierSud;
	private Quartier quartierOuest;
	private int X;
	private int Y;
	private char orientation = 'N';
	
	public Piece(int pIdPiece, Quartier pQuartierNord, Quartier pQuartierEst, Quartier pQuartierSud, Quartier pQuartierOuest) {
		this.idPiece = pIdPiece;
		this.quartierNord = pQuartierNord;
		this.quartierEst = pQuartierEst;
		this.quartierSud = pQuartierSud;
		this.quartierOuest = pQuartierOuest;	
	}
	
	/* ----- ACCESSEURS ------ */
	
	public int getPosX() {
		return X;
	}

	public void setPosX(int x) {
		X = x;
	}

	public int getPosY() {
		return Y;
	}

	public void setPosY(int y) {
		Y = y;
	}

	public int getIdPiece() {
		return idPiece;
	}

	public Quartier getQuartierNord() {
		return quartierNord;
	}

	public void setQuartierNord(Quartier quartierNord) {
		this.quartierNord = quartierNord;
	}

	public Quartier getQuartierEst() {
		return quartierEst;
	}

	public void setQuartierEst(Quartier quartierEst) {
		this.quartierEst = quartierEst;
	}

	public Quartier getQuartierSud() {
		return quartierSud;
	}

	public void setQuartierSud(Quartier quartierSud) {
		this.quartierSud = quartierSud;
	}

	public Quartier getQuartierOuest() {
		return quartierOuest;
	}

	public void setQuartierOuest(Quartier quartierOuest) {
		this.quartierOuest = quartierOuest;
	}

	public char getOrientation() {
		return orientation;
	}

	public void setOrientation(char orientation) {
		this.orientation = orientation;
	}
}
