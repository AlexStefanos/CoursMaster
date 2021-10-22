package up.mi.jgm.td01;

import java.util.Scanner;

public class EntreeNotes {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Combien d'etudiants y a-t'il ?");
		int nbEtudiants = sc.nextInt();

		double[] notes = new double[2 * nbEtudiants];
		for (int i = 0; i < nbEtudiants; i++) {
			System.out.println("Quelle est la note de CC de l'etudiant " + i + " ?");
			notes[2 * i] = sc.nextDouble();
			System.out.println("Quelle est la note d'examen de l'etudiant " + i + " ?");
			notes[2 * i + 1] = sc.nextDouble();
		}
		System.out.println("La moyenne du groupe d'etudiants est " + UtilNotes.moyenneGroupe(notes));
		sc.close();
	}
}
