package up.mi.jgm.td03.television;

/**
 * Represente une fiction diffusee a la television
 * 
 * @author Jean-Guy Mailly
 *
 */
public class Fiction extends Programme {
	/**
	 * Le titre du film
	 */
	private String titre;

	/**
	 * Le nom du realisateur
	 */
	private String realisateur;

	/**
	 * Indique s'il s'agit d'une rediffusion ou non
	 */
	private boolean redif;

	/**
	 * Construit une fiction
	 * 
	 * @param hD          l'heure de debut
	 * @param hF          l'heure de fin
	 * @param titre       le titre de la fiction
	 * @param realisateur le nomdu realisateur
	 * @param redif       indique s'il s'agit d'une rediffusion
	 */
	public Fiction(int hD, int hF, String titre, String realisateur, boolean redif) {
		super(hD, hF);
		this.titre = titre;
		this.realisateur = realisateur;
		this.redif = redif;
	}

	@Override
	public String toString() {
		StringBuilder build = new StringBuilder(titre);
		build.append(" realise par ");
		build.append(realisateur + ",");
		build.append("de ");
		build.append(getHeureDebut());
		build.append("h00 a ");
		build.append(getHeureFin());
		build.append("h00 : ");
		if (redif)
			build.append("rediffusion.");
		else
			build.append("inedit.");
		return build.toString();
	}
}
