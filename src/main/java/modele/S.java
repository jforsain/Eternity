package modele;

public enum S {
	
	triangle("triangle"),
	couronne("couronne"),
	ligne("ligne"),
	soleil("soleil"),
	lignes_verticales("lignes verticales"),
	lignes_horizontales("lignes horizontales"),
	carre("carre");
	
	private final String symbole;
	S(String symbole)
	{
		this.symbole = symbole;
	}
	
	public static String get(String name)
	{
		for(S a : S.values())
		{
			if(a.toString().equals(name))
				return a.symbole;
		}
		return null;
	}
}
