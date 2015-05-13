package vue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Gameover extends JPanel{
	
	private BufferedImage bufferedImage;
	
	public Gameover()
	{
		try
		{
			this.bufferedImage = ImageIO.read(new File("src/main/resources/game-over.jpg"));
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
