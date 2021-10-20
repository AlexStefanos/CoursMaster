package up.mi.as.td02;
import java.util.ArrayList;
import java.util.HashMap;

public class T9 {
	
	public T9() {}
	
	public static String fromStr(String mot) {
		String code = "";
		for(int i = 0; i < mot.length(); i++) {
			code += getChiffreT9(mot.charAt(i));
		}
		return(code);
	}
	
	public static byte getChiffreT9(char c) {
		switch(Character.toLowerCase(c)) {
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
	
	public static void enregistrer(HashMap<String, ArrayList<String>> dico, String chaine) {
		String conv = fromStr(chaine);
		if(dico.containsKey(conv)) {
			if(!dico.get(conv).contains(chaine))
				dico.get(conv).add(chaine);
		}
		else {
			ArrayList<String> l = new ArrayList<String>();
			l.add(chaine);
			dico.put(conv, l);
		}
	}
}
