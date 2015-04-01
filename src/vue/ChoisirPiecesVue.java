package vue;

import java.awt.Dimension;

import javax.swing.JPanel;

import modele.Observer;

public class ChoisirPiecesVue extends JPanel implements Observer{

	@Override
	public void update(String str) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getWidth()
	{
		return 400;
	}
	
	@Override
	public int getHeight()
	{
		return 400;
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(getWidth(), getHeight());
	}
	
	
}
