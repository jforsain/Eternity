package vue;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutPanel extends JFrame{
	
	private JButton bouton;
	private JPanel jPanel = new JPanel();
	private JLabel jLabel = new JLabel("<html>Eternity<br/><br/>Version 1.0<br/><br/>Copyright© 2014-2015 ESIEA 3A-UFA<br/><br/>Web: <a href=\"esiea.fr\">www.esiea.fr</a></html>");
	private JLabel image = new JLabel(new ImageIcon("src/main/resources/images/icone_piece.JPG"));
	
	public AboutPanel(JButton pBouton)
	{
		this.bouton = pBouton;
		this.bouton.setPreferredSize(new Dimension(100, 30));
		this.jPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		this.jPanel.add(image);
		this.jPanel.add(jLabel);
		this.jPanel.add(bouton);
		this.add(jPanel);
		
		this.setTitle("A propos Eternity");
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
	}
}
