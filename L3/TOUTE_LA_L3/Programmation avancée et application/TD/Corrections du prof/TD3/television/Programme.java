package up.mi.jgm.td03.television;

/**
 * Represente un programme televise quelconque
 * 
 * @author Jean-Guy Mailly
 *
 */
public abstract class Programme implements Comparable<Programme>{
	/**
	 * L'heure de debut du programme
	 */
	private int heureDebut;

	/**
	 * L'heure de fin du programme
	 */
	private int heureFin;

	/**
	 * Cree un programme en fonction de ses heures de debut et de fin
	 * 
	 * @param hD l'heure de debut
	 * @param hF l'heure de fin
	 */
	public Programme(int hD, int hF) {
		heureDebut = hD;
		heureFin = hF;
	}

	/**
	 * Permet d'obtenir l'heure de debut du programme
	 * 
	 * @return l'heure de debut
	 */
	public int getHeureDebut() {
		return heureDebut;
	}

	/**
	 * Permet d'obtenir l'heure de fin du programme
	 * 
	 * @return l'heure de fin
	 */
	public int getHeureFin() {
		return heureFin;
	}
	
	@Override
	public int compareTo(Programme p) {
		return this.getHeureDebut() - p.getHeureDebut();
	}
}
