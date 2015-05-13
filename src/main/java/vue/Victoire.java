package vue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Victoire extends JPanel{

private BufferedImage bufferedImage;
	
	public Victoire()
	{
		try
		{
			this.bufferedImage = ImageIO.read(new File("src/main/resources/victoire.jpg"));
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
}