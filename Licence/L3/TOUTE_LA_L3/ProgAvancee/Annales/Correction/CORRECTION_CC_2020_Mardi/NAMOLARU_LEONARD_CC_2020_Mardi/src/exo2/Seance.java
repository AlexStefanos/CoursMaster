package exo2;

public class Seance {
	private String nomFilm;
	private Salle salle;
	private int horaire;
	private int placesVendues;
	
	public Seance(String nomFilm, Salle salle, int horaire) {
		this.nomFilm = nomFilm;
		this.salle = salle;
		this.horaire = horaire;
		placesVendues = 0;
	}
	
	public boolean placeVendue() {
		if( placesVendues < salle.getMaxSpectateurs() ) {
			placesVendues++;
			return true;
		}
		
		return false;
	}

	public String getNomFilm() {
		return nomFilm;
	}

	public void setNomFilm(String nomFilm) {
		this.nomFilm = nomFilm;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public int getHoraire() {
		return horaire;
	}

	public void setHoraire(int horaire) {
		this.horaire = horaire;
	}

	public int getPlacesVendues() {
		return placesVendues;
	}

	public void setPlacesVendues(int placesVendues) {
		this.placesVendues = placesVendues;
	}

	@Override
	public String toString() {
		return "Seance [nomFilm=" + nomFilm + ", salle=" + salle + ", horaire=" + horaire + ", placesVendues=" + placesVendues + "]";
	}

}
