package controleur;
import java.awt.BorderLayout;
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
import javax.swing.JPanel;

import vue.PlateauDeJeuVue;
import modele.PlateauDeJeuModele;

public class EternityGUI extends JFrame implements Serializable, MouseListener, ActionListener, KeyListener {
	
	/* Le controleur connait la VUE et le MODELE */
	private PlateauDeJeuVue plateauDeJeuVue;
	private PlateauDeJeuModele plateauDeJeuModele;
	
	private JButton pivoterPiece = new JButton("Pivoter");
	private JButton enleverPiece = new JButton("Enlever");
	private JButton ajouterPiece = new JButton("Ajouter");
	
	public EternityGUI(PlateauDeJeuModele pPlateauDeJeuModele, PlateauDeJeuVue pPlateauDeJeuVue)
	{
		this.plateauDeJeuModele = new PlateauDeJeuModele();
		this.plateauDeJeuVue = new PlateauDeJeuVue(plateauDeJeuModele);
		initialisationFenetre();
	}
	
	public void initialisationFenetre()
	{
		JPanel lesBoutons = new JPanel();
		lesBoutons.add(pivoterPiece);
		lesBoutons.add(enleverPiece);
		lesBoutons.add(ajouterPiece);
		
		this.setTitle("Eternity");
	    this.setSize(700, 700);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.add(lesBoutons, BorderLayout.NORTH);
	    this.add(this.plateauDeJeuVue, BorderLayout.CENTER);
	    
	    this.setResizable(false);
	    this.pack();
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}
	
	/* Sélection de la case par le joueur */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int col, row;
		Point point = arg0.getPoint();
		
		row = (int) point.getX() % 100;
		col = (int) point.getY() % 100;
		this.plateauDeJeuVue.setCurrentCol(col);
		this.plateauDeJeuVue.setCurrentRow(row);
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