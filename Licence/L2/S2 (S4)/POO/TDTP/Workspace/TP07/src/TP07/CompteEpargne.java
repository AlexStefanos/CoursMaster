package TP07;

public class CompteEpargne extends Compte{
	//Attributs
		private int annees;
		private float taux;

		//Constructeur
		public CompteEpargne (String identifiant,float solde,int annees,float taux){
			super(identifiant,solde);
			this.annees = annees;
			this.taux = taux;
		}

		//gets et sets
		public int getAnnees() { return annees; }
		public void setAnnees(int annees) { this.annees = annees; }
		public float getTaux() { return taux; }
		public void setTaux(float taux) { this.taux = taux; }
		
		//M�thode getSolde
		public float getSolde(){
		  float solde = super.getSolde();
	 	  return (float)(solde * (Math.pow((1 + taux),annees)));
		}

}

