package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Titre extends JPanel{
	
	private BufferedImage bufferedImage;
	
	public Titre()
	{
		try
		{
			this.bufferedImage = ImageIO.read(new File("src/main/resources/titre.JPG"));
		} catch(IOException exception)
		{
			exception.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
        graphics.drawImage(bufferedImage, 0, 0, null);
	}
	
	public int getWidth()
	{
		return 800;
	}
	
	
	public int getHeight()
	{
		return 500;
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(getWidth(), getHeight());
	}
}
