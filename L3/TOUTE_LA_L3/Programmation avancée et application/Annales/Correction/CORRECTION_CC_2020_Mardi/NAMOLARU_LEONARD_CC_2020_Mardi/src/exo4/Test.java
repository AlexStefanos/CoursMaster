package exo4;

public class Test {

	public static void main(String[] args) {
		MaListe liste = new MaListe();

		for (int i = 0; i < 100; i++) {
			liste.ajouter(2 * i);
		}

		if (liste.estPresent(10)) {
			System.out.println("10 est dans la liste.");
		} else {
			System.out.println("10 n'est pas dans la liste.");
		}

		if (liste.estPresent(11)) {
			System.out.println("11 est dans la liste.");
		} else {
			System.out.println("11 n'est pas dans la liste.");
		}

		int indice = liste.indice(20);
		if (indice != -1) {
			System.out.println("L'indice de 20 est " + indice + ".");
		} else {
			System.out.println("20 n'est pas dans la liste.");
		}
		
		indice = liste.indice(31);
		if (indice != -1) {
			System.out.println("L'indice de 31 est " + indice + ".");
		} else {
			System.out.println("31 n'est pas dans la liste.");
		}
		
		System.out.print("Affichage de la liste : ");
		for(int i = 0 ; i < 99 ; i++) {
			System.out.print(liste.nombre(i) + ", ");
		}
		System.out.println(liste.nombre(99));
	}

}
