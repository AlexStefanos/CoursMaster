package TP07;

import java.util.Vector;

public class Compte {
	//Liste des attributs
	private String identifiant;
	private float solde;
	
	//cet attribut repr�sente (sous forme d'un vecteur) une liste des mouvements effectu�s
	// sur le compte c�d les retraits et les d�pots
	private Vector<Float> mouvements ;

	//Constructeur
	public Compte(String identifiant, float solde){
		this.identifiant = identifiant;
		this.solde = solde;
		mouvements = new Vector<Float>();
	}
	
	//Les get et les set pour acc�der ou modifier les attributs priv�s
	public String getIdentifiant() {
		return identifiant;
	}
	
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
		
	}

	public float getSolde() {
		return solde;
		
	}
	
	public void setSolde(int solde) {
		this.solde = solde;
		float difference = solde - this.solde;
		mouvements.add(difference);
		
	}
	 
	 
	//Les autres m�thodes
	
	//d�pot d'un montant
	public void depot(float montant){
		solde += montant;
		mouvements.add(montant);
	}
	
	//retrait d'un montant
	public void retrait(float montant){
		solde -= montant;
		mouvements.add(-montant);
	}

	//Afficher la liste des mouvements r�alis�s sur le compte
	public void afficherMouvements(){
		for(Float f : mouvements)
		System.out.println(f);
	 }
	
	//Afficher l'identifiant et le solde du compte
	public void afficherCompte(){
		System.out.println("Compte de "+identifiant+", solde = "+solde);
	} 
}
