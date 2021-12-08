package up.mi.jgm.td03.television;

public class Reportage extends Programme {
	private Theme theme;
	private String nom;

	public Reportage(int hD, int hF, Theme theme, String nom) {
		super(hD, hF);
		this.theme = theme;
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom + " reportage sur " + theme + ", de " + getHeureDebut() + "h00 a " + getHeureFin() + "h00.";
	}
}
