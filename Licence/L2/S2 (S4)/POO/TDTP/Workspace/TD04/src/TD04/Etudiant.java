package TD04;

public class Etudiant {
	private String nom;
	private double moy;
	private String avis;
	private Notation[] notations;
	
	public Etudiant(String nom, Notation[] notations) {
		this.nom = nom;
		this.moy = 0.0;
		this.avis = null;
		this.notations = notations;
	}
	
	public Etudiant (String nom, int nbNotes) {
		this.nom = nom;
		this.moy = 0.0;
		avis = null;
		notations = new Notation[nbNotes];
	}
	
	private double sommeNotes() {
		double s = 0;
		for (int i = 0; i < notations.length; i++) {
			s += notations[i].getNote()*notations[i].getCoef();
		}
		return s;
	}
	
	private double sommeCoefs() {
		double s = 0;
		for (int i = 0; i < notations.length; i++) {
			s += notations[i].getCoef();
		}
		return s;
	}
	
	public void ajoutNotation(Notation n, int pos) {
		notations[pos] = n;
	}
	
	public void moyenne() {
		moy = sommeNotes() / sommeCoefs();
	}

	public void avis (double seuil) {
		if (moy >= seuil)
			avis = "Favorable";
		else
			avis = "Défavorable";
	}
	
	public void afficher() {
		System.out.println("Nom : " + nom);
		System.out.println("=======================Notations======================");
		for (int i = 0;i<notations.length;i++) {
			notations[i].afficher();
		}
		System.out.println("=======================================================");
		System.out.println("Moyenne : " + moy);
		System.out.println("Avis : " + avis);
	}
}
