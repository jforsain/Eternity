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

import vue.AboutPanel;
import vue.ChoisirPiecesVue;
import vue.Gameover;
import vue.Pause;
import vue.PlateauDeJeuVue;
import vue.Titre;
import vue.Victoire;
import modele.Piece;
import modele.PlateauDeJeuModele;
import modele.SoundClip;
import modele.TextFileWriter;

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
	private LoadGame gamePanel;
	private Savegame sauvegarderJeuPanel;
	private AboutPanel aPropos;
	
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
	private JMenuItem closeGame = new JMenuItem("Fermer");
	
	private JMenuItem help = new JMenuItem("Aide...");
	private JMenuItem aboutItem = new JMenuItem("A propos...");
	
	private JButton clearAll = new JButton("Clear");
	private JButton solution = new JButton("Solution");
	private JButton ouiRejouer = new JButton();
	private JButton nonRejouer = new JButton();
	private JButton okGame = new JButton("OK");
	private JButton annulerGame = new JButton("Annuler");
	private JButton ouiTextfieldSaveGame = new JButton("OK");
	private JButton annulerTextfieldSaveGame= new JButton("Annuler");
	private JButton okApropos = new JButton("OK");
	
	
	private ImageIcon img = new ImageIcon("src/main/resources/icon_eternity.jpg");
	
	private Piece iscliqued;
	
	public EternityGUI(PlateauDeJeuModele pPlateauDeJeuModele, PlateauDeJeuVue pPlateauDeJeuVue, ChoisirPiecesVue pChoisirPiecesVue)
	{	
		/* Instanciation + affectation */
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
		this.gamePanel = new LoadGame(okGame, annulerGame, "");
		this.sauvegarderJeuPanel = new Savegame(ouiTextfieldSaveGame, annulerTextfieldSaveGame);
		this.aPropos = new AboutPanel(this.okApropos);
		
		this.jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, plateauDeJeuVue, choisirPiecesVue);
		this.jSplitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jSplitPane, info);
		
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
		this.menu.add(closeGame);
		
		/* Paramétrage menu2 */
		this.menu2.add(help);
		this.menu2.add(aboutItem);
		
		
		/*  ---- Ajout des listeners ----- */
		
		/* ACTION LISTENERS */
		nouveau.addActionListener(this);
		closeGame.addActionListener(this);
		pause.addActionListener(this);
		continueGame.addActionListener(this);
		stopGame.addActionListener(this);
		loadGame.addActionListener(this);
		saveGame.addActionListener(this);
		ouiRejouer.addActionListener(this);
		nonRejouer.addActionListener(this);
		clearAll.addActionListener(this);
		solution.addActionListener(this);
		okGame.addActionListener(this);
		annulerGame.addActionListener(this);
		ouiTextfieldSaveGame.addActionListener(this);
		annulerTextfieldSaveGame.addActionListener(this);
		aboutItem.addActionListener(this);
		okApropos.addActionListener(this);
		
		
		/* KEY LISTENERS */
		this.setFocusable(true); // Ajout du focus pour intercepter les saisies claviers
	    this.addKeyListener(this);	    
	    
	    
	    /* WINDOW LISTENER */
	    this.gamePanel.addWindowListener(this);
	    this.sauvegarderJeuPanel.addWindowListener(this);
	    
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
		if(e.getSource() == nouveau || e.getSource() == this.replay.getOui()) // Clique sur 'Nouveau' ou 'Oui' quand on propose au joueur de rejouer 
		{
			if(this.plateauDeJeuModele.getPartieEnCours()) // Si la partie est déjà en cours (en pause)
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
			if(this.plateauDeJeuModele.getPartieEnCours())
			{
				this.soundClip.getClip().stop();
				this.info.getTimerLabel().timerStop();
			}
			
			if(e.getSource() == this.loadGame)
				this.gamePanel.setTitle("Charger partie");
			else
				this.gamePanel.setTitle("Sauvegarder partie");
			this.setEnabled(false);
			
			this.gamePanel.setVisible(true);
		}
		
		if(e.getSource() == this.okGame) // Clique sur OK selon que l'on ait cliqué sur 'Charger partie' ou 'Sauvegarder partie'
		{
			if(this.gamePanel.getTitle().equals("Sauvegarder partie") && this.gamePanel.getjList().getSelectedValue() != null)
			{
				this.gamePanel.setEnabled(false);
				this.sauvegarderJeuPanel.setVisible(true);
				this.sauvegarderJeuPanel.setTextField(this.gamePanel.getjList().getSelectedValue().toString());;
			}
			if(this.gamePanel.getTitle().equals("Charger partie") && this.gamePanel.getjList().getSelectedValue() != null)
			{
				if(this.plateauDeJeuModele.chargerPartie(this.gamePanel.getjList().getSelectedValue().toString()))
				{
					this.sauvegarderJeuPanel.setVisible(false);
					this.gamePanel.setEnabled(true);
					this.gamePanel.setVisible(false);
					this.setEnabled(true);
					if(!this.plateauDeJeuModele.getPartieEnCours()) // Si c'est l'écran de titre
					{
						this.plateauDeJeuModele.setPartieEnCours(true);
						this.getContentPane().remove(this.titre);
					    this.getContentPane().add(this.jSplitPane2, BorderLayout.CENTER);
					    
					    this.saveGame.setEnabled(true);
						this.pause.setEnabled(true);
						this.stopGame.setEnabled(true);
					    
					    this.pack();
					}
					this.soundClip.getClip().loop(Clip.LOOP_CONTINUOUSLY);
				}
			}
		}
		
		if(e.getSource() == this.annulerGame)
		{
			this.gamePanel.setVisible(false);
			this.setEnabled(true);
			if(this.plateauDeJeuModele.getPartieEnCours())
			{
				this.info.getTimerLabel().timerStart();
			}
		}
		
		if(e.getSource() == this.ouiTextfieldSaveGame) // Clique sur 'OK' quand le texte est rentré
		{
			if(this.plateauDeJeuModele.sauvegarderPartie(this.sauvegarderJeuPanel.getTextField().getText()))
			{	
				TextFileWriter.append(this.sauvegarderJeuPanel.getTextField().getText());
				this.sauvegarderJeuPanel.dispose();
				this.gamePanel.setEnabled(true);
				this.gamePanel.dispose();
				this.setEnabled(true);
				this.info.getTimerLabel().timerStart();
				this.soundClip.getClip().loop(Clip.LOOP_CONTINUOUSLY);
				this.gamePanel = new LoadGame(okGame, annulerGame, ""); // On rafraîchit la JList
			}
			else
			{
				this.sauvegarderJeuPanel.dispose();
				this.gamePanel.setEnabled(true);
				this.gamePanel.dispose();
				this.setEnabled(true);
				JOptionPane.showMessageDialog(null, "Le nom de fichier existe déjà.", "Erreur sauvegarde", JOptionPane.INFORMATION_MESSAGE);
				if(this.plateauDeJeuModele.getPartieEnCours() && !this.plateauDeJeuModele.getPartieEnPause())
				{
					this.info.getTimerLabel().timerStart();
					this.soundClip.getClip().loop(Clip.LOOP_CONTINUOUSLY);
				}
			}
		}
		
		if(e.getSource() == this.annulerTextfieldSaveGame)
		{
			this.sauvegarderJeuPanel.dispose();
			this.gamePanel.setEnabled(true);
		}
		
		if(e.getSource() == this.aboutItem) // Clique sur 'A propos'
		{
			this.setEnabled(false);
			this.aPropos.setVisible(true);
			if(plateauDeJeuModele.getPartieEnCours())
				this.info.getTimerLabel().timerStop();
		}
		
		if(e.getSource() == this.okApropos)
		{
			if(this.plateauDeJeuModele.getPartieEnCours())
				this.info.getTimerLabel().timerStart();
			
			this.aPropos.dispose();
			this.setEnabled(true);
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
		
		if((e.getKeyCode() == 113 && this.plateauDeJeuModele.getPartieEnCours()) || e.getKeyCode() == 114) // Touches F2 ou F3
		{
			if(this.plateauDeJeuModele.getPartieEnCours())
			{
				this.soundClip.getClip().stop();
				this.info.getTimerLabel().timerStop();
			}
			
			if(e.getKeyCode() == 114)
				this.gamePanel.setTitle("Charger partie");
			else
				this.gamePanel.setTitle("Sauvegarder partie");
			this.setEnabled(false);
			
			this.gamePanel.setVisible(true);
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
		if(e.getSource() == this.gamePanel)
		{
			this.setEnabled(true);
		}
		if(e.getSource() == this.sauvegarderJeuPanel)
		{
			this.gamePanel.setEnabled(true);
		}
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