package controleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import vue.ChoisirPiecesVue;
import vue.PlateauDeJeuVue;
import modele.PlateauDeJeuModele;

public class EternityGUI extends JFrame implements Serializable, MouseListener, ActionListener, KeyListener {
	
	/* Le controleur connait la VUE et le MODELE */
	private PlateauDeJeuVue plateauDeJeuVue;
	private PlateauDeJeuModele plateauDeJeuModele;
	private ChoisirPiecesVue choisirPiecesVue;
	
	private JSplitPane jSplitPane;
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menu = new JMenu("Jeu");
	private JMenu menu2 = new JMenu("Aide");
	
	private JMenuItem item = new JMenuItem("Nouveau");
	private JMenuItem item2 = new JMenuItem("Continuer          F4");
	private JMenuItem item3 = new JMenuItem("Pause");
	private JMenuItem item4 = new JMenuItem("Charger...          F3");
	private JMenuItem item5 = new JMenuItem("Sauvegarder...   F2");
	private JMenuItem item6 = new JMenuItem("Options");
	private JMenuItem item7 = new JMenuItem("Fermer");
	
	private JMenuItem item8 = new JMenuItem("Aide...");
	private JMenuItem item9 = new JMenuItem("A propos...");
	
	public EternityGUI(PlateauDeJeuModele pPlateauDeJeuModele, PlateauDeJeuVue pPlateauDeJeuVue)
	{
		choisirPiecesVue = new ChoisirPiecesVue();
		choisirPiecesVue.setBackground(Color.RED);
		
		this.plateauDeJeuModele = new PlateauDeJeuModele();
		this.plateauDeJeuVue = new PlateauDeJeuVue(plateauDeJeuModele);
		
		jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, plateauDeJeuVue, choisirPiecesVue);
		jSplitPane.setEnabled(false);
		initialisationFenetre();
	}
	
	public void initialisationFenetre()
	{	
		/* Paramétrage du menu1 */
		this.menu.add(item);
		this.menu.add(item2);
		this.menu.add(item3);
		this.menu.addSeparator();
		this.menu.add(item4);
		this.menu.add(item5);
		this.menu.addSeparator();
		this.menu.add(item6);
		this.menu.addSeparator();
		this.menu.add(item7);
		
		/* Paramétrage menu2 */
		this.menu2.add(item8);
		this.menu2.add(item9);
		
		
		this.setTitle("Eternity");
	    this.setSize(700, 700);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.getContentPane().add(this.jSplitPane, BorderLayout.CENTER);
	    
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.menuBar.add(menu);
	    this.menuBar.add(menu2);
	    this.setJMenuBar(menuBar);
	    this.pack();
	    this.setVisible(true);
	}
	
	/* S�lection de la case par le joueur */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int col, row;
		Point point = arg0.getPoint();
		
		row = (int) point.getX() % 100;
		col = (int) point.getY() % 100;
		
		System.out.println(row);
		this.plateauDeJeuVue.setCurrentCol(col);
		this.plateauDeJeuVue.setCurrentRow(row);
		
		this.plateauDeJeuVue.update("");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}