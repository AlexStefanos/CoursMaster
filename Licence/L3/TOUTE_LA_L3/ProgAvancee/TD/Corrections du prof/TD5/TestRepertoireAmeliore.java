package up.mi.jgm.td05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import up.mi.jgm.td02.repertoire.Personne;
import up.mi.jgm.td03.repertoire.RepertoireAmeliore;

class TestRepertoireAmeliore {
	private RepertoireAmeliore rep;

	@BeforeEach
	void beforeEach() {
		rep = new RepertoireAmeliore(new Personne("Jean-Guy", "Mailly", "0123456789"));
	}

	@ParameterizedTest
	@CsvSource({ "'John,Lennon,0123456789,Paul,McCartney,0912345678',0912345678,'Paul McCartney'",
			"'John,Lennon,0123456789,Paul,McCartney,0912345678',0123456789,'John Lennon'",
			"'Freddie,Mercury,0123456789,Brian,May,0912345678,Roger,Taylor,0812345679,John,Deacon,0712345689',0123456789,'Freddie Mercury'",
			"'Freddie,Mercury,0123456789,Brian,May,0912345678,Roger,Taylor,0812345679,John,Deacon,0712345689',0912345678,'Brian May'",
			"'Freddie,Mercury,0123456789,Brian,May,0912345678,Roger,Taylor,0812345679,John,Deacon,0712345689',0812345679,'Roger Taylor'",
			"'Freddie,Mercury,0123456789,Brian,May,0912345678,Roger,Taylor,0812345679,John,Deacon,0712345689',0712345689,'John Deacon'" })
	void testChercherPersonne(String repPersonnes, String num, String personne) {
		String[] tab = repPersonnes.split(",");
		if ((tab.length % 3) != 0) {
			throw new IllegalArgumentException("La chaine est mal formee : " + repPersonnes);
		}
		for (int i = 0; i < tab.length / 3; i++) {
			rep.addPersonne(tab[3 * i], tab[3 * i + 1], tab[3 * i + 2]);
		}
		assertEquals(personne, rep.cherchePersonne(num));
	}

}
