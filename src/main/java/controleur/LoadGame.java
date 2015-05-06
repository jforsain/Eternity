package controleur;

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

public class LoadGame extends JFrame implements ListSelectionListener{
	
	private JLabel jLabel = new JLabel("Select a snapsot:");
	private JButton ok;
	private JButton cancel;
	private JList jList;
	String names[] = {"Item", "Item", "Item", "Item", "Item", "Item", "Item", "Item", "Item", "Item"};
	
	public LoadGame(JButton pOk, JButton pCancel)
	{
		JPanel panel = new JPanel();
		JPanel panelBoutons = new JPanel();
		
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		ok = new JButton("OK");
		cancel = new JButton("Cancel");
		
		ok.setPreferredSize(new Dimension(100, 30));
		cancel.setPreferredSize(new Dimension(100, 30));
		
		panelBoutons.add(ok);
		panelBoutons.add(cancel);
		
		jList = new JList(names);
		
		jList.setFixedCellWidth(200);
		jList.setFixedCellHeight(20);
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
		
		setTitle("Load Game");
		setSize(400, 300);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		add(panel);
		setVisible(true);
	}

	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
