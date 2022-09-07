package TD07;

import java.util.Vector;

public class Bibliotheque {
	//Attributs
	private Vector<Document> documents;
	
	//Constructeurs
	public Bibliotheque() {
		documents = new Vector<Document>();
	}
	
	//Affichage de tous les documents de la bibliothèque
	public void afficherDocuments() {
		for (int i=0; i<documents.size(); i++) {
			System.out.println(documents.get(i).toString());
		}
	}
	
	/*Afficher tous les auteurs de la bibli
	Attention, seuls les documents de type Livre et Roman possedent des auteurs */
	public void afficherAuteurs() {
		for (int i = 0; i<documents.size(); i++) {
			//On recup et on affiche l'auteur seulement si documents.size()>i
			if(documents.get(i) instanceof Livre) {
				Livre l = (Livre)documents.get(i);
				//System.out.println((Livre)documents.get(i).getAuteur());
				System.out.println(l.getAuteur());
			}
		}
	}
	
	public boolean supprimer(Document doc) {
		for (int i = 0; i<documents.size(); i++) {
			if (documents.get(i).equals(doc)) {
				documents.remove(doc);
				return true;
			}
		}
		return false;
	}
	
	//Ajouter un doc
	public boolean ajouter(Document doc) {
		return documents.add(doc);
	}
	
	//Obtenir le doc à l'indice i
	public Document getDocument(int i) {
		return documents.get(i);
	}
}
