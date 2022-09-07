package exo2;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
public class Tour {
	
	private String adresse;
	private String dateCreation;
	private List<Touriste> clients;
	private List<Voyage> prestationVoyages;
	private Map< Touriste, ArrayList<Voyage> > voyagesClient;
	
	public Tour(String adresse, String dateCreation,  ArrayList<Touriste> clients, ArrayList<Voyage> prestationVoyages) {
		this.adresse = adresse;
		this.dateCreation = dateCreation;		
		this.clients = clients;
		this.prestationVoyages = prestationVoyages;
		
		voyagesClient = new HashMap< Touriste, ArrayList<Voyage> >();
	}
	
	public void ajouterVoyageur(Touriste touriste, Voyage voyage) {
		if( !voyagesClient.containsKey(touriste) )
			voyagesClient.put(touriste, new ArrayList<Voyage>());
		
		voyagesClient.get(touriste).add(voyage);
	}

	private int obtenirVoyagesAnneeTouriste(Touriste touriste, String annee) {
		int count = 0;
		
		ArrayList<Voyage> voyagesTouriste = voyagesClient.get( touriste );			
		for(int i = 0; i < voyagesTouriste.size(); i++) {
				if( voyagesTouriste.get(i).getDateDebut().contains(annee) || voyagesTouriste.get(i).getDateFin().contains(annee)  )
					count++;
		}
		
		return count;
	}
	
	private int obtenirPrixAnneeTouriste(Touriste touriste, String annee) {
		int sum = 0;
		
		ArrayList<Voyage> voyagesTouriste = voyagesClient.get( touriste );			
		for(int i = 0; i < voyagesTouriste.size(); i++) {
				if( voyagesTouriste.get(i).getDateDebut().contains(annee) || voyagesTouriste.get(i).getDateFin().contains(annee)  )
					sum +=  voyagesTouriste.get(i).getPrixClient();
		}
		
		return sum;
	}
	
	private int obtenirIndexMax(List<Integer> l) {
		Integer max = l.get(0);
		int indexMax = 0;
		for(int i = 0 ; i < l.size(); i++) {
			if( l.get(i).compareTo( max ) > 0 ) {
				max = l.get(i);
				indexMax = i;
			}
		}

		return indexMax;
	}

	// Question a
	public Touriste obtenirGrandVoyageur(String annee) {
		List<Integer> numVoyagesAnneeParClient = new ArrayList<Integer>( clients.size() );
				
		for(int i = 0 ; i < clients.size(); i++)
			numVoyagesAnneeParClient.add( i, obtenirVoyagesAnneeTouriste(clients.get(i), annee) );	
				
		return clients.get( obtenirIndexMax(numVoyagesAnneeParClient) );		
	}

	// Question b
	public int obtenirNombreClientParVoyage(Voyage voyage) {
		int count = 0;
		
		for(int i = 0 ; i < clients.size(); i++) {
			ArrayList<Voyage> voyagesClientI = voyagesClient.get( clients.get(i) );
			
			for(int j = 0; j < voyagesClientI.size(); j++) {
				if( voyagesClientI.get(j).equals( voyage ) ) 
					count++;				
			}
		}
		
		return count;
	}

	// Question c
	public Touriste obtenirMeilleurClient(String annee) {
		List<Integer> prixVoyagesAnneeParClient = new ArrayList<Integer>( clients.size() );
		
		for(int i = 0 ; i < clients.size(); i++)
			prixVoyagesAnneeParClient.add( i, obtenirPrixAnneeTouriste(clients.get(i), annee) );	
				
		return clients.get( obtenirIndexMax(prixVoyagesAnneeParClient) );		
	}
	
	
	// Question d
	public String obtenirListeMailARelancerFrequence(String anneeN, String anneeN_1) {
		StringBuilder result = new StringBuilder("");
		for(int i = 0 ; i < clients.size(); i++) {
			if( obtenirVoyagesAnneeTouriste(clients.get(i), anneeN) - obtenirVoyagesAnneeTouriste(clients.get(i), anneeN_1) < 0)
				result.append( clients.get(i).getMail() + " \n");
		}
		
		return result.toString();
	}
	
	// Question e
	public String obtenirListeMailARelancerPrix(String anneeN, String anneeN_1) {
		StringBuilder result = new StringBuilder("");
		for(int i = 0 ; i < clients.size(); i++) {
			if( obtenirPrixAnneeTouriste(clients.get(i), anneeN) - obtenirPrixAnneeTouriste(clients.get(i), anneeN_1) < 0)
				result.append( clients.get(i).getMail() + " \n");
		}
		
		return result.toString();
	}
	
	// Question f
	public Voyage obtenirVoyageAVendreParPeriode(String mois) {
		String moisDansDate = "-" + mois + "-"; // Exemple dateDebut : "2021-01-01"

		for(int i = 0 ; i <  prestationVoyages.size() ; i++) {
			if( obtenirNombreClientParVoyage( prestationVoyages.get(i) ) - prestationVoyages.get(i).getNombreMaximum() < 0  ) {
				if( prestationVoyages.get(i).getDateDebut().contains(moisDansDate) || prestationVoyages.get(i).getDateFin().contains(moisDansDate) )
					return prestationVoyages.get(i);
			}					
		}
		
		return null;
	}

	
	
	
}