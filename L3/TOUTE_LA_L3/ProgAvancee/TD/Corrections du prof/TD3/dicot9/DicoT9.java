package up.mi.jgm.td03.dicot9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe qui represente un dictionnaire de chaines T9
 * 
 * @author Jean-Guy Mailly
 *
 */
public class DicoT9 {
	/**
	 * Map qui associe des codes T9 aux chaines de caracteres correspondant a ces
	 * codes
	 */
	private HashMap<String, ArrayList<String>> map;

	/**
	 * Initialise un DicoT9 vide
	 */
	public DicoT9() {
		map = new HashMap<String, ArrayList<String>>();
	}

	/**
	 * Ajoute dans le dico une chaine, en fonction de sa valeur codee en T9
	 * 
	 * @param chaine la chaine a ajouter dans le DicoT9
	 */
	public void enregistrer(String chaine) {
		ArrayList<String> liste;
		String chaineCodee = getChaineCodee(chaine);
		if (map.containsKey(getChaineCodee(chaine))) {
			liste = map.get(chaineCodee);
		} else {
			liste = new ArrayList<String>();
			map.put(chaineCodee, liste);
		}
		liste.add(chaine);
	}

	/**
	 * Recupere la liste des mots associes a une chaine codee en T9
	 * 
	 * @param chaineT9 la chaine codee en T9
	 * @return la liste des mots qui correspondent a chaineT9 dans DicoT9
	 */
	public ArrayList<String> recuperer(String chaineT9) {
		ArrayList<String> liste;
		if (map.containsKey(chaineT9)) {
			liste = map.get(chaineT9);
		} else {
			liste = new ArrayList<String>();
		}
		return liste;
	}

	/**
	 * Permet d'obtenir le codage en T9 d'un mot
	 * 
	 * @param chaine le mot a coder en T9
	 * @return le code T9 de la chaine
	 */
	private static String getChaineCodee(String chaine) {
		StringBuilder build = new StringBuilder();
		for (int i = 0; i < chaine.length(); i++)
			build.append(getChiffreT9(chaine.charAt(i)));
		return build.toString();
	}

	/**
	 * Permet d'obtenir le codage en T9 d'un caractere
	 * 
	 * @param c le caractere a coder en T9
	 * @return le code T9 du caractere c
	 */
	private static byte getChiffreT9(char c) {
		switch (Character.toLowerCase(c)) {
		case 'a':
		case 'b':
		case 'c':
			return 2;
		case 'd':
		case 'e':
		case 'f':
			return 3;
		case 'g':
		case 'h':
		case 'i':
			return 4;
		case 'j':
		case 'k':
		case 'l':
			return 5;
		case 'm':
		case 'n':
		case 'o':
			return 6;
		case 'p':
		case 'q':
		case 'r':
		case 's':
			return 7;
		case 't':
		case 'u':
		case 'v':
			return 8;
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			return 9;
		default:
			return 0;
		}
	}
	
	public Map<String,ArrayList<String>> getMap(){
		return map ;
	}
}
