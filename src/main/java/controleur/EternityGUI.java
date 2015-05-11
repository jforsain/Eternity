package controleur;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
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
import vue.Titre;
import vue.Victoire;
import modele.Piece;
import modele.PlateauDeJeuModele;
import modele.SoundClip;

public class EternityGUI extends JFrame implements Serializable, MouseListener, ActionListener, KeyListener, WindowListener {
	
	/* Le controleur connait la VUE et le MODELE */
	private PlateauDeJeuVue plateauDeJeuVue;
	private ChoisirPiecesVue choisirPiecesVue;
	private Informations info;
	private PlateauDeJeuModele plateauDeJeuModele;
	private SoundClip soundClip;
	private Pause panelPause;
	private Pause panelPause2;
	private Gameover gameover;
	private Replay replay;
	private Victoire victoire;
	private Titre titre;
	
	private JSplitPane jSplitPane;
	private JSplitPane jSplitPane2;
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menu = new JMenu("Jeu");
	private JMenu menu2 = new JMenu("Aide");
	
	private JMenuItem nouveau = new JMenuItem("Nouveau");
	private JMenuItem continueGame = new JMenuItem("Continuer          F4");
	private JMenuItem pause = new JMenuItem("Pause              Esc");
	private JMenuItem stopGame = new JMenuItem("Arrêter");
	private JMenuItem loadGame = new JMenuItem("Charger...          F3");
	private JMenuItem saveGame = new JMenuItem("Sauvegarder...   F2");
	private JMenuItem options = new JMenuItem("Options...");
	private JMenuItem closeGame = new JMenuItem("Fermer");
	
	private JMenuItem help = new JMenuItem("Aide...");
	private JMenuItem about = new JMenuItem("A propos...");
	
	private JButton clearAll = new JButton("Clear");
	private JButton solution = new JButton("Solution");
	private JButton ouiRejouer = new JButton();
	private JButton nonRejouer = new JButton();
	private JButton ouiGame = new JButton("OK");
	private JButton annulerGame = new JButton("Annuler");
	private JButton ouiTextfieldSaveGame = new JButton("OK");
	private JButton annulerTextfieldSaveGame= new JButton("Annuler");
	
	
	private ImageIcon img = new ImageIcon("src/main/resources/icon_eternity.jpg");
	
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
		this.replay = new Replay(ouiRejouer, nonRejouer);
		this.victoire = new Victoire();
		this.titre = new Titre();
		
		this.jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, plateauDeJeuVue, choisirPiecesVue);
//		jSplitPane.setEnabled(false);
		this.jSplitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jSplitPane, info);
//		jSplitPane2.setEnabled(false);
		
		initialisationFenetre();
		
		
		// On désactive déjà les items lors du démarrage du jeu
		continueGame.setEnabled(false);
		pause.setEnabled(false);
		stopGame.setEnabled(false);
		saveGame.setEnabled(false);
	}
	
	public void initialisationFenetre()
	{	
		this.setTitle("Eternity");
		
		/* Paramétrage du menu1 */
		this.menu.add(nouveau);
		this.menu.add(continueGame);
		this.menu.add(pause);
		this.menu.add(stopGame);
		this.menu.addSeparator();
		this.menu.add(loadGame);
		this.menu.add(saveGame);
		this.menu.addSeparator();
		this.menu.add(options);
		this.menu.addSeparator();
		this.menu.add(closeGame);
		
		/* Paramétrage menu2 */
		this.menu2.add(help);
		this.menu2.add(about);
		
		
		/*  ---- Ajout des listeners ----- */
		
		/* ACTION LISTENERS */
		nouveau.addActionListener(this);
		closeGame.addActionListener(this);
		pause.addActionListener(this);
		continueGame.addActionListener(this);
		stopGame.addActionListener(this);
		loadGame.addActionListener(this);
		saveGame.addActionListener(this);
		
		this.info.getSolution().addActionListener(this);
		this.info.getClearAll().addActionListener(this);
		this.replay.getOui().addActionListener(this);
		this.replay.getNon().addActionListener(this);
		
		/* KEY LISTENERS */
		this.setFocusable(true); // Ajout du focus pour intercepter les saisies claviers
	    this.addKeyListener(this);	    
	    
	    
	    /* WINDOW LISTENER */
	    
	    
	    this.getContentPane().add(this.titre, BorderLayout.CENTER); // Ajout de l'écran de titre dans le panel
	    
	    this.menuBar.add(menu);
	    this.menuBar.add(menu2);
	    this.setJMenuBar(menuBar);
	    
	    this.setIconImage(img.getImage());
	    
	    
	    this.setResizable(false);
	    this.pack();
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		if(e.getSource() == nouveau || e.getSource() == this.replay.getOui()) // Nouvelle partie  
		{
			if(this.plateauDeJeuModele.getPartieEnCours()) // Si la partie est déjà en cours (en pause
			{
				this.info.getTimerLabel().timerStop();
				this.soundClip.getClip().stop();
				int val = JOptionPane.showConfirmDialog(null, "Une partie est en cours. Êtes-vous sûr ?", "New Game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
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
						this.stopGame.setEnabled(true);
						if(this.plateauDeJeuModele.getPartieEnPause())
						{
							jSplitPane.setRightComponent(choisirPiecesVue);
							jSplitPane.setLeftComponent(plateauDeJeuVue);
							jSplitPane.validate();
							this.continueGame.setEnabled(false);
							this.pause.setEnabled(true);
							this.plateauDeJeuModele.setPartieEnPause(false);
						}
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
			else  // La partie commence
			{

				jSplitPane.setRightComponent(this.choisirPiecesVue);
				jSplitPane.validate();
				jSplitPane2.setRightComponent(this.info);
				jSplitPane2.validate();
			    
				this.plateauDeJeuModele.setPartieEnCours(true);
				this.plateauDeJeuModele.setGiveup(false);
				this.plateauDeJeuModele.nouvellePartie();
				this.plateauDeJeuModele.randomShuffleArray();
				this.plateauDeJeuModele.initialiserTerrain();
				this.soundClip.getClip().setFramePosition(0);
				this.soundClip.getClip().loop(Clip.LOOP_CONTINUOUSLY);
				this.info.getTimerLabel().timerStart();
				
				this.saveGame.setEnabled(true);
				this.pause.setEnabled(true);
				this.stopGame.setEnabled(true);
				
				this.getContentPane().remove(this.titre);
			    this.getContentPane().add(this.jSplitPane2, BorderLayout.CENTER);
			    this.pack();
			}
		}
		
		if(e.getSource() == closeGame) // Clique sur 'Fermer'
		{
			if(this.plateauDeJeuModele.getPartieEnCours())
			{
				this.info.getTimerLabel().timerStop();
				this.soundClip.getClip().stop();
				int val = JOptionPane.showConfirmDialog(null, "Une partie est en cours. Voulez-vous vraiment quitter ?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				switch(val)
				{
					case JOptionPane.YES_OPTION:
						System.exit(0);
						break;
					case JOptionPane.NO_OPTION:
					case JOptionPane.CLOSED_OPTION:
						if(!this.plateauDeJeuModele.getPartieEnPause()) // Si la partie n'est pas en pause QUAND on clique sur 'Fermer'
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
		if(e.getSource() == pause) // Clique sur 'Pause'
		{
			this.soundClip.getClip().stop();
			this.info.getTimerLabel().timerStop();
			continueGame.setEnabled(true);
			pause.setEnabled(false);
			this.plateauDeJeuModele.setPartieEnPause(true);
			majGUI();
		}
		if(e.getSource() == continueGame) // Clique sur 'Continuer'
		{
			this.soundClip.getClip().loop(Clip.LOOP_CONTINUOUSLY);
			this.info.getTimerLabel().timerStart();
			
			this.plateauDeJeuModele.setPartieEnPause(false);
			
			pause.setEnabled(true);
			continueGame.setEnabled(false);
			
			majGUI();
		}
		if(e.getSource() == this.info.getSolution()) // Clique sur 'Solution'
		{
			this.soundClip.getClip().stop();
			this.info.getTimerLabel().timerStop();
			int val = JOptionPane.showConfirmDialog(null, "Souhaitez-vous vraiment abandonner ?", "Solution", JOptionPane.YES_NO_OPTION);
			switch(val)
			{
				case JOptionPane.YES_OPTION:
					this.plateauDeJeuModele.setGiveup(true); 
					this.plateauDeJeuModele.setPlateau(this.plateauDeJeuModele.getCases()); // On fournit la solution
					this.jSplitPane.setRightComponent(this.gameover);
					if(this.plateauDeJeuModele.getPartieEnPause() && this.plateauDeJeuModele.getGiveup()) // Si l'utilisateur à mis le jeu en pause ET qu'il a abandonné
						this.jSplitPane.setLeftComponent(plateauDeJeuVue);
					this.jSplitPane.validate(); // MàJ des 2 plateaux
					this.jSplitPane2.setRightComponent(this.replay);
					this.jSplitPane2.validate();
					this.plateauDeJeuModele.setPartieEnCours(false);
					this.info.getTimerLabel().timerReset();
					
					this.continueGame.setEnabled(false);
					this.pause.setEnabled(false);
					this.stopGame.setEnabled(false);
					break;
				case JOptionPane.NO_OPTION:
				case JOptionPane.CLOSED_OPTION:
					this.soundClip.getClip().loop(Clip.LOOP_CONTINUOUSLY);
					this.info.getTimerLabel().timerStart();
					break;
			}
		}
		
		if(e.getSource() == this.replay.getNon()) // Clique sur 'Non' quand on propose au joueur de rejouer
		{
			this.continueGame.setEnabled(false);
			this.pause.setEnabled(false);
			this.stopGame.setEnabled(false);
			this.saveGame.setEnabled(false);
			
			this.getContentPane().remove(this.jSplitPane2);
		    this.getContentPane().add(this.titre, BorderLayout.CENTER);
		    this.pack();
		}
		
		if(e.getSource() == this.stopGame) // Clique sur 'Arrêter'
		{
			this.soundClip.getClip().stop();
			this.info.getTimerLabel().timerStop();
			int val = JOptionPane.showConfirmDialog(null, "Retourner à l'écran de titre ?", "Stop Game", JOptionPane.YES_NO_OPTION);
			switch(val)
			{
				case JOptionPane.YES_OPTION:
					this.stopGame.setEnabled(false);
					this.pause.setEnabled(false);
					this.continueGame.setEnabled(false);
					this.saveGame.setEnabled(false);
					
					this.getContentPane().remove(this.jSplitPane2);
				    this.getContentPane().add(this.titre, BorderLayout.CENTER);
				    this.pack();
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
		
		if(e.getSource() == this.loadGame || e.getSource() == this.saveGame) // Clique sur 'Charger'
		{
			LoadGame loadGame = new LoadGame(ouiGame, annulerGame);
			this.setEnabled(false);
		}
	}
	

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 115 && this.plateauDeJeuModele.getPartieEnPause()) // Touche F4
		{
			this.soundClip.getClip().loop(Clip.LOOP_CONTINUOUSLY);
			this.info.getTimerLabel().timerStart();
			pause.setEnabled(true);
			continueGame.setEnabled(false);
			this.plateauDeJeuModele.setPartieEnPause(false);
			majGUI();
		}
		
		if(e.getKeyCode() == 27 && !this.plateauDeJeuModele.getPartieEnPause()) // Touche Esc
		{
			this.soundClip.getClip().stop();
			this.info.getTimerLabel().timerStop();
			continueGame.setEnabled(true);
			pause.setEnabled(false);
			this.plateauDeJeuModele.setPartieEnPause(true);
			majGUI();
		}
		
	}

	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
