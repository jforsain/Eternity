import vue.ChoisirPiecesVue;
import vue.PlateauDeJeuVue;
import modele.PlateauDeJeuModele;
import controleur.EternityGUI;
import controleur.Informations;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PlateauDeJeuModele plateauDeJeuModele = new PlateauDeJeuModele();
		PlateauDeJeuVue plateauDeJeuVue = new PlateauDeJeuVue(plateauDeJeuModele);
		ChoisirPiecesVue choisirPiecesVue = new ChoisirPiecesVue(plateauDeJeuModele);
		EternityGUI eternityGUI = new EternityGUI(plateauDeJeuModele, plateauDeJeuVue, choisirPiecesVue);
		
		plateauDeJeuVue.addMouseListener(eternityGUI);
		choisirPiecesVue.addMouseListener(eternityGUI);
	}
}
