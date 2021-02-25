package TD04;

public class TestEtudiant {
	public static void main(String[] args) {
		String nom;
		int nbNotes;
		nom = Saisie.lireChaine("Nom de l'étudiant");
		nbNotes = Saisie.lireEntier("Nombre de notes ?");
		Etudiant e = new Etudiant(nom, nbNotes);
		
		for (int i=0; i<nbNotes; i++) {
			int coef = Saisie.lireEntier("coefficient ?");
			double note = Saisie.lireReel("note ?");
			Notation n = new Notation(coef,note);
			
			e.ajoutNotation(n, i);
		}
		e.moyenne();
		e.avis(12);
		e.afficher();
	}
}
