package ubs.info.as.tp03;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
//import java.util.StringTokenizer;

/**
 * TP03 Programmation Multi-Paradigme : Classe Metro
 * @author Alexandre Stefanos
 *
 */
public class Metro {
	/**
	 * Lecture du fichier donné
	 * @param nomDeFichier_ : nom du fichier donné
	 * @throws IOException
	 */
	public static void lireMetro(String nomDeFichier_) throws IOException { //peut-être return une String
		try(FileReader fReader = new FileReader(nomDeFichier_)) {
			BufferedReader bReader = new BufferedReader(fReader);
			String str = new String();
			while(bReader.ready()) {
				str += bReader.readLine();
				str += '\n';
			}
//			String result = new String();
//			StringTokenizer strTokenizer = new StringTokenizer(str, " ");
//			while(strTokenizer.hasMoreTokens() == true) {
//				result += strTokenizer.nextToken();					
//			}
		}
	}
}
