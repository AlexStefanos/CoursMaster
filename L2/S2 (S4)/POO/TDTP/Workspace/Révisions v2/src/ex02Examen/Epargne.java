package ex02Examen;

public class Epargne {
	private static double plafond;
	private static double tauxInteret;
	private String titulaire;
	private double solde;
	
	public Epargne(String titulaire, double solde) {
		this.titulaire = titulaire;
		this.plafond = 30000;
		this.tauxInteret = 0.03;
		this.solde = solde;
	}
	
	public void crediter(double credit) throws EpargneException {
		if ((solde + credit) > plafond) {
			throw new EpargneException ("Crediter : le solde ne peut dépasser le plafond.");
		}
		else {
			solde += credit;
		}
	}
	
	public void debiter(double debit) throws EpargneException {
		if ((solde - debit) < 0)
			throw new EpargneException ("Debiter : le solde ne peut être inférieur à 0.");
		else
			solde -= debit;
	}
	
	public void afficher() {
		System.out.println("Titulaire : " + titulaire);
		System.out.println("Solde : " + solde);
	}
}
