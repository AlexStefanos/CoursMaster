package up.mi.as.td03;

public class Fiction extends Programme {
	private String nom, real;
	private boolean rediff;
	
	public Fiction(int heureDebut, int heureFin, String nom, String real, boolean rediff) {
		super(heureDebut, heureFin);
		this.nom = nom;
		this.real = real;
		this.rediff = rediff;
	}

	public boolean isRediff() {
		return(rediff);
	}

	public void setRediff(boolean rediff) {
		this.rediff = rediff;
	}

	public String getNom() {
		return(nom);
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getReal() {
		return(real);
	}

	public void setReal(String real) {
		this.real = real;
	}
}
