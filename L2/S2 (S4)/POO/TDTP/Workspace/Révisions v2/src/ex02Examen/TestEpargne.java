package ex02Examen;

import java.util.Scanner;

public class TestEpargne {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); //ne pas oublier le System.in
		String titulaire;
		titulaire = sc.nextLine();
		
		double depotInitial;
		depotInitial = sc.nextDouble();
	
		try {
			Epargne epargne = new Epargne(titulaire, depotInitial);
			epargne.crediter(5000);
			epargne.afficher();
			epargne.debiter(4000);
			epargne.afficher();
		} catch (EpargneException e) {
			System.out.println("Erreur dans le main : " + e.getMessage()); //ne pas oublier le fonctionnement et l'écriture du bloc catch
		}
	}
}
