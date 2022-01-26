package up.mi.jgm.td02.dicot9;

import java.util.ArrayList;
import java.util.HashMap;

public class TestDicoT9 {
	public static void main(String[] args) {
		HashMap<String, ArrayList<String>> dico = new HashMap<String, ArrayList<String>>();
		enregistrer(dico, "bonjour");
		enregistrer(dico, "bonne");
		enregistrer(dico, "comme");

		System.out.println(recuperer(dico, "26663"));
		System.out.println(recuperer(dico, "2665687"));
		System.out.println(recuperer(dico, "123456"));
	}

	/**
	 * Recupere la liste des mots associes a une chaine codee en T9
	 * @param dico le dictionnaire ou chercher la chaine
	 * @param chaineT9 la chaine codee en T9
	 * @return la liste des mots qui correspondent a chaineT9 dans dico
	 */
	private static ArrayList<String> recuperer(HashMap<String, ArrayList<String>> dico, String chaineT9) {
		ArrayList<String> liste;
		if (dico.containsKey(chaineT9)) {
			liste = dico.get(chaineT9);
		} else {
			liste = new ArrayList<String>();
		}
		return liste;
	}

	/**
	 * Ajoute dans le dico une chaine, en fonction de sa valeur codee en T9
	 * @param dico le dico ou ajouter la chaine
	 * @param chaine la chaine a ajouter dans le dico
	 */
	private static void enregistrer(HashMap<String, ArrayList<String>> dico, String chaine) {
		ArrayList<String> liste;
		String chaineCodee = getChaineCodee(chaine);
		if (dico.containsKey(getChaineCodee(chaine))) {
			liste = dico.get(chaineCodee);
		} else {
			liste = new ArrayList<String>();
			dico.put(chaineCodee, liste);
		}
		liste.add(chaine);

	}

	/**
	 * Permet d'obtenir le codage en T9 d'un mot
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
}