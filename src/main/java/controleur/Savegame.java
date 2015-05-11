package controleur;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Savegame extends JFrame {

	private JTextField textField = new JTextField();
	private JLabel jLabel = new JLabel("Entrez un nouveau nom:");
	private JButton okSaveGame;
	private JButton annulerSaveGame;

	public Savegame(JButton pOkSaveGame, JButton pAnnulerSaveGame) {
		setTitle("Nouveau nom");
		this.okSaveGame = pOkSaveGame;
		this.annulerSaveGame = pAnnulerSaveGame;
		this.textField.setDocument(new JTextFieldLimit(12));

		JPanel panel = new JPanel();
		JPanel panelBoutons = new JPanel();

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		okSaveGame.setPreferredSize(new Dimension(100, 30));
		annulerSaveGame.setPreferredSize(new Dimension(100, 30));

		panelBoutons.add(okSaveGame);
		panelBoutons.add(annulerSaveGame);

		layout.setHorizontalGroup(layout
				.createParallelGroup()
				.addGroup(
						layout.createSequentialGroup().addContainerGap()
								.addComponent(jLabel))
				.addGroup(
						layout.createSequentialGroup().addContainerGap()
								.addComponent(textField).addContainerGap())
				.addGroup(layout.createSequentialGroup()

				.addComponent(panelBoutons)));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup().addComponent(jLabel))
				.addGap(5)
				.addGroup(layout.createParallelGroup().addComponent(textField))
				.addGroup(
						layout.createParallelGroup().addComponent(panelBoutons)));

		setAlwaysOnTop(true);
		add(panel);
		this.pack();
		setLocationRelativeTo(null);
	}

	/*----- ACCESSEURS -----*/
	public JButton getOkSaveGame() {
		return okSaveGame;
	}

	public void setOkSaveGame(JButton okSaveGame) {
		this.okSaveGame = okSaveGame;
	}

	public JButton getAnnulerSaveGame() {
		return annulerSaveGame;
	}

	public void setAnnulerSaveGame(JButton annulerSaveGame) {
		this.annulerSaveGame = annulerSaveGame;
	}

	public JTextField getTextField() {
		return textField;
	}
}
