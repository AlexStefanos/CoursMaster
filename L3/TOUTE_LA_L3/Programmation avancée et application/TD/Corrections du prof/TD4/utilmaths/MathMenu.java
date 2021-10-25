package up.mi.jgm.td04.utilmaths;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MathMenu {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choix = 0;

		do {
			menu();
			choix = lireEntierAuClavier(sc, "choix = ");
			switch (choix) {
			case 0:
				break;
			case 1:
				somme(sc);
				break;
			case 2:
				factorielle(sc);
				break;
			case 3:
				combinaison(sc);
				break;
			case 4:
				puissance(sc);
				break;
			default:
				System.out.println("Le choix " + choix + " n'est pas valide.");
			}
		} while (choix != 0);

		sc.close();
	}

	private static void menu() {
		System.out.println("Quelle operation souhaitez-vous effectuer ?");
		System.out.println("0 Quitter");
		System.out.println("1 Somme de trois entiers");
		System.out.println("2 Factorielle d'un entier");
		System.out.println("3 Combinaison de deux entiers");
		System.out.println("4 Puissance d'un entier");
	}

	private static void somme(Scanner sc) {
		System.out.println("Quels entiers souhaitez vous additionner ?");
		int n1 = lireEntierAuClavier(sc, "n1 = ");
		int n2 = lireEntierAuClavier(sc, "n2 = ");
		int n3 = lireEntierAuClavier(sc, "n3 = ");
		System.out.println(n1 + " + " + n2 + " + " + n3 + " = " + UtilMaths.somme3(n1, n2, n3));
	}

	private static void factorielle(Scanner sc) {
		System.out.println("De quel nombre souhaitez vous calculer la factorielle ?");
		int n = lireEntierAuClavier(sc, "n = ");
		try {
			System.out.println(n + "! = " + UtilMaths.fact(n));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void combinaison(Scanner sc) {
		System.out.println("De quels nombres souhaitez vous calculer la combinaison ?");
		int p = lireEntierAuClavier(sc, "p = ");
		int n = lireEntierAuClavier(sc, "n = ");
		try {
			System.out.println("Le nombre de combinaisons de " + p + " parmi " + n + " est " + UtilMaths.comb(n, p));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void puissance(Scanner sc) {
		System.out.println("Quel entier n souhaitez vous elever a la puissance m ?");
		int n = lireEntierAuClavier(sc, "n = ");
		int m = lireEntierAuClavier(sc, "m = ");
		try {
			System.out.println(n + " puissance " + m + " est egal a " + UtilMaths.puissance(n, m));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Cette methode lit un entier dans un scanner, et s'assure que c'est bien un
	 * entier qui a ete lu. Dans le cas contraire, un message est affiche a
	 * l'utilisateur, et un nouveal entier est demande.
	 * 
	 * @param sc le scanner dans lequel on veut lire un entier
	 * @param message le message affiche a l'utilisateur pour demander l'entier
	 * @return l'entier lu
	 */
	private static int lireEntierAuClavier(Scanner sc, String message) {
		int res = 0;
		boolean lectureOK = false;

		while (!lectureOK) {
			try {
				System.out.print(message);
				res = sc.nextInt();
				lectureOK = true;
			} catch (InputMismatchException e) {
				System.out.println("Il faut taper un nombre entier");
				sc.nextLine();
			}
		}
		return res;
	}
}
