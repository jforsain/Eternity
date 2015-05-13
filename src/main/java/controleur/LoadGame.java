package controleur;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.TextFileWriter;

public class LoadGame extends JFrame {
	
	private JLabel jLabel = new JLabel("Choisissez un fichier:");
	private JButton okBouton;
	private JButton annulerBouton;
	private JList jList;

	private String titreFenetre;
	String names[] = new String[10];
	
	public LoadGame(JButton pOk, JButton pAnnuler, String pTitreFenetre)
	{
		names = TextFileWriter.listeNomFichiers();
		for(int i = 0; i < names.length; i++)
		{
			if(names[i] == null)
			{
				names[i] = "--VIDE--";
			}
		}
		
		this.titreFenetre = pTitreFenetre;
		this.okBouton = pOk;
		this.annulerBouton = pAnnuler;
		
		setTitle(this.titreFenetre);
		this.okBouton.setPreferredSize(new Dimension(100, 30));
		this.annulerBouton.setPreferredSize(new Dimension(100, 30));
		
		JPanel panel = new JPanel();
		JPanel panelBoutons = new JPanel();
		
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		panelBoutons.add(this.okBouton);
		panelBoutons.add(this.annulerBouton);
		
		jList = new JList(names);
		
		jList.setFixedCellWidth(200);
		jList.setFixedCellHeight(20);
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
					.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabel)
					)
					.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jList)
						.addGap(5)
						.addComponent(panelBoutons)
					)
				);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup()
							.addComponent(jLabel))
					)
					.addGap(5)
					.addGroup(layout.createParallelGroup()
						.addComponent(jList)
						.addComponent(panelBoutons)
					)
				);
		
		setResizable(false);
		setSize(400, 300);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		add(panel);
	}

	public String getTitreFenetre() {
		return titreFenetre;
	}

	public void setTitreFenetre(String titreFenetre) {
		this.titreFenetre = titreFenetre;
	}
	
	public JList getjList() {
		return jList;
	}
}
