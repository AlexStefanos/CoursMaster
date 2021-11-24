package up.mi.as.td04;

public class Personne {
	private String prenom, nom, tel;
	
	public Personne(String prenom, String nom, String tel) {
		this.prenom = prenom;
		this.nom = nom;
		this.tel = tel;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String toString() {
		return("(" + this.prenom + ", " + this.nom + ", " + this.tel + ")");
	}
}
