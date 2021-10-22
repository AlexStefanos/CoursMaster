package up.mi.jgm.td02.cartes.simple;

import java.util.LinkedList;
import java.util.List;

import up.mi.jgm.td02.UtilListes;

/**
 * Permet de representer la main d'un joueur comme une liste de cartes.
 * 
 * @author Jean-Guy Mailly
 *
 */
public class MainSimple {
	/**
	 * La liste des cartes qui composent la main
	 */
	private List<Carte> cartes;

	public MainSimple() {
		cartes = new LinkedList<Carte>();
	}

	/**
	 * Permet l'ajout d'une carte dans la main
	 * 
	 * @param c la carte qui doit etre ajoutee
	 */
	public void add(Carte c) {
		cartes.add(c);
	}

	/**
	 * Permet la suppression d'une carte de la main
	 * 
	 * @param c la carte qui doit etre supprimee
	 */
	public void remove(Carte c) {
		if (cartes.contains(c)) {
			cartes.remove(c);
		}
	}

	/**
	 * Permet de trier la main du joueur
	 * 
	 * @return la main triee
	 */
	public List<Carte> tri() {
		return UtilListes.tri(cartes);
	}
	
	@Override
	public String toString() {
		return cartes.toString();
	}

}
