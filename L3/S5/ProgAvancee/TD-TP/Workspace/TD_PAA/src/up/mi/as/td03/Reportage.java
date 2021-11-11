package up.mi.as.td03;

public class Reportage extends Programme {
	private Theme theme;
	private String nom;
	
	public Reportage(int heureDebut, int heureFin, Theme theme, String nom) {
		super(heureDebut, heureFin);
		this.theme = theme;
		this.nom = nom;
	}

	public Theme getTheme() {
		return(theme);
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public String getNom() {
		return(nom);
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
