import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.JFrame;


public class EternityGUI extends JFrame implements Serializable, MouseListener {
	
	private PlateauDeJeu plateauDeJeu;
	
	public EternityGUI()
	{
		this.plateauDeJeu = new PlateauDeJeu(4, 4);
		initialisationFenetre();
	}
	
	public void initialisationFenetre()
	{
		this.setTitle("Eternity");
	    this.setSize(700, 700);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.add(this.plateauDeJeu, BorderLayout.CENTER);
	    
	    this.pack();
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}