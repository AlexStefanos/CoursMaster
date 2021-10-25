package up.mi.jgm.td03.dicot9;

public class TestDicoT9 {
	public static void main(String[] args) {
		DicoT9 dico = new DicoT9();
		dico.enregistrer("bonjour");
		dico.enregistrer("bonne");
		dico.enregistrer("comme");

		System.out.println(dico.recuperer("26663"));
		System.out.println(dico.recuperer("2665687"));
		System.out.println(dico.recuperer("123456"));
	}
}
