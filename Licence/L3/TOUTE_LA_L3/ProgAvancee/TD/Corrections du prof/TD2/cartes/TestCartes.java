package up.mi.jgm.td02.cartes;

import up.mi.jgm.td02.cartes.ameliore.CarteBasique;
import up.mi.jgm.td02.cartes.ameliore.CarteManille;
import up.mi.jgm.td02.cartes.ameliore.MainBasique;
import up.mi.jgm.td02.cartes.ameliore.MainManille;
import up.mi.jgm.td02.cartes.simple.Carte;
import up.mi.jgm.td02.cartes.simple.MainSimple;

public class TestCartes {

	public static void main(String[] args) {
		System.out.println("Avec cartes simples (exercice VII)");
		MainSimple mainSimple = new MainSimple();
		mainSimple.add(new Carte(Couleur.TREFLE, Valeur.DIX));
		mainSimple.add(new Carte(Couleur.CARREAU, Valeur.CINQ));
		mainSimple.add(new Carte(Couleur.TREFLE, Valeur.CINQ));
		mainSimple.add(new Carte(Couleur.CARREAU, Valeur.AS));
		mainSimple.add(new Carte(Couleur.TREFLE, Valeur.ROI));

		System.out.println("main simple : " + mainSimple);
		System.out.println("main simple triee : " + mainSimple.tri());

		System.out.println("Avec cartes basiques (exercice VIII)");
		MainBasique mainBasique = new MainBasique();
		mainBasique.add(new CarteBasique(Couleur.TREFLE, Valeur.DIX));
		mainBasique.add(new CarteBasique(Couleur.CARREAU, Valeur.CINQ));
		mainBasique.add(new CarteBasique(Couleur.TREFLE, Valeur.CINQ));
		mainBasique.add(new CarteBasique(Couleur.CARREAU, Valeur.AS));
		mainBasique.add(new CarteBasique(Couleur.TREFLE, Valeur.ROI));

		System.out.println("main basique : " + mainBasique);
		System.out.println("main basique triee : " + mainBasique.tri());

		System.out.println("Avec cartes de manille (exercice VIII)");
		MainManille mainManille = new MainManille();
		mainManille.add(new CarteManille(Couleur.TREFLE, Valeur.DIX));
		mainManille.add(new CarteManille(Couleur.CARREAU, Valeur.CINQ));
		mainManille.add(new CarteManille(Couleur.TREFLE, Valeur.CINQ));
		mainManille.add(new CarteManille(Couleur.CARREAU, Valeur.AS));
		mainManille.add(new CarteManille(Couleur.TREFLE, Valeur.ROI));
		
		System.out.println("main manille : " + mainManille);
		System.out.println("main manille triee : " + mainManille.tri());
	}

}
