package up.mi.jgm.td03.repertoire;

import up.mi.jgm.td02.repertoire.Personne;

public class TestRepertoireAmeliore {

	public static void main(String[] args) {
		RepertoireAmeliore rep = new RepertoireAmeliore(new Personne("Jimi", "Hendrix", "0987654321"));
		rep.addPersonne("John", "Lennon", "0123456789");
		rep.addPersonne("Paul", "McCartney", "0234567891");
		rep.addPersonne("George", "Harrison", "0345678912");
		rep.addPersonne("Ringo", "Starr", "0456789123");
		rep.addPersonne("Sean", "Lennon", "0567891234");

		System.out.println(rep.chercheNumero("John", "Lennon"));
		System.out.println(rep.chercheNumero("Freddie", "Mercury"));
		System.out.println(rep.cherchePersonne("0234567891"));
		System.out.println(rep.cherchePersonne("0234567899"));
		System.out.println(rep);

	}

}
