package up.mi.jgm.td03.produits;

import java.util.ArrayList;
import java.util.List;

public class TestProduits {

	public static void main(String[] args) {
		List<Produit> catalogue = new ArrayList<Produit>();
		catalogue.add(new DVD("Star Wars", "George Lucas", 16.66));
		catalogue.add(new Fruit("Pomme","France",1));
		catalogue.add(new Livre("Le Seigneur des Anneaux","J.R.R. Tolkien","ISBN",12.5));
		catalogue.add(new Medicament("Doliprane", "labo", 16, 2.5));
		
		System.out.println(catalogue);
	}

}
