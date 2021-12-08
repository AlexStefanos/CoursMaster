package up.mi.as.td02;
import java.util.ArrayList;

public class Promotion {
	private ArrayList<Etudiant> etudiants;
	private ArrayList<UE> uEs;
	
	public Promotion() {
		this.etudiants = new ArrayList<Etudiant>();
		this.uEs = new ArrayList<UE>();
	}
	
	public void ajoutEtudiant(Etudiant etu) {
		etudiants.add(etu);
	}
	
	public void ajoutUE(UE ue) {
		uEs.add(ue);
	}
	
	public void affichage() {
		System.out.println("");
	}
}