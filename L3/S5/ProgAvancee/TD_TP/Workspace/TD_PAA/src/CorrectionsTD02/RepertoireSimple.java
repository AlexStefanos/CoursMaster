package CorrectionsTD02;

import java.util.ArrayList;
import java.util.List;

/**
 * Represente un repertoire telephonique simple.
 * 
 * @author Jean-Guy Mailly
 *
 */
public class RepertoireSimple {
	/**
	 * La liste des personnes presentes dans le repertoire
	 */
	private List<Personne> repertoire;

	/**
	 * Construit un repertoire vide
	 */
	public RepertoireSimple() {
		repertoire = new ArrayList<Personne>();
	}

	/**
	 * Ajoute une personne au repertoire avec son identite et son numero de
	 * telephone
	 * 
	 * @param prenom le prenom de la personne sous forme de String
	 * @param nom    le nom de la personne sous forme de String
	 * @param numero le numero de telephone de la personne sous forme de String
	 */
	public void addPersonne(String prenom, String nom, String numero) {
		repertoire.add(new Personne(prenom, nom, numero));
	}

	/**
	 * Recherche le numero de telephone d'une personne identifiee par son prenom et
	 * son nom. Le numero est renvoye sous forme de String. Dans le cas ou aucune
	 * personne ne correspond, le String retourne correspond a un message d'erreur.
	 * 
	 * @param prenom le prenom de la personne recherche
	 * @param nom    le nom de la personne recherchee
	 * @return le numero de telephone sous forme de String, ou un message d'erreur
	 */
	public String chercheNumero(String prenom, String nom) {
		for(Personne p : repertoire) {
			if(prenom.equals(p.getPrenom()) && nom.equals(p.getNom())) {
				return p.getNumero();
			}
		}
		return "L'identite " + prenom + " " + nom + " est inconnue.";
	}
	
	/**
	 * Permet d'obtenir le contenu du repertoire
	 * @return le repertoire
	 */
	public List<Personne> getRepertoire(){
		return repertoire ;
	}

}
