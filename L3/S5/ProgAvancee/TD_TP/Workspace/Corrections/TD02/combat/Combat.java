package up.mi.jgm.td02.combat;

import java.util.Random;
import java.util.Scanner;

public class Combat {
	
	/**
	 * Pour la gestion des entrées au clavier
	 */
	private static Scanner sc ;
	
	/***
	 * Personnage choisi par l'utilisateur
	 */
	private Personnage persoUtilisateur;
	
	/***
	 * Personnage choisi par l'IA
	 */
	private Personnage persoIA;
	
	/***
	 * Nombre de tours de la partie
	 */
	private int tour;
	
	/***
	 * Constructeur de la classe Combat. Permet aux joueurs de choisir leur personnage.
	 * Initialise le nombre de tour.
	 */
	public Combat() {
		choixPerso();
		this.tour = 1;
	}
	
	/***
	 * Méthode permettant de choisir le personnage de l'IA parmi les choix restants. Stratégie : aléatoire
	 * @param nom1 nom du premier personnage
	 * @param nom2 nom du second personnage
	 */
	public void choixPersoIA(String nom1, String nom2) {
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(2);
		String[] tabNom = {nom1, nom2};
		
		if(tabNom[nombreAleatoire].equals("Aragorn")) {
			 this.persoIA = Personnage.creeAragorn();
			 System.out.println("[Choix] L'IA a choisi Aragorn.");
		}
		else {
			if(tabNom[nombreAleatoire].equals("Gimli")) {
				this.persoIA = Personnage.creeGimli();
				System.out.println("[Choix] L'IA a choisi Gimli.");
			}
			else {
				this.persoIA = Personnage.creeLegolas();
				System.out.println("[Choix] L'IA a choisi Legolas.");
			}
		}
	}
	
	/***
	 * Méthode permettant à l'utilisateur de choisir son personnage parmi les trois disponibles.
	 */
	public void choixPerso() {
		int choix;
		
		// On affiche les choix possibles
		System.out.println("Choisissez votre personnage parmi les 3 suivants :");
		System.out.println("\t 1) Aragorn");
		System.out.println("\t 2) Gimli");
		System.out.println("\t 3) Legolas");
		
		
		do {
			choix = sc.nextInt();
			
			switch(choix) {
				case 1:
					this.persoUtilisateur = Personnage.creeAragorn();
					System.out.println("[Choix] Vous avez choisi Aragorn.");
					choixPersoIA("Legolas","Gimli");
					break;
				case 2:
					this.persoUtilisateur = Personnage.creeGimli();
					System.out.println("[Choix] Vous avez choisi Gimli.");
					choixPersoIA("Legolas","Aragorn");
					break;
				case 3:
					this.persoUtilisateur = Personnage.creeLegolas();
					System.out.println("[Choix] Vous avez choisi Legolas.");
					choixPersoIA("Gimli","Aragorn");
					break;
				default:
					System.out.println("Mauvais choix !");
			}
		} while(choix != 1 && choix != 2 && choix != 3);
	}
	
	public void tourJoueur() {
		Attaque att1 = this.persoUtilisateur.getAttaque1();
		Attaque att2 = this.persoUtilisateur.getAttaque2();
		
		int choix;
		
		boolean choixValide = false;
		
		System.out.println("Attaque(s) possible(s) :");
		System.out.println("\t1) Attaque 1 : " + att1.toString());
		System.out.println("\t2) Attaque 2 : " + att2.toString());
		
		do {
			choix = sc.nextInt();
			
			switch(choix) {
				case 1:
					if(att1.getNbUtilisation() > 0) {
						att1.utilise();
						persoIA.perdPDV(att1.getForce());
						System.out.println("[Attaque USER] " + this.persoUtilisateur.getNom() + " attaque " + this.persoIA.getNom() + " (" + att1.getNom() + ") => succes");
						choixValide = true;
					}
					else
						System.out.println("[Erreur] Impossible d'utiliser l'Attaque 1 (nbUtilisation = 0) !");
					break;
				case 2:
					if(att2.getNbUtilisation() > 0) {
						att2.utilise();
						persoIA.perdPDV(att2.getForce());
						System.out.println("[Attaque USER] " + this.persoUtilisateur.getNom() + " attaque " + this.persoIA.getNom() + " (" + att1.getNom() + ") => succes");
						choixValide = true;
					}
					else
						System.out.println("[Erreur] Impossible d'utiliser l'Attaque 2 (nbUtilisation = 0 ) !");
					break;
				default:
					System.out.println("Mauvais choix !");
			}
		} while(!choixValide);
	}
	
	public Attaque randomChoixAttaque(Attaque attaque1, Attaque attaque2) {
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(2);
		if(nombreAleatoire == 0)
			return attaque1;
		else
			return attaque2;
	}
	
	public void tourIA() {
		Attaque att1 = this.persoIA.getAttaque1();
		Attaque att2 = this.persoIA.getAttaque2();
		
		Attaque att = null;
		
		if(att1.getNbUtilisation() > 0 && att2.getNbUtilisation()>0) {
			att = randomChoixAttaque(att1,att2);	
		}
		if(att1.getNbUtilisation() > 0 && att2.getNbUtilisation() == 0) {
			att = att1;	
		}
		if(att1.getNbUtilisation() == 0 && att2.getNbUtilisation() > 0) {
			att = att2;	
		}
		att.utilise();
		persoUtilisateur.perdPDV(att.getForce());
		System.out.println("[Attaque IA] " + this.persoIA.getNom() + " attaque " + this.persoUtilisateur.getNom() + " (" + att1.getNom() + ") => succes");
	}
	
	public void etatPartie() {
		System.out.println("--------------------------------------");
		System.out.println(this.persoUtilisateur);
		System.out.println(this.persoIA);
		System.out.println("--------------------------------------");
	}
	
	
	
	public void partie() {
		boolean fin = false;
		
		etatPartie();
		
		while(!fin) {
			System.out.println("\nTour " + this.tour);
			
			tourJoueur();
			
			if(persoIA.estMort()) {
				fin = true;
				System.out.println("Le joueur a gagné !");
				continue;
			}
			tourIA();
			
			if(persoUtilisateur.estMort()) {
				fin = true;
				System.out.println("L'IA a gagné !");
			}
			
			etatPartie();
			this.tour++;
		}
	}
	
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		Combat combat = new Combat();
		combat.partie();
		sc.close();
	}

}
