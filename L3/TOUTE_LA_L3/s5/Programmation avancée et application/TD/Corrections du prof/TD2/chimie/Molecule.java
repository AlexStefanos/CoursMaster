package up.mi.jgm.td02.chimie;

import java.util.HashMap;

public class Molecule extends HashMap<Atome, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addAtome(Atome at, int quantite) {
		int nbAtome = 0;
		if (keySet().contains(at)) {
			nbAtome = get(at);
		}
		put(at, nbAtome + quantite);
	}

	public void addAtome(Atome at) {
		addAtome(at, 1);
	}
	
	public String toString() {
		StringBuilder build = new StringBuilder();
		for(Atome at : keySet()) {
			build.append(at.getSymbole());
			build.append(get(at));
		}
		return build.toString();
	}

	public int getNbProtons() {
		int nbProtons = 0 ;
		for(Atome at : keySet()) {
			nbProtons += at.getNbProtons() * get(at);
		}
		return nbProtons;
	}

}
