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
	
	private JButton help = new JButton("Help");
	private JButton clearAll = new JButton("Clear");
	private JButton solution = new JButton("Solution");
	private TimerLabel timerLabel = new TimerLabel();
	
	private JPanel panel = new JPanel();
	
	public Informations()
	{
		solution.setForeground(Color.RED);
		this.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 100));
		panel.setLayout(new GridLayout(3, 1));
		panel.add(help);
		panel.add(clearAll);
		panel.add(solution);
		this.add(BorderLayout.WEST, panel);
		this.add(timerLabel);
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
		
	}
}
