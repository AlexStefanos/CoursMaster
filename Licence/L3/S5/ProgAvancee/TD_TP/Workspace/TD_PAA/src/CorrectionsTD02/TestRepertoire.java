package CorrectionsTD02;

public class TestRepertoire {

	public static void main(String[] args) {
		RepertoireSimple rep = new RepertoireSimple();
		rep.addPersonne("John", "Lennon", "0123456789");
		rep.addPersonne("Paul", "McCartney", "0234567891");
		rep.addPersonne("George", "Harrison", "0345678912");
		rep.addPersonne("Ringo", "Starr", "0456789123");
		
		System.out.println(rep.chercheNumero("John", "Lennon"));
		System.out.println(rep.chercheNumero("Paul", "McCartney"));
		System.out.println(rep.chercheNumero("Freddie", "Mercury"));
	}

}
