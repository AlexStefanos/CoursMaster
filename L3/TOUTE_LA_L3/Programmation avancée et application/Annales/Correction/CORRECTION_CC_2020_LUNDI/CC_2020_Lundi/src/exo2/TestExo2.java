package exo2;

import java.util.ArrayList;
public class TestExo2 {
	public static void main(String[] args) {
		Voyage belgique = new Voyage("Belgique", "2021-01-01", "2021-05-01", 10000, 1000, Hebergement.HOTEL, Transport.BUS, Formule.DEMI_PENSION, 15);
		Voyage chypre = new Voyage("Chypre", "2021-02-01", "2021-06-01", 10000, 1000, Hebergement.HOTEL, Transport.BUS, Formule.DEMI_PENSION, 15);
		Voyage grece = new Voyage("Grece", "2020-01-01", "2020-05-01", 10000, 1000, Hebergement.HOTEL, Transport.BUS, Formule.DEMI_PENSION, 15);
		Voyage italie = new Voyage("Italie", "2020-02-01", "2020-06-01", 10000, 1000, Hebergement.HOTEL, Transport.BUS, Formule.DEMI_PENSION, 15);
		Voyage roumanie = new Voyage("Roumanie", "2020-03-01", "2020-07-01", 20000, 2000, Hebergement.AUBERGE, Transport.AVION, Formule.PENSION, 10);

		Touriste touriste1 = new Touriste("Hollande", "Francois", "0167894654", "mail@gmail.com", 60);
		Touriste touriste2 = new Touriste("Sarkozy", "Nicolas", "0167894667", "mail@yahoo.com", 63);
		Touriste touriste3 = new Touriste("Macron", "Emmanuel", "0167894689", "mail@u-paris.fr", 40);
		
		ArrayList<Touriste> clients = new ArrayList<Touriste>();
		ArrayList<Voyage> prestationVoyages = new ArrayList<Voyage>();
		
		clients.add(touriste1);
		clients.add(touriste2);
		clients.add(touriste3);

		prestationVoyages.add(belgique);
		prestationVoyages.add(chypre);
		prestationVoyages.add(grece);
		prestationVoyages.add(italie);
		prestationVoyages.add(roumanie);
		
		Tour tour = new Tour("20 rue du Département 75018 Paris", "1999-07-01", clients, prestationVoyages);
		
		tour.ajouterVoyageur(touriste1, belgique);
		tour.ajouterVoyageur(touriste1, chypre);
		tour.ajouterVoyageur(touriste2, chypre);
		tour.ajouterVoyageur(touriste3, chypre);
		
		tour.ajouterVoyageur(touriste1, italie);
		tour.ajouterVoyageur(touriste2, roumanie);
		tour.ajouterVoyageur(touriste2, grece);
		tour.ajouterVoyageur(touriste2, italie);
		tour.ajouterVoyageur(touriste1, roumanie);

		//Question a
		System.out.println("Question a - Test obtenirGrandVoyageur (1) : " + tour.obtenirGrandVoyageur("2021"));
		System.out.println("Question a - Test obtenirGrandVoyageur (2) : " + tour.obtenirGrandVoyageur("2020"));

		//Question b
		System.out.println("Question b - Test obtenirNombreClientParVoyage (1) : " + tour.obtenirNombreClientParVoyage(belgique));
		System.out.println("Question b - Test obtenirNombreClientParVoyage (2) : " + tour.obtenirNombreClientParVoyage(roumanie));

		//Question c
		System.out.println("Question c - Test obtenirMeilleurClient (1) : " +  tour.obtenirMeilleurClient("2021"));
		System.out.println("Question c - Test obtenirMeilleurClient (2) : " + tour.obtenirMeilleurClient("2020"));
		
		//Question d
		System.out.println("Question c - Test obtenirListeMailARelancerFrequence : \n" +  tour.obtenirListeMailARelancerFrequence("2021", "2020"));

		//Question e
		System.out.println("Question e - Test obtenirListeMailARelancerPrix : \n" +  tour.obtenirListeMailARelancerPrix("2021", "2020"));

		//Question f
		System.out.println("Question f - Test obtenirVoyageAVendreParPeriode : " +  tour.obtenirVoyageAVendreParPeriode("01"));


	}
}
