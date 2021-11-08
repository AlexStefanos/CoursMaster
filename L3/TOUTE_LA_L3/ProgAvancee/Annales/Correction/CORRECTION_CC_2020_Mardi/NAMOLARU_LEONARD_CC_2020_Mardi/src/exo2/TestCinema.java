package exo2;

public class TestCinema {
	public static void main(String[] args) {
		Cinema cinema = new Cinema(3);
		cinema.salle3D(2);
		cinema.capaciteSalle(1, 100);
		cinema.capaciteSalle(2, 150);
		cinema.capaciteSalle(3, 150);
		cinema.ajouterSeance("Le Parrain", 14, 1);
		cinema.ajouterSeance("Star Wars", 14, 2);
		cinema.ajouterSeance("Le Seigneur des Anneaux", 14, 3);
		cinema.ajouterSeance("Le Parrain", 16, 1);

		if (!cinema.ajouterSeance("Le Parrain", 15, 1)) {
			System.out.println("Il est impossible de creer cette seance, la salle est deja occupee.");
		}

		if (!cinema.vendrePlace("Jurassic Park", 16)) {
			System.out.println("Impossible de vendre cette place, aucune seance ne correspond.");
		}

		for (int i = 0; i < 50; i++) {
			cinema.vendrePlace("Le Parrain", 14);
		}

		for (int i = 0; i < 40; i++) {
			cinema.vendrePlace("Le Parrain", 16);
		}

		for (int i = 0; i < 120; i++) {
			cinema.vendrePlace("Star Wars", 14);
		}

		for (int i = 0; i < 90; i++) {
			cinema.vendrePlace("Le Seigneur des Anneaux", 14);
		}

		System.out.println("Le taux de remplissage moyen est " + cinema.tauxRemplissage() + ".");
		System.out.println("Le chiffre d'affaire est " + cinema.chiffreAffaire() + ".");
	}
}
