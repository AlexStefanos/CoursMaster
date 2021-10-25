package up.mi.jgm.td04.repertoire;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import up.mi.jgm.td02.repertoire.Personne;
import up.mi.jgm.td03.repertoire.RepertoireAmeliore;

public class SauvegardeRepertoire {
	
	/**
	 * Permet de sauvegarder un RepertoireAmeliore dans un fichier texte
	 * @param rep le RepertoireAmeliore qui doit etre sauvegarde
	 * @param file le chemin du fichier
	 */
	public static void sauvegarder(RepertoireAmeliore rep, String file) {
		try (PrintWriter printW = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
			Personne proprio = rep.getProprietaire();
			printW.println(
					"proprietaire(" + proprio.getPrenom() + "," + proprio.getNom() + "," + proprio.getNumero() + ")");
			for (Personne contact : rep.getRepertoire()) {
				printW.println(
						"contact(" + contact.getPrenom() + "," + contact.getNom() + "," + contact.getNumero() + ")");
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
