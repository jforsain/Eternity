package vue;

import java.awt.Dimension;

import javax.swing.JPanel;

import java.util.Observable;
import java.util.Observer;

public class ChoisirPiecesVue extends JPanel implements Observer{

	
	public void update(String str) {
		// TODO Auto-generated method stub
		
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


	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
