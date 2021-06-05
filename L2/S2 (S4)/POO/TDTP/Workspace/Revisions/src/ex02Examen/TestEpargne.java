package ex02Examen;

import java.util.Scanner;

public class TestEpargne {
	public static void main() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Donnez le nom du titulaire du compte ? ");
		String titulaire;
		titulaire = sc.nextLine();
		
		System.out.println("Donnez la valeur du dépot initial ?");
		double depotInitial;
		depotInitial = sc.nextDouble();
		
		try {
			Epargne epargne = new Epargne(titulaire, depotInitial);
			epargne.crediter(5000);
			epargne.afficher();
			epargne.debiter(4000);
			epargne.afficher();
		} catch (EpargneException e) {
			System.out.println("Erreur dans l'exécution du main : " + e.getMessage());
		}
	}
}
