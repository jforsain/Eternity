import vue.PlateauDeJeuVue;
import modele.PlateauDeJeuModele;
import controleur.EternityGUI;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PlateauDeJeuModele plateauDeJeuModele = new PlateauDeJeuModele();
		PlateauDeJeuVue plateauDeJeuVue = new PlateauDeJeuVue(plateauDeJeuModele);
		EternityGUI eternityGUI = new EternityGUI(plateauDeJeuModele, plateauDeJeuVue);
	}

}
