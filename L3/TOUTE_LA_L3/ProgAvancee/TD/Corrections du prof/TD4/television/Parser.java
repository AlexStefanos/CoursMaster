package up.mi.jgm.td04.television;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import up.mi.jgm.td03.television.Divertissement;
import up.mi.jgm.td03.television.Fiction;
import up.mi.jgm.td03.television.Journal;
import up.mi.jgm.td03.television.Programme;
import up.mi.jgm.td03.television.Reportage;
import up.mi.jgm.td03.television.Theme;

public class Parser {
	/**
	 * Lit un fichier texte qui decrit des programmes tele, et retoure la liste de
	 * programmes correspondant
	 * 
	 * @param fichier le chemin vers le fichier
	 * @return la liste des programmes décrits dans le fichier
	 */
	public static List<Programme> parser(String fichier) {
		List<Programme> liste = new ArrayList<Programme>();
		try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
			String ligne = null;

			while ((ligne = br.readLine()) != null) {
				liste.add(parserLigne(ligne));
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		return liste;
	}

	/**
	 * Parse une ligne pour creer un Programme
	 * 
	 * @param ligne la ligne lue dans le fichier de description des programmes TV
	 * @return le Programme correspondant a la ligne
	 * @throws IllegalArgumentException si la ligne lue est incorrecte
	 */
	private static Programme parserLigne(String ligne) {
		if (ligne.startsWith("divertissement")) {
			return parserDivertissement(ligne);
		}
		if (ligne.startsWith("journal")) {
			return parserJournal(ligne);
		}
		if (ligne.startsWith("reportage")) {
			return parserReportage(ligne);
		}
		if (ligne.startsWith("fiction")) {
			return parserFiction(ligne);
		}
		throw new IllegalArgumentException("La ligne lue est incorrecte :\n" + ligne);
	}

	private static Divertissement parserDivertissement(String ligne) {
		String infos = ligne.substring("divertissement(".length(), ligne.length() - 1);
		String[] infosTab = infos.split(",");
		return new Divertissement(Integer.parseInt(infosTab[2]), infosTab[0], infosTab[1]);
	}

	private static Journal parserJournal(String ligne) {
		String infos = ligne.substring("journal(".length(), ligne.length() - 1);
		String[] infosTab = infos.split(",");
		return new Journal(Integer.parseInt(infosTab[1]), Integer.parseInt(infosTab[2]), infosTab[0]);
	}

	private static Reportage parserReportage(String ligne) {
		String infos = ligne.substring("reportage(".length(), ligne.length() - 1);
		String[] infosTab = infos.split(",");
		return new Reportage(Integer.parseInt(infosTab[2]), Integer.parseInt(infosTab[3]), Theme.valueOf(infosTab[0]),
				infosTab[1]);
	}

	private static Fiction parserFiction(String ligne) {
		String infos = ligne.substring("fiction(".length(), ligne.length() - 1);
		String[] infosTab = infos.split(",");
		boolean redif = infosTab[2].equals("T") ? true : false;
		return new Fiction(Integer.parseInt(infosTab[3]), Integer.parseInt(infosTab[4]), infosTab[0], infosTab[1],
				redif);
	}
}
