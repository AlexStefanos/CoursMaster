package up.mi.as.td03;

public class Divertissement extends Programme {
	private String presentateur, nom;
	
	public Divertissement(int heureDebut, String presentateur, String nom) {
		super(heureDebut, heureDebut+2);
		this.presentateur = presentateur;
		this.nom = nom;
	}

	public String getNom() {
		return(nom);
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPresentateur() {
		return(presentateur);
	}

	public void setPresentateur(String presentateur) {
		this.presentateur = presentateur;
	}
}
