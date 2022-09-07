package exo2;

import java.util.List;
import java.util.ArrayList;
public class Cinema {
	private List<Salle> salles;
	private List<Seance> seances;
	
	
	/**
	 * Cree une cinema avec un nombre de salles fixe
	 * 
	 * @param nbSalles le nombre de salles du cinema
	 */
	public Cinema(int nbSalles) {
		salles = new ArrayList<Salle>( nbSalles );
		seances = new ArrayList<Seance>();
	}

	/** 
	 * Creee une seance pour un film, dans une salle precise, a une heure precise
	 * 
	 * @param titre    le titre du film
	 * @param heure    l'heure de la seance
	 * @param numSalle le numero de la salle, entre 1 et le nombre de salle du
	 *                 cinema
	 * @return true si et seulement si la creation de la seance est possible
	 */
	public boolean ajouterSeance(String titre, int heure, int numSalle) {
		for(Seance seance : seances) {
			if(seance.getSalle().getNumero() == numSalle) {
				if(!( ( heure >= (seance.getHoraire() + 2) ) || ( (heure + 2) <= seance.getHoraire() ) ))
					return false;
			}
		}
		
		Salle laSalle = null;
		for(Salle salle : salles) {
			if(salle.getNumero() == numSalle)
				laSalle = salle;
		}
		
		if(laSalle != null)
			seances.add( new Seance(titre, laSalle, heure) );
		else
			return false;
		
		return true;	
	}

	/**
	 * Permet d'indiquer qu'une salle diffuse des films en 3D
	 * 
	 * @param numSalle le numero de la salle
	 */
	public void salle3D(int numSalle) {
		for(Salle salle : salles) {
			if(salle.getNumero() == numSalle) {
				salle.setFilms3d(true);
				return;
			}
		}
	}

	/**
	 * Permet de specifier le nombre maximal de spectateurs dans une salle
	 * 
	 * @param numSalle le numero de la salle
	 * @param capacite le nombre maximal de spectateurs
	 */
	public void capaciteSalle(int numSalle, int capacite) {
		for(Salle salle : salles) {
			if(salle.getNumero() == numSalle) {
				salle.setMaxSpectateurs(capacite);
				return;
			}
		}
	}

	/**
	 * Permet de vendre une place pour un film, à une certaine seance. Si deux
	 * seances diffusent le meme film en meme temps, alors c'est la salle la moins
	 * pleine qui est choisie.
	 * 
	 * @param film  le film
	 * @param heure l'heure
	 * @return vrai si et seulement si la vente de la place est possible
	 */
	public boolean vendrePlace(String film, int heure) {
		List<Seance> seancesDiffusentFilm = new ArrayList<Seance>();
		
		for(Seance seance : seances) {
			if( (seance.getNomFilm().equals(film)) && (seance.getHoraire() == heure) ) 
				seancesDiffusentFilm.add(seance);
		}
		
		Seance MoinsPleine = (seancesDiffusentFilm.size() > 0) ? seancesDiffusentFilm.get(0) : null;
		for(Seance seance : seancesDiffusentFilm) {
			if( (seance.getPlacesVendues() /  seance.getSalle().getMaxSpectateurs()) < (MoinsPleine.getPlacesVendues() /  MoinsPleine.getSalle().getMaxSpectateurs()) )
				MoinsPleine = seance;
		}
		
		if( MoinsPleine != null) {
			if( MoinsPleine.placeVendue() )
				return true;
		}
		return false;
	}

	/**
	 * Calcule le taux de remplissage moyen du cinema sur l'ensemble des seances.
	 * 
	 * @return le taux de remplissage moyen
	 */
	public double tauxRemplissage() {
		int sum = 0;
		for(Seance seance : seances) 
			sum += (seance.getPlacesVendues() /  seance.getSalle().getMaxSpectateurs());
			
		return ((seances.size() > 0) ? ((double) sum / (double) seances.size()) : 0);		

	}

	/**
	 * Calcule le chiffre d'affaire du cinema sur l'ensemble des seances.
	 * 
	 * @return le chiffre d'affaire
	 */
	public double chiffreAffaire() {
		double sum = 0;
		for(Seance seance : seances) 
			sum += (seance.getSalle().isFilms3d()) ?  seance.getPlacesVendues() * 12 :  seance.getPlacesVendues() * 10;
			
		return sum;		
	}

}
