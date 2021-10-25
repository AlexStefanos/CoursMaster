package exo3;

import java.util.Collections;
import java.util.List;

public class UtilMots {
	
	/**
	 * Retourne le miroir du mot passe en parametre
	 * 
	 * @param mot le mot dont on veut le miroir
	 * @return le miroir du mot passe en parametre
	 */
	public static String miroir(String mot) {
		return new StringBuilder(mot).reverse().toString();
	}

	/**
	 * Indique si un mot est un palindrome
	 * 
	 * @param mot le mot a tester
	 * @return true si et seulement si mot est un palindrome
	 */
	public static boolean palidrome(String mot) {
		return mot.equals(miroir(mot));
	}

	/**
	 * Trie une liste de mots dans l'odre lexicograhpique. Cette methode modifie
	 * l'objet, et en retourne une reference.
	 * 
	 * @param mots la liste de mots a trier
	 * @return la liste triee
	 */
	public static List<String> trierListeMots(List<String> mots) {
		Collections.sort(mots);
		return mots;
	}
}
