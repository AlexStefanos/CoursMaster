package ex02Examen;

import java.util.Scanner;

public class Epargne {
	private static double plafond;
	private static double tauxInteret;
	private String titulaire;
	private double solde;
	
	public Epargne(String titulaire, double depotInitial) throws EpargneException{
		this.titulaire = titulaire;
		this.plafond = 30000.0;
		this.tauxInteret = 0.03;
		this.solde = depotInitial;
	}
	
	public void crediter(double credit) throws EpargneException {
		if ((solde + credit) <= plafond) {
			solde += credit;
		}
		else {
			throw new EpargneException ("Dans cr�diter : le plafond de " + plafond + "ne peut pas �tre d�pass�.");
		}
	}
	
	public void debiter(double debit) throws EpargneException {
		if ((solde - debit) >= 0) {
			solde -= debit;
		}
		else {
			throw new EpargneException ("Dans d�biter : le solde " + solde + "ne peut pas �tre inf�rieur � 0.");
		}
	}

	public void afficher() {
		System.out.println("Le compte de " + titulaire + "a un solde de " + solde + "pour un plafond de " + plafond + "avec un taux d'int�r�t de " + tauxInteret);
	}
}
