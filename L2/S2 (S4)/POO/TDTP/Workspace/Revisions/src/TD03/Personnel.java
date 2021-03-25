package TD03;

public class Personnel {
	private String nom, prenom, situation, adresse;
	private int datenaissance, age;
	
	public Personnel(String nom, String prenom, String situation, String adresse, int datenaissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.situation = situation;
		this.adresse = adresse;
		this.datenaissance = datenaissance;
		age = 2021 - datenaissance;
	}
	
	Personnel () {
		nom = "";
		prenom = "";
		situation = "";
		adresse = "";
		datenaissance = 0;
	}
	
	public void affichage() {
		System.out.println(nom + " " + prenom + " est née en " + datenaissance + ", elle est " + situation + " et habite au " + adresse + ". Elle a " + age + " ans.");
	}
}
