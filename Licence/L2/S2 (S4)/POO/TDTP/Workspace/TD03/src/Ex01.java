public class Ex01 {
	private String prenom;
	private String nom;
	private int anneedenaissance;
	private String situationamoureuse;
	private String adresse;
	private String ville;
	
	public Ex01(String prenom, String nom, int anneedenaissance, String situationamoureuse, String adresse, String ville) {
		this.prenom = prenom; //ou on définit String p (par exemple) et on a pas besoin de this
		this.nom = nom;
		this.anneedenaissance = anneedenaissance;
		this.situationamoureuse = situationamoureuse;
		this.adresse = adresse;
		this.ville = ville;
	}
	
	public Ex01() {
		this("", "", 0, "", "", "");
	}
	
	public int age (int annee) {
		return (annee - anneedenaissance);
	}
	
	public void afficheInfo(int annee) {
		System.out.println(prenom + " " + nom + "est né(e) en " + anneedenaissance + ", est " + situationamoureuse + "et habite" + adresse + "à" + ville + ".");
		System.out.println("Son age : " + age(annee));
	}
}

class TestEx01 {
	public static void main(String[] args) {
			Ex01 e1 = new Ex01 ("Dulac", "Marie", 1969, "Célibataire", "10 rue des Saints-Pères", "Paris.");
			e1.afficheInfo(2021);
		}
}