package up.mi.jgm.td03.repertoire;

import java.util.List;

import up.mi.jgm.td02.UtilListes;
import up.mi.jgm.td02.repertoire.Personne;
import up.mi.jgm.td02.repertoire.RepertoireSimple;

/**
 * Modelise un repertoire ameliore, c'est a dire un repertoire qui possede un
 * proprietaire, qui permet de retrouver une personne en fonction de son numero
 * de telephone, et d'afficher le contenu du repertoire trie
 * 
 * Cette classe necessite de legeres modifications de la classe Personne du TD2
 * 
 * @author Jean-Guy Mailly
 *
 */
public class RepertoireAmeliore extends RepertoireSimple {
	/**
	 * Le proprietaire du repertoire
	 */
	private Personne proprietaire;

	/**
	 * Construit un repertoire avec son proprietaire
	 * 
	 * @param proprio le proprietaire du repertoire
	 */
	public RepertoireAmeliore(Personne proprio) {
		proprietaire = proprio;
	}

	/**
	 * Permet d'obtenir une personne en fonction de son numero de telephone
	 * 
	 * @param numero le numero recherche
	 * @return le prenom et le nom de la personne sous forme de String, ou un
	 *         message d'erreur si numero n'est pas dans le repertoire
	 */
	public String cherchePersonne(String numero) {
		for (Personne p : getRepertoire()) {
			if (p.getNumero().equals(numero)) {
				return p.getPrenom() + " " + p.getNom();
			}
		}
		return "Le numero " + numero + " n'appartient a personne.";
	}

	@Override
	public String toString() {
		StringBuilder build = new StringBuilder("Proprietaire : ");
		build.append(proprietaire.toString());
		build.append("\n");
		List<Personne> repTrie = UtilListes.tri(this.getRepertoire());
		for (Personne p : repTrie) {
			build.append(p.toString());
			build.append("\n");
		}
		return build.toString();
	}
	
	/**
	 * Permet d'obtenir le proprietaire du repertoire
	 * @return le proprietaire
	 */
	public Personne getProprietaire() {
		return proprietaire;
	}
}
