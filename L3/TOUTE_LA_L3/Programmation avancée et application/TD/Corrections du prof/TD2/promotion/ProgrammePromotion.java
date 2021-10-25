package up.mi.jgm.td02.promotion;

import java.util.Scanner;

public class ProgrammePromotion {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Promotion promo = new Promotion();

		int choix = 0;

		do {
			menu();
			choix = sc.nextInt();

			switch (choix) {
			case 0:
				break;
			case 1:
				ajoutEtudiant(sc, promo);
				break;
			case 2:
				ajoutNote(sc, promo);
				break;
			case 3:
				affichageEtudiants(promo);
				break;
			case 4:
				moyenneGlobale(promo);
				break;
			case 5:
				majorGlobal(promo);
				break;
			case 6:
				moyenneUnite(sc, promo);
				break;
			case 7:
				majorUnite(sc, promo);
				break;
			default:
				System.out.println("Erreur : l'option " + choix + " n'est pas valable.");
			}
		} while (choix != 0);

		sc.close();

	}

	private static void majorUnite(Scanner sc, Promotion promo) {
		System.out.println("Pour quelle unité d'enseignement voulez-vous le major?");
		for (UniteEnseignement ue : UniteEnseignement.values()) {
			System.out.println(ue.ordinal() + " - " + ue);
		}
		UniteEnseignement ue = UniteEnseignement.values()[sc.nextInt()];
		System.out.println("Le major de promo pour l'unité " + ue + " est : " + promo.majorPromo(ue));
	}

	private static void moyenneUnite(Scanner sc, Promotion promo) {
		System.out.println("Pour quelle unité d'enseignement voulez-vous la moyenne ?");
		for (UniteEnseignement ue : UniteEnseignement.values()) {
			System.out.println(ue.ordinal() + " - " + ue);
		}
		UniteEnseignement ue = UniteEnseignement.values()[sc.nextInt()];
		System.out.println("La moyenne de promo pour l'unité " + ue + " est : " + promo.moyennePromo(ue));
	}

	private static void majorGlobal(Promotion promo) {
		System.out.println("Le major de promo est : " + promo.majorPromo());
	}

	private static void moyenneGlobale(Promotion promo) {
		System.out.println("La moyenne de promo est : " + promo.moyennePromo());
	}

	private static void affichageEtudiants(Promotion promo) {
		for (Etudiant etu : promo) {
			System.out.println(etu);
		}

	}

	private static void ajoutNote(Scanner sc, Promotion promo) {
		System.out.println("Pour quel étudiant souhaitez-vous ajouter une note ?");
		sc.nextLine();
		String nomEtu = sc.nextLine();
		System.out.println("Pour quelle unité d'enseignement ?");
		for (UniteEnseignement ue : UniteEnseignement.values()) {
			System.out.println(ue.ordinal() + " - " + ue);
		}
		UniteEnseignement ue = UniteEnseignement.values()[sc.nextInt()];
		System.out.println("Est-ce une note d'examen (réponse OUI/NON) ?");
		sc.nextLine();
		String reponse = sc.nextLine();
		System.out.println("Quelle est la note ?");
		double note = sc.nextDouble();
		if (reponse.equals("OUI")) {
			promo.ajoutNoteExam(nomEtu, ue, note);
		} else {
			promo.ajoutNoteCC(nomEtu, ue, note);
		}
	}

	private static void ajoutEtudiant(Scanner sc, Promotion promo) {
		System.out.println("Indiquez le nom de l'étudiant :");
		sc.nextLine();
		String nom = sc.nextLine();
		promo.add(new Etudiant(nom));
	}

	private static void menu() {
		System.out.println("Que souhaitez vous faire ?");
		System.out.println("0 - Quitter");
		System.out.println("1 - Ajouter un étudiant");
		System.out.println("2 - Ajouter une note à un étudiant");
		System.out.println("3 - Afficher les étudiants de la promotion");
		System.out.println("4 - Calculer la moyenne globale");
		System.out.println("5 - Afficher le major de promo");
		System.out.println("6 - Calculer la moyenne pour une unité");
		System.out.println("7 - Afficher le major pour une unité");
	}

}
