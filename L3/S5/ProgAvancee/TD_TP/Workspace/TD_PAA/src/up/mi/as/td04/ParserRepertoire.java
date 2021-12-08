package up.mi.as.td04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParserRepertoire {
	public static Personne creationPersonne (String s) {
		String[] split = new String[3];
		String prenom, nom, num;
		if(s != null) {
			split = s.split(","); //il faut enlever les partanthèses aussi
		}
		else
			return(null);
		prenom = split[0];
		nom = split[1];
		num = split[2];
		return new Personne(prenom, nom, num);
	}
	
	public static RepertoireSimple parser(String file) { 
		ArrayList<Personne> liste = new ArrayList<Personne>();
		Personne proprio = null;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String ligne = null;
			while((ligne = br.readLine()) != null) {
				if(ligne.startsWith("proprio")) {
					if(proprio != null) {
						System.err.println("Il ne peut pas y avoir " + " deux lignes pour le prenom");
						System.exit(0);
					}
					else
						proprio = creationPersonne(ligne);
				}
				else if(ligne.startsWith("contact")) {
					liste.add(creationPersonne(ligne));
				}
			} 
		} catch(FileNotFoundException e) {
				e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		if(proprio == null) {
			System.err.println("Le propriétaire n'est pas indiqué");
			System.exit(0);
		}
		
		RepertoireSimple repS = new RepertoireSimple();
		/*for(Personne p : list) {
			repS.addPersonne(p.getPrenom(), p.getNom(), p.getTel());
		}*/
		return(repS);
	}
}
