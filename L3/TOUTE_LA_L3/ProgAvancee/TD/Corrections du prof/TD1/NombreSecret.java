package up.mi.jgm.td01;

import java.util.Scanner;

public class NombreSecret {
	private static int min = 1;
	private static int max = 100;

	public static void main(String[] args) {
		System.out.println("Pensez a un nombre !");
		Scanner sc = new Scanner(System.in);
		char reponse = ' ';
		int proposition;

		do {
			proposition = calculProposition();
			System.out.println("Ma proposition est " + proposition + ".");
			System.out.println("Est-ce plus grand (+), plus petit (-), ou egal (=) a votre nombre ?");

			reponse = sc.nextLine().charAt(0);

			switch (reponse) {
			case '-':
				min = proposition;
				proposition = calculProposition();
				break;
			case '+':
				max = proposition;
				proposition = calculProposition();
				break;
			case '=':
				System.out.println("Victoire !");
				break;
			default:
				System.out.println("La reponse " + reponse + " est invalide.");
			}

		} while (reponse != '=');

		sc.close();
	}

	private static int calculProposition() {
		return (min + max) / 2;
	}
}
