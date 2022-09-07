package ex03Examen;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Personnel {
	private List<Employe> employes;
	
	public Personnel() {
		employes = new ArrayList<Employe>();
	}
	
	public void ajouter(Employe e) {
		if (!employes.contains(e)) {
			employes.add(e);
		}
	}
	
	public void trier() {
		boolean permute = true;
		while(permute) {
			permute = false;
			for (int i = 0; i<(employes.size()-1);i++) {
				if(employes.get(i).isPlusGrand(employes.get(i+1))) {
					Employe temp = employes.get(i);
					employes.set(i, employes.get(i+1));
					employes.set(i+1, temp);
					permute = true;
				}
			}
		}
	}
	
	public void afficher() {
		Iterator<Employe> it = employes.iterator();
		System.out.println("Affichage de la liste des employés : ");
		while (it.hasNext()) {
			it.next().afficher();
		}
		System.out.println("FIN");
	}
}
