package up.mi.jgm.td04.repertoire;

import java.util.InputMismatchException;
import java.util.Scanner;

import up.mi.jgm.td02.repertoire.Personne;
import up.mi.jgm.td03.repertoire.RepertoireAmeliore;

public class MainRepertoire {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Indiquez votre prenom, votre nom et votre numero de telephone pour creer le repertoire :");
		System.out.print("Prenom : ");
		String prenom = sc.nextLine();
		System.out.print("Nom : ");
		String nom = sc.nextLine();
		System.out.print("Numero : ");
		String numero = sc.nextLine();

		RepertoireAmeliore rep = new RepertoireAmeliore(new Personne(prenom, nom, numero));

		int choix;

		do {
			menu();
			choix = lireEntierAuClavier(sc, "Choix = ");

			switch (choix) {
			case 0:
				break;
			case 1:
				ajoutContact(rep, sc);
				break;
			case 2:
				chercherNumero(rep, sc);
				break;
			case 3:
				chercherPersonne(rep, sc);
				break;
			case 4:
				System.out.println(rep);
				break;
			case 5:
				sauvegarder(rep, sc);
				break;
			case 6:
				rep = charger(sc);
				break;
			default:
				System.out.println("Le choix " + choix + " n'est pas correct.");
			}

		} while (choix != 0);

		sc.close();
	}

	/**
	 * Permet de charger un repertoire a partir d'un fichier dont le chemin est
	 * obtenu via un scanner
	 * 
	 * @param sc le scanner qui permet d'obtenir le chemin du fichier
	 * @return le nouveau repertoire
	 */
	private static RepertoireAmeliore charger(Scanner sc) {
		sc.nextLine();
		System.out.print("Quel est le chemin vers le fichier a charger ? ");
		String file = sc.nextLine();
		return ParserRepertoire.parser(file);
	}

	/**
	 * Permet de sauvegarder le repertoire dans un fichier dont le nom est obtenu
	 * dans un scanner
	 * 
	 * @param rep le repertoire qui doit etre sauvegarde
	 * @param sc  le scanner qui permet d'obtenir le nom du fichier
	 */
	private static void sauvegarder(RepertoireAmeliore rep, Scanner sc) {
		sc.nextLine();
		System.out.print("Quel est le chemin vers le fichier de sauvegarde ? ");
		String file = sc.nextLine();
		SauvegardeRepertoire.sauvegarder(rep, file);
	}

	/**
	 * Cherche dans un repertoire le proprietaire d'un numero obtenu via un scanner
	 * 
	 * @param rep le repertoire
	 * @param sc  le scanner a partir duquel on obtient le numero
	 */
	private static void chercherPersonne(RepertoireAmeliore rep, Scanner sc) {
		sc.nextLine();
		System.out.print("Numero du contact : ");
		String numero = sc.nextLine();
		System.out.println(rep.cherchePersonne(numero));
	}

	/**
	 * Cherche dans le repertoire le numero d'un contact dont le prenom et le nom
	 * sont lus au clavier
	 * 
	 * @param rep le repertoire
	 * @param sc  le scanner ou sont lus le prenom et le nom
	 */
	private static void chercherNumero(RepertoireAmeliore rep, Scanner sc) {
		sc.nextLine();
		System.out.print("Prenom du contact : ");
		String prenom = sc.nextLine();
		System.out.print("Nom du contact : ");
		String nom = sc.nextLine();
		System.out.println(rep.chercheNumero(prenom, nom));
	}

	/**
	 * Ajoute dans le repertoire un contact dont les informations sont lues dans un
	 * scanner
	 * 
	 * @param rep le repertoire dans lequel le contact est ajoute
	 * @param sc  le scanner ou les informations sont lues
	 */
	private static void ajoutContact(RepertoireAmeliore rep, Scanner sc) {
		sc.nextLine();
		System.out.print("Prenom du contact : ");
		String prenom = sc.nextLine();
		System.out.print("Nom du contact : ");
		String nom = sc.nextLine();
		System.out.print("Numero du contact : ");
		String numero = sc.nextLine();
		rep.addPersonne(prenom, nom, numero);
	}

	/**
	 * Affiche le menu a l'utilisateur
	 */
	private static void menu() {
		System.out.println("0 - Quitter");
		System.out.println("1 - Ajouter un contact");
		System.out.println("2 - Rechercher le numero d'une personne");
		System.out.println("3 - Rechercher le proprietaire d'un numero");
		System.out.println("4 - Afficher le contenu du repertoire");
		System.out.println("5 - Sauvegarder le repertoire dans un fichier");
		System.out.println("6 - Charger un repertoire a partir d'un fichier");
	}

	/**
	 * Lit un entier au clavier
	 * 
	 * @param sc      le scanner dans lequel lire l'entier
	 * @param message le message a afficher avant la lecture
	 * @return l'entier lu
	 */
	private static int lireEntierAuClavier(Scanner sc, String message) {
		boolean lectureOK = false;
		int nb = 0;
		while (!lectureOK) {
			try {
				System.out.print(message);
				nb = sc.nextInt();
				lectureOK = true ;
			} catch (InputMismatchException e) {
				System.out.println("Il faut taper un nombre entier");
				sc.nextLine();
			}
		}
		return nb;
	}

}
