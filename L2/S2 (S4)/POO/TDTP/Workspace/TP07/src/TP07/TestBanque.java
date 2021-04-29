package TP07;

public class TestBanque {

	public static void main(String[] args) {
		// TODOAuto-generated method stub
		Compte c = new Compte("Alexandre Stefanos",2000);
		c.afficherCompte();
		c.retrait(200);
		c.depot(20);
		c.afficherMouvements();
		c.setSolde(2);
		c.afficherCompte();
				
		CompteEpargne ce = new CompteEpargne("Epargne :",600,1,0.10f);
		ce.afficherCompte();
		System.out.println("Solde avec les interets : "+ce.getSolde());

	}

}
