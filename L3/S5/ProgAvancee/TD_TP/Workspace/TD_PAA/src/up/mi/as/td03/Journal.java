package up.mi.as.td03;

public class Journal extends Programme {
	private String presentateur;
	
	public Journal(int heureDebut, int heureFin, String presentateur) {
		super(heureDebut, heureFin);
		this.presentateur = presentateur;
	}

	public String getPresentateur() {
		return(presentateur);
	}

	public void setPresentateur(String presentateur) {
		this.presentateur = presentateur;
	}
}
