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
import javax.swing.SwingUtilities;

import vue.ChoisirPiecesVue;
import vue.PlateauDeJeuVue;
import modele.Case;
import modele.Piece;
import modele.PlateauDeJeuModele;

public class EternityGUI extends JFrame implements Serializable, MouseListener, ActionListener, KeyListener {
	
	/* Le controleur connait la VUE et le MODELE */
	private PlateauDeJeuVue plateauDeJeuVue;
	private ChoisirPiecesVue choisirPiecesVue;
	private Informations info;
	private PlateauDeJeuModele plateauDeJeuModele;
	
	private JSplitPane jSplitPane;
	private JSplitPane jSplitPane2;
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu menu = new JMenu("Jeu");
	private JMenu menu2 = new JMenu("Aide");
	
	private JMenuItem item = new JMenuItem("Nouveau");
	private JMenuItem item2 = new JMenuItem("Continuer          F4");
	private JMenuItem item3 = new JMenuItem("Pause");
	private JMenuItem item4 = new JMenuItem("Charger...          F3");
	private JMenuItem item5 = new JMenuItem("Sauvegarder...   F2");
	private JMenuItem item6 = new JMenuItem("Options...");
	private JMenuItem item7 = new JMenuItem("Fermer");
	
	private JMenuItem item8 = new JMenuItem("Aide...");
	private JMenuItem item9 = new JMenuItem("A propos...");
	
	private Case iscliqued;
	private String vueIsCliqued;
	
	public EternityGUI(PlateauDeJeuModele pPlateauDeJeuModele, PlateauDeJeuVue pPlateauDeJeuVue, ChoisirPiecesVue pChoisirPiecesVue, Informations pInfos)
	{
		this.choisirPiecesVue = pChoisirPiecesVue;
		this.plateauDeJeuModele = pPlateauDeJeuModele;
		this.plateauDeJeuVue = pPlateauDeJeuVue;
		this.info = pInfos;
		
		jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, plateauDeJeuVue, choisirPiecesVue);
		jSplitPane.setEnabled(false);
		jSplitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jSplitPane, info);
		jSplitPane2.setEnabled(false);
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
		
		
		/* Ajout des listeners */
		item.addActionListener(this);
		item7.addActionListener(this);
		
		this.setTitle("Eternity");
	    this.setSize(700, 700);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.getContentPane().add(this.jSplitPane2, BorderLayout.CENTER);
	    
	    this.setResizable(false);
	    this.menuBar.add(menu);
	    this.menuBar.add(menu2);
	    this.setJMenuBar(menuBar);
	    this.pack();
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);

	}
	
	/* S�lection de la case par le joueur */

	
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == item)
		{
			this.plateauDeJeuModele.nouvellePartie();
			this.plateauDeJeuModele.randomShuffleArray();
			this.plateauDeJeuModele.initialiserTerrain();
		}
		if(e.getSource() == item7)
			System.exit(0);
	}

	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent e) {
		// 100 pour taille case
				int horizontal = e.getX()/100; // n = Un/r - Uo  
				int vertical = e.getY()/100; // n = Un/r - Uo  
				Case tmp = null;
				String vue = e.getSource().getClass().getName();
				if (SwingUtilities.isRightMouseButton(e) || e.isControlDown())      
				{
					Piece tmpPiece = null;
					if( vue == "vue.PlateauDeJeuVue")
						tmpPiece = (Piece) this.plateauDeJeuModele.getPlateau()[vertical][horizontal];
					else if( vue == "vue.ChoisirPiecesVue")
						tmpPiece = (Piece) this.plateauDeJeuModele.getCasesShuffle()[vertical][horizontal];
					
					tmpPiece.tourner();
					
					this.plateauDeJeuModele.miseAJour();
				}
				else
				{					
					if(iscliqued == null)
					{
						try
						{
							
							if( vue == "vue.PlateauDeJeuVue"){
								this.iscliqued = this.plateauDeJeuModele.getPlateau()[vertical][horizontal];
								this.vueIsCliqued = "vue.PlateauDeJeuVue";
							}
							else if( vue == "vue.ChoisirPiecesVue"){
								this.iscliqued = this.plateauDeJeuModele.getCasesShuffle()[vertical][horizontal];
								this.vueIsCliqued = "vue.ChoisirPiecesVue" ;
							}
							
							System.out.println("register");
						}
						catch (Exception exeption)
						{
							System.out.println("erreur register");
							this.iscliqued = null;
						}
							
						
					}
					else
					{
						
						try
						{								
								if( vue == "vue.PlateauDeJeuVue"){
									tmp = this.plateauDeJeuModele.getPlateau()[vertical][horizontal];
								}
								else if( vue == "vue.ChoisirPiecesVue"){
									tmp = this.plateauDeJeuModele.getCasesShuffle()[vertical][horizontal];
								}
								
								this.plateauDeJeuModele.inverser(this.iscliqued, tmp, this.vueIsCliqued , vue);

							this.iscliqued = null;
							System.out.println("chargement");
						}
						catch (Exception exeption)
						{
							System.out.println("erreur changement");
							this.iscliqued = null;
						}

					}
						
				}
		// TODO Auto-generated method stub
		//System.out.println(e.getSource());
		//System.out.println(e.getX());
	}
}
