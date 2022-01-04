package exams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Dictionnaire {
	private ArrayList<String> mots, definitions;
	public Dictionnaire() {
		mots = new ArrayList<String>();
		definitions = new ArrayList<String>();
	}
	
	public void ajouterMot(String mot, String definition) {
		mots.add(mot);
		definitions.add(definition);
	}
	
	public Dictionnaire parser(String file) {
		Dictionnaire dico = new Dictionnaire();
		try(BufferedReader bR = new BufferedReader(new FileReader(file))) {
			String line = new String();
			String[] donnees;
			while((line = bR.readLine()) != null) {
				donnees = line.split(":");
				dico.ajouterMot(donnees[0], donnees[1]);
			}
		} catch(FileNotFoundException e) {
			System.err.println("Erreur");
			System.exit(1);
		} catch(IOException e) {
			System.err.println("Erreur");
			System.exit(1);
		}
		return(dico);
	}
	
	public void save(String file) {
        try(PrintWriter printW = new PrintWriter(new BufferedWriter(new FileWriter(file)))){
            for(int i = 0; i < mots.size(); i++) {
                printW.println(mots.get(i) + " : " + definitions.get(i));
            }
        }catch(FileNotFoundException e) {
            System.err.println("Erreur");
            System.exit(1);
        }catch (IOException e) {
            System.err.println("Erreur");
            System.exit(1);
        }
	}
	
	public int getNbMots() {
		return mots.size();
	}
	
	public String getMot(int i) {
		return mots.get(i);
	}
	
	public String getDefinition(int i) {
		return definitions.get(i);
	}
}
