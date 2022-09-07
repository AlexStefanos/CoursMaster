package exo3;

import java.util.List;
import java.util.ArrayList;

import exo1.UtilString;
import exo2.Voyage;
public class Catalogue {
	private static String catalogue_texte = "Avec un bain de soleil digne de la Nouvelle Zélande, on peut capturer un girafarig au Zoo Safari sans prendre de Xanax";
	private static List<Voyage> voyages = new ArrayList<Voyage>();

	public static void main(String[] args) {
		
		String[] str = UtilString.estPalindromeParMorceaux( Catalogue.catalogue_texte );
		System.out.println("Test estPalindromeParMorceaux(catalogue_texte) : " + str.length);
		
		for(int i = 0 ; i < str.length ; i++) {
			System.out.print(" " + str[i] + " ");
			if (i == (str.length -1)) 
				System.out.println();
		}


	}

}
