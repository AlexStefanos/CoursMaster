package exo2;

public class Voyage {
	
	private String titre;
	private String dateDebut;
	private String dateFin;
	private int prix;
	private int prixClient;
	private Hebergement typeHebergement;
	private Transport typeTransport;
	private Formule typeFormule;
	private int nombreMaximum;
	
	public Voyage(String titre, String dateDebut, String dateFin, int prix, int prixClient, Hebergement typeHebergement, Transport typeTransport, Formule typeFormule, int nombreMaximum) {
		this.titre = titre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.prix = prix;
		this.prixClient = prixClient;
		this.typeHebergement = typeHebergement;
		this.typeTransport = typeTransport;
		this.typeFormule = typeFormule;
		this.nombreMaximum = nombreMaximum;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getPrixClient() {
		return prixClient;
	}

	public void setPrixClient(int prixClient) {
		this.prixClient = prixClient;
	}

	public Hebergement getTypeHebergement() {
		return typeHebergement;
	}

	public void setTypeHebergement(Hebergement typeHebergement) {
		this.typeHebergement = typeHebergement;
	}

	public Transport getTypeTransport() {
		return typeTransport;
	}

	public void setTypeTransport(Transport typeTransport) {
		this.typeTransport = typeTransport;
	}

	public Formule getTypeFormule() {
		return typeFormule;
	}

	public void setTypeFormule(Formule typeFormule) {
		this.typeFormule = typeFormule;
	}

	public int getNombreMaximum() {
		return nombreMaximum;
	}

	public void setNombreMaximum(int nombreMaximum) {
		this.nombreMaximum = nombreMaximum;
	}
	
	@Override
	public String toString() {
		return "Voyage [titre=" + titre + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", prix=" + prix
				+ ", prixClient=" + prixClient + ", typeHebergement=" + typeHebergement + ", typeTransport="
				+ typeTransport + ", typeFormule=" + typeFormule + ", nombreMaximum=" + nombreMaximum + "]";
	}
	
}

