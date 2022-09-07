package up.mi.jgm.td04.television;

import java.util.Collections;
import java.util.List;

import up.mi.jgm.td03.television.Programme;

public class TestProgrammeTV {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Le chemin vers un fichier doit etre donne sur la ligne de commande.");
			System.exit(1);
		}

		List<Programme> liste = Parser.parser(args[0]);

		Collections.sort(liste);
		for (Programme p : liste) {
			System.out.println(p);
		}
	}

}
