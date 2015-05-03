package controleur;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Informations extends JPanel{
	
	private JButton help = new JButton("Aide");
	private JButton clearAll = new JButton("Clear");
	private JButton solution = new JButton("Solution");
	
	public Informations()
	{
		
		
		this.add(help);
		this.add(clearAll);
		this.add(solution);
	}
	
	public int getWidth()
	{
		return 800;
	}
		
	public int getHeight()
	{
		return 50;
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(getWidth(), getHeight());
	}
}
