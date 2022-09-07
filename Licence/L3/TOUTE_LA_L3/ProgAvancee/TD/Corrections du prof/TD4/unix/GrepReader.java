package up.mi.jgm.td04.unix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Cette classe permet de lire le contenu d'un fichier en filtrant les lignes en
 * fonction d'un motif.
 * 
 * @author Jean-Guy Mailly
 *
 */
public class GrepReader extends BufferedReader {
	/**
	 * Le motif qui doit apparaitre dans les lignes
	 */
	private String motif;

	/**
	 * Construit un GrepReader
	 * @param in le flux d'entree dans lequel filtrer les lignes
	 * @param motif le motif qui doit apparaitre dans les lignes
	 */
	public GrepReader(Reader in, String motif) {
		super(in);
		this.motif = motif;
	}

	@Override
	public String readLine() throws IOException {
		String ligne = null;
		do {
			ligne = super.readLine();
		} while ((ligne != null) && !(ligne.contains(motif)));
		return ligne;
	}

}
