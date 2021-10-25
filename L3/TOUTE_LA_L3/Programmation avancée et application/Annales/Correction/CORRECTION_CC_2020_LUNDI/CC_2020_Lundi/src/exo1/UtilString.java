package exo1;

import java.util.List;
import java.util.ArrayList;
public class UtilString {
	
	/**
	 * Détermine si une chaîne de caractères appartient a un tableau de chaînes de caractères
	 * 
	 *​ @param​ str la chaîne
	 *​ @param​ tab le tableau
	 *​ @return​ true si et seulement si str appartient a tab
	 */
	public static boolean appartientTableau(String str, String[] tab) {
		for(int i = 0 ; i < tab.length ; i++) {
			if( tab[i].equals(str) )
				return true;
		}
	
		return false;
	}
	
	/**
	 * Méthode qui détermine si une chaîne de caractères apparaît dans l'un des éléments d'un tableau de chaînes de caractères
	 *
	 * @param​ str la chaîne
	 *​ @param​ tab le tableau
	 *​ @return​ String contenant l'élément du tableau dans lequel str apparaît dans tab, reste null sinon.
	 */
	public static String apparaitDansTableau(String str, String[] tab) {
		for(int i = 0 ; i < tab.length ; i++) {
			if( tab[i].contains(str) )
				return tab[i];
		}
	
		return null;
	}
	
	/**
	 * Méthode qui détermine si une chaîne de caractères est un palindrome. 
	 *
	 * @param​ str la chaîne
	 * @return​ true si palindrome, false sinon
	 */
	public static boolean estPalindrome(String str) {
		// toute chaîne de caractères contenant moins de deux caractères n’est ​de facto​ pas un palindrome
		if(str.length() < 2)		
			return false;
		 
		 StringBuilder temp = new StringBuilder("");
		 for(int i = (str.length() - 1) ; i >= 0 ; i--)
			 temp.append( str.charAt(i) );
		 
		 return temp.toString().equals(str);
	}
	
	/**
	 * Méthode qui reconnaît si une chaîne est un palindrome ​parmorceaux.
	 *
	 * ​@param​ str la chaîne
	 * ​@return​ String[]  ​contenant chacun des palindromes trouvé.
	 */
	public static String[] estPalindromeParMorceaux(String str) {
		// toute chaîne de caractères contenant moins de deux caractères n’est ​de facto​ pas un palindrome
		if(str.length() < 2)		
			return new String[0];

		 //..
		 
		return new String[0];
	}
	
	public static String[] listerPalindrome(String[] strTab) {
		List<String> palindromes = new ArrayList<String>();
		for(int i = 0 ; i < strTab.length; i++) {
			if( estPalindrome(strTab[i]) )
				palindromes.add(strTab[i]);
			
			String[] tmp = estPalindromeParMorceaux(strTab[i]);
			for(int j = 0 ; j < tmp.length ; j++)
				palindromes.add(tmp[j]);
		}
		
		return palindromes.toArray(new String[0]);
	}

}