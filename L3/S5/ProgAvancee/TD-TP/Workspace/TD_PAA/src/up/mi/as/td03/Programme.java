package up.mi.as.td03;

public abstract class Programme {
	private int heureDebut, heureFin;
	
	public Programme(int heureDebut, int heureFin) {
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}

	public int getHeureFin() {
		return(heureFin);
	}

	public void setHeureFin(int heureFin) {
		this.heureFin = heureFin;
	}

	public int getHeureDebut() {
		return(heureDebut);
	}

	public void setHeureDebut(int heureDebut) {
		this.heureDebut = heureDebut;
	}
}
