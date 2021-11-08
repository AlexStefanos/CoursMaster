package up.mi.jgm.td04.unix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyGrep {

	public static void main(String[] args) {
		if(args.length != 2) {
			System.err.println("Ce programme attend deux arguments sur la ligne de commande :");
			System.err.println("\t- le chemin vers un fichier texte");
			System.err.println("\t- une chaine de caracteres");
			System.exit(1);
		}
		
		try (GrepReader grep = new GrepReader(new FileReader(new File(args[0])), args[1]);){
			String ligne = null ;
			while((ligne = grep.readLine())!=null) {
				System.out.println(ligne);
			}
			
		} catch (FileNotFoundException e) {
			System.err.print("Le fichier " + args[0] + " n'existe pas.");
			System.exit(2);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(3);
		}
	}

}
