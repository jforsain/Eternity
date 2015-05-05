package controleur;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Replay extends JPanel{
	
	private JPanel jPanel = new JPanel();
	private JButton oui;
	private JButton non;
	private JLabel jLabel = new JLabel("Souhaitez-vous rejouer ?", JLabel.CENTER);
	
	public Replay(JButton pOui, JButton pNon)
	{
		this.oui = pOui;
		this.non = pNon;		
		this.oui.setText("Oui");
		this.non.setText("Non");
		jLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.NORTH, jLabel);
		jPanel.add(oui);
		jPanel.add(non);
		this.add(BorderLayout.CENTER, jPanel);
		
	}

	public JButton getOui() {
		return oui;
	}

	public JButton getNon() {
		return non;
	}
}
