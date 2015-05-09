package modele;

public class Piece extends Case{
	
	private int idPiece;
	private Quartier quartierNord;
	private Quartier quartierEst;
	private Quartier quartierSud;
	private Quartier quartierOuest;
	private char orientation = 'N';
	
	public Piece(int pIdPiece, Quartier pQuartierNord, Quartier pQuartierEst, Quartier pQuartierSud, Quartier pQuartierOuest) {
		this.idPiece = pIdPiece;
		this.quartierNord = pQuartierNord;
		this.quartierEst = pQuartierEst;
		this.quartierSud = pQuartierSud;
		this.quartierOuest = pQuartierOuest;	
	}
	
	public Piece(Piece a) {
		this.setPosX( a.getPosX() );
		this.setPosY( a.getPosY() );
		this.orientation = a.orientation;
		this.quartierNord = a.getQuartierNord();
		this.quartierSud = a.getQuartierSud();
		this.quartierEst = a.getQuartierEst();
		this.quartierOuest = a.getQuartierOuest();
		this.idPiece = a.idPiece;
	}
	
	/* ----- ACCESSEURS ------ */

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
	
	public void tourner() {
	
			Quartier tmp =  quartierNord;
			
			quartierNord = quartierOuest;
			quartierOuest = quartierSud;
			quartierSud = quartierEst;
			quartierEst = tmp;
			
			switch(this.orientation){
			case 'N':
				this.setOrientation('E');
				break;
			case 'S':
				this.setOrientation('O');
				break;
			case 'E':
				this.setOrientation('S');
				break;
			case 'O':
				this.setOrientation('N');
				break;
			}
	}
	
	public Piece inverse(Piece a)
	{
		this.setPosX( a.getPosX() );
		this.setPosY( a.getPosY() );
		return this;
	}
	
	public boolean equals(Piece pPiece)
	{
		return (this.orientation == pPiece.getOrientation()) && (this.idPiece == pPiece.getIdPiece());
	}
}
