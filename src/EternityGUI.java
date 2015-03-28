import javax.swing.JFrame;


public class EternityGUI extends JFrame {
	
	private int rectSize = 40;
	
	public EternityGUI()
	{
		initialisationFenetre();
	}
	
	public void initialisationFenetre()
	{
		this.setTitle("Eternity");
	    this.setSize(400, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	    this.setVisible(true);
	}
}
