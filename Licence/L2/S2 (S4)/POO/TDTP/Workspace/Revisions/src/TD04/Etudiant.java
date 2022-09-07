package TD04;

public class Etudiant {
	private String nom, avis;
	private int moy,maxnotes;
	private Notation[] nota;
	
	public Etudiant (String nom, int maxnotes) {
		this.nom = nom;
		this.maxnotes = maxnotes;
		avis = null;
		moy = 0;
		nota = new Notation[maxnotes];
	}
	
	private int sommeNotes() {
		int s=0;
		for (int i = 0; i<nota.length; i++) {
			s += nota[i].getNote() * nota[i].getCoeff();
		}
		return s;
	}
	
	private int sommeCoeffs() {
		int s=0;
		for (int i=0; i<nota.length; i++) {
			s+= nota[i].getCoeff();
		}
		return s;
	}
	
	 public void ajoutNotation(Notation n, int pos){
		 nota[pos]=n;
	 }
	
	public void moyenne() {
		 moy = sommeNotes()/sommeCoeffs();
	}
	
	 public void avis(double seuil){
		 if(moy>= seuil)
			 avis="Favorable";
		 else
			 avis = "Defavorable";
	 } 
	
	public void afficher() {
		 System.out.println("Nom : " + nom);
		 System.out.println("==================Notations================");
		 for(int i=0;i<nota.length;i++){
			 nota[i].affichage();
		 }
		 System.out.println("============================================");
		 System.out.println("Moyenne : " + moy);
		 System.out.println("Avis : " + avis);
	}
}
