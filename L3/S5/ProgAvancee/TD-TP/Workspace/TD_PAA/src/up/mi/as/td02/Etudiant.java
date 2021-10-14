package up.mi.as.td02;
import java.util.HashMap;

public class Etudiant {
	private String nom;
	private HashMap<UE, Float> moys;

	public Etudiant(String nom) {
		this.nom = nom;
		this.moys = new HashMap<UE, Float>();
	}
	
	public void ajoutMoys(UE ue, float cc, float examFinal) {
		moys.put(ue, Math.max(examFinal, (cc+examFinal)/2));
	}
}
