package modele;

import java.awt.Color;

public enum C {
	noir(Color.BLACK),
	rouge(Color.RED),
	bleu(Color.BLUE),
	vert(Color.GREEN),
	cyan(Color.CYAN),
	gris(Color.GRAY),
	magenta(Color.MAGENTA),
	orange(Color.ORANGE),
	rose(Color.PINK),
	blanc(Color.WHITE),
	jaune(Color.YELLOW);
	
	private final Color color;
	
	C(Color color)
	{
		this.color = color;
	}
	
	public static Color get(String name)
	{
		for(C a : C.values())
		{
			if(a.toString().equals(name))
				return a.color;
		}
		return null;
	}
}
