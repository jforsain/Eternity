package modele;

public class Case {

	private int X;
	private int Y;

	public Case(int pX, int pY) {
		this.X = pX;
		this.Y = pY;
	}

	public Case() {

	}

	public void inverse(int x, int y) {
		this.setPosX(x);
		this.setPosY(y);
	}

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

}
