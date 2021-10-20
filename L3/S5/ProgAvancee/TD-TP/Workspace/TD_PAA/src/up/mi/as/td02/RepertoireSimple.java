package up.mi.as.td02;
import java.util.ArrayList;

public class RepertoireSimple {
	private ArrayList<String> nomListe;
	private ArrayList<String> prenomListe;
	private ArrayList<String> numListe;
	
	public RepertoireSimple() {
		nomListe = new ArrayList<String>();
		prenomListe = new ArrayList<String>();
		numListe = new ArrayList<String>();
	}
	
	void addPersonne(String prenom, String nom, String num) {
		nomListe.add(nom);
		prenomListe.add(prenom);
		numListe.add(num);
	}
	
	String chercheNumero(String prenom, String nom) {
		if(nomListe.contains(nom) && prenomListe.contains(prenom))
			return(numListe.get(nomListe.indexOf(nom)));
		else
			return("La personne recherchée n'existe pas dans le répertoire.");
	}
}
