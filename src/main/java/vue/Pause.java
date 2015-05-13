package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pause extends JPanel{
	
	private JLabel jLabel = new JLabel("PAUSE");
	
	public Pause()
	{
		this.setLayout(new GridBagLayout());
		jLabel.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
		this.add(this.jLabel);
	}
	
	public int getWidth()
	{
		return 400;
	}
	
	public int getHeight()
	{
		return 400;
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(getWidth(), getHeight());
	}
}
