package controleur;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import vue.ChoisirPiecesVue;
import vue.Gameover;
import vue.Pause;
import vue.PlateauDeJeuVue;
import modele.Piece;
import modele.PlateauDeJeuModele;
import modele.SoundClip;

public class EternityGUI extends JFrame implements Serializable, MouseListener, ActionListener, KeyListener {
	
	/* Le controleur connait la VUE et le MODELE */
	private PlateauDeJeuVue plateauDeJeuVue;
	private ChoisirPiecesVue choisirPiecesVue;
	private Informations info;
	private PlateauDeJeuModele plateauDeJeuModele;
	private SoundClip soundClip;
	private Pause panelPause;
	private Pause panelPause2;
	private Gameover gameover;
	
	private JSplitPane jSplitPane;
	private JSplitPane jSplitPane2;
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menu = new JMenu("Jeu");
	private JMenu menu2 = new JMenu("Aide");
	
	private JMenuItem item = new JMenuItem("Nouveau");
	private JMenuItem item2 = new JMenuItem("Continuer          F4");
	private JMenuItem item3 = new JMenuItem("Pause              Esc");
	private JMenuItem item10 = new JMenuItem("Arrêter");
	private JMenuItem item4 = new JMenuItem("Charger...          F3");
	private JMenuItem item5 = new JMenuItem("Sauvegarder...   F2");
	private JMenuItem item6 = new JMenuItem("Options...");
	private JMenuItem item7 = new JMenuItem("Fermer");
	
	private JMenuItem item8 = new JMenuItem("Aide...");
	private JMenuItem item9 = new JMenuItem("A propos...");
	
	private JButton clearAll = new JButton("Clear");
	private JButton solution = new JButton("Solution");
	
	private Piece iscliqued;
	
	public EternityGUI(PlateauDeJeuModele pPlateauDeJeuModele, PlateauDeJeuVue pPlateauDeJeuVue, ChoisirPiecesVue pChoisirPiecesVue)
	{
		this.choisirPiecesVue = pChoisirPiecesVue;
		this.plateauDeJeuModele = pPlateauDeJeuModele;
		this.plateauDeJeuVue = pPlateauDeJeuVue;
		this.info = new Informations(clearAll, solution);
		this.soundClip = new SoundClip();
		this.panelPause = new Pause();
		this.panelPause2 = new Pause();
		this.gameover = new Gameover();
		
		jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, plateauDeJeuVue, choisirPiecesVue);
		jSplitPane.setEnabled(false);
		jSplitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jSplitPane, info);
		jSplitPane2.setEnabled(false);
		initialisationFenetre();
		item2.setEnabled(false);
		item3.setEnabled(false);
	}
	
	public void initialisationFenetre()
	{	
		/* Paramétrage du menu1 */
		this.menu.add(item);
		this.menu.add(item2);
		this.menu.add(item3);
		this.menu.add(item10);
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
		
		
		/* Ajout des listeners */
		item.addActionListener(this);
		item7.addActionListener(this);
		item3.addActionListener(this);
		item2.addActionListener(this);
		this.info.getSolution().addActionListener(this);
		this.info.getClearAll().addActionListener(this);
		
		
		this.setTitle("Eternity");
	    this.setSize(700, 700);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.getContentPane().add(this.jSplitPane2, BorderLayout.CENTER);
	    this.setFocusable(true);
	    this.addKeyListener(this);
	    
	    this.setResizable(false);
	    this.menuBar.add(menu);
	    this.menuBar.add(menu2);
	    this.setJMenuBar(menuBar);
	    this.pack();
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}
	
	
	
	private void majGUI()
	{
		if(this.plateauDeJeuModele.getPartieEnPause())
		{
			jSplitPane.setRightComponent(this.panelPause);
			jSplitPane.setLeftComponent(this.panelPause2);
		}
		else
		{
			jSplitPane.setRightComponent(this.choisirPiecesVue);
			jSplitPane.setLeftComponent(this.plateauDeJeuVue);
		}
		jSplitPane.validate();
	}
	
	/* S�lection de la case par le joueur */

	
	public void mouseEntered(MouseEvent arg0) {
		
	}

	
	public void mouseExited(MouseEvent arg0) {
		
	}

	
	public void mousePressed(MouseEvent arg0) {
	}

	
	public void mouseReleased(MouseEvent arg0) {
		
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == item) // Nouvelle partie  
		{
			if(this.plateauDeJeuModele.getPartieEnCours()) // Si la partie est déjà en cours
			{
				this.info.getTimerLabel().timerStop();
				this.soundClip.getClip().stop();
				int val = JOptionPane.showConfirmDialog(null, "Une partie est en cours. Êtes-vous sûr ?", "New Game", JOptionPane.YES_NO_OPTION);
				switch(val)
				{
					case JOptionPane.YES_OPTION:
						this.plateauDeJeuModele.nouvellePartie();
						this.plateauDeJeuModele.randomShuffleArray();
						this.plateauDeJeuModele.initialiserTerrain();
						this.info.getTimerLabel().timerReset();
						this.info.getTimerLabel().timerStart();
						this.soundClip.getClip().setFramePosition(0);
						this.soundClip.getClip().loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case JOptionPane.NO_OPTION:
					case JOptionPane.CLOSED_OPTION:
						if(!this.plateauDeJeuModele.getPartieEnPause())
						{
							this.info.getTimerLabel().timerStart();
							this.soundClip.getClip().start();
						}
						break;
				}
			}
			else
			{
				if(plateauDeJeuModele.getGiveup())
				{
					jSplitPane.setRightComponent(this.choisirPiecesVue);
					jSplitPane.validate();
				}
				this.plateauDeJeuModele.setPartieEnCours(true);
				this.plateauDeJeuModele.setGiveup(false);
				this.soundClip.getClip().setFramePosition(0);
				this.soundClip.getClip().loop(Clip.LOOP_CONTINUOUSLY);
				this.plateauDeJeuModele.nouvellePartie();
				this.plateauDeJeuModele.randomShuffleArray();
				this.plateauDeJeuModele.initialiserTerrain();
				this.info.getTimerLabel().timerStart();
				this.item3.setEnabled(true);
			}
		}
		if(e.getSource() == item7) // Fermer
		{
			if(this.plateauDeJeuModele.getPartieEnCours())
			{
				this.info.getTimerLabel().timerStop();
				this.soundClip.getClip().stop();
				int val = JOptionPane.showConfirmDialog(null, "Une partie est en cours. Voulez-vous vraiment quitter ?", "Quit", JOptionPane.YES_NO_OPTION);
				switch(val)
				{
					case JOptionPane.YES_OPTION:
						System.exit(0);
						break;
					case JOptionPane.NO_OPTION:
					case JOptionPane.CLOSED_OPTION:
						if(!this.plateauDeJeuModele.getPartieEnPause())
						{
							this.info.getTimerLabel().timerStart();
							this.soundClip.getClip().loop(Clip.LOOP_CONTINUOUSLY);
						}
						break;
				}
			}
			else
			{
				System.exit(0);
			}
		}
		if(e.getSource() == item3) // Pause 
		{
			this.soundClip.getClip().stop();
			this.info.getTimerLabel().timerStop();
			item2.setEnabled(true);
			item3.setEnabled(false);
			this.plateauDeJeuModele.setPartieEnPause(true);
			majGUI();
		}
		if(e.getSource() == item2) // Continuer
		{
			this.soundClip.getClip().loop(Clip.LOOP_CONTINUOUSLY);
			this.info.getTimerLabel().timerStart();
			item3.setEnabled(true);
			item2.setEnabled(false);
			this.plateauDeJeuModele.setPartieEnPause(false);
			majGUI();
		}
		if(e.getSource() == this.info.getSolution())
		{
			this.soundClip.getClip().stop();
			this.info.getTimerLabel().timerStop();
			int val = JOptionPane.showConfirmDialog(null, "Souhaitez-vous vraiment abandonner ?", "Solution", JOptionPane.YES_NO_OPTION);
			switch(val)
			{
				case JOptionPane.YES_OPTION:
					this.plateauDeJeuModele.setGiveup(true);
					this.plateauDeJeuModele.setPlateau(this.plateauDeJeuModele.getCases());
					this.jSplitPane.setRightComponent(this.gameover);
					if(this.plateauDeJeuModele.getPartieEnPause() && this.plateauDeJeuModele.getGiveup())
						this.jSplitPane.setLeftComponent(plateauDeJeuVue);
					this.jSplitPane.validate();
					this.plateauDeJeuModele.setPartieEnCours(false);
					this.info.getTimerLabel().timerReset();
					break;
				case JOptionPane.NO_OPTION:
				case JOptionPane.CLOSED_OPTION:
					this.soundClip.getClip().loop(Clip.LOOP_CONTINUOUSLY);
					this.info.getTimerLabel().timerStart();
					break;
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 115 && this.plateauDeJeuModele.getPartieEnPause()) // Touche F4
		{
			this.soundClip.getClip().loop(Clip.LOOP_CONTINUOUSLY);
			this.info.getTimerLabel().timerStart();
			item3.setEnabled(true);
			item2.setEnabled(false);
			this.plateauDeJeuModele.setPartieEnPause(false);
			majGUI();
		}
		
		if(e.getKeyCode() == 27 && !this.plateauDeJeuModele.getPartieEnPause()) // Touche Esc
		{
			this.soundClip.getClip().stop();
			this.info.getTimerLabel().timerStop();
			item2.setEnabled(true);
			item3.setEnabled(false);
			this.plateauDeJeuModele.setPartieEnPause(true);
			majGUI();
		}
		
	}

	
	public void keyReleased(KeyEvent e) {
		
	}

	
	public void keyTyped(KeyEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
//		// 100 pour taille case
//				int horizontal = e.getX()/100; // n = Un/r - Uo  
//				int vertical = e.getY()/100; // n = Un/r - Uo  
//				
//				if (SwingUtilities.isRightMouseButton(e) || e.isControlDown())      
//				{
//					Piece tmp = (Piece) this.plateauDeJeuModele.getCases()[vertical][horizontal];
//					tmp.tourner();
//					this.plateauDeJeuModele.miseAJour();
//				}
//				else
//				{
//
//
//					System.out.println("case : H " + horizontal + " ; V :" + vertical );
//					
//					if(iscliqued == null)
//					{
//						try
//						{
//							this.iscliqued = (Piece) this.plateauDeJeuModele.getCases()[vertical][horizontal];
//							System.out.println("register");
//						}
//						catch (Exception exeption)
//						{
//							System.out.println("erreur register");
//							this.iscliqued = null;
//						}
//							
//						
//					}
//					else
//					{
//						
//						try
//						{
//							Piece tmp = (Piece) this.plateauDeJeuModele.getCases()[vertical][horizontal];
//							if(tmp.getIdPiece() != this.iscliqued.getIdPiece()){
//								System.out.println("changement");
//								this.plateauDeJeuModele.inverser(this.iscliqued, tmp);
//							}
//							this.iscliqued = null;
//						}
//						catch (Exception exeption)
//						{
//							System.out.println("erreur changement");
//							this.iscliqued = null;
//						}
//
//					}
//						
//				}
//		// TODO Auto-generated method stub
//		System.out.println(e.getSource());
//		System.out.println(e.getX());
	}
}
