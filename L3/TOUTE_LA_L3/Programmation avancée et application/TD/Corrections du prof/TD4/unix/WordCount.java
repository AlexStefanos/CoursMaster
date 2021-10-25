package up.mi.jgm.td04.unix;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Ce programme attend le chemin vers un fichier sur la ligne de commande.");
			System.exit(1);
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
			StringBuilder build = new StringBuilder();
			String ligne;
			int nbLignes = 0;

			while ((ligne = reader.readLine()) != null) {
				build.append(ligne + "\n");
				nbLignes++;
			}

			String contenuFichier = build.toString();
			String[] tab = contenuFichier.split("[ \n\t]");
			System.out.println("\t" + nbLignes + "\t" + tab.length + "\t" + contenuFichier.length() + " " + args[0]);

		} catch (FileNotFoundException e) {
			System.err.println("Le fichier " + args[0] + " n'existe pas.");
			System.exit(2);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(3);
		}
	}
}
