package up.mi.jgm.td02.cartes.ameliore;

import java.util.LinkedList;
import java.util.List;

import up.mi.jgm.td02.UtilListes;

/**
 * Main du joueur qui peut etre triee selon l'ordre naturel des cartes
 * @author Jean-Guy Mailly
 *
 */
public class MainBasique{
	/**
	 * La liste des cartes qui composent la main
	 */
	private List<CarteBasique> cartes;
	
	public MainBasique() {
		cartes = new LinkedList<CarteBasique>();
	}
	
	/**
	 * Permet l'ajout d'une carte dans la main
	 * 
	 * @param c la carte qui doit etre ajoutee
	 */
	public void add(CarteBasique c) {
		cartes.add(c);
	}

	/**
	 * Permet la suppression d'une carte de la main
	 * 
	 * @param c la carte qui doit etre supprimee
	 */
	public void remove(CarteBasique c) {
		if (cartes.contains(c)) {
			cartes.remove(c);
		}
	}
	
	/**
	 * Permet de trier la main du joueur
	 * 
	 * @return la main triee
	 */
	public List<CarteBasique> tri() {
		return UtilListes.tri(cartes);
	}
	
	@Override
	public String toString() {
		return cartes.toString();
	}
}
