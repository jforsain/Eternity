package controleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Informations extends JPanel implements ActionListener{
	
	private JButton clearAll;
	private JButton solution;
	private TimerLabel timerLabel = new TimerLabel();
	
	private JPanel panel = new JPanel(); // contient les boutons
	
	public Informations(JButton pClearAll, JButton pSolution)
	{
		this.clearAll = pClearAll;
		this.solution = pSolution;
		this.solution.setForeground(Color.RED);
		this.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 100));
		panel.setLayout(new GridLayout(2, 1));
		panel.add(this.clearAll);
		panel.add(this.solution);
		this.add(BorderLayout.WEST, panel);
		this.add(timerLabel);
		
		clearAll.addActionListener(this);
		solution.addActionListener(this);
	}
	
	public TimerLabel getTimerLabel() {
		return timerLabel;
	}

	public int getWidth()
	{
		return 800;
	}
		
	public int getHeight()
	{
		return 100;
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(getWidth(), getHeight());
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.clearAll)
		{
			
		}
	}

	public JButton getClearAll() {
		return clearAll;
	}

	public JButton getSolution() {
		return solution;
	}
}
