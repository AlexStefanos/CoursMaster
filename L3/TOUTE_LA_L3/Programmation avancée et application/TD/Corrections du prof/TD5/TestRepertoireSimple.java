package up.mi.jgm.td05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import up.mi.jgm.td02.repertoire.RepertoireSimple;

class TestRepertoireSimple {
	private RepertoireSimple rep;

	@BeforeEach
	void beforeEach() {
		rep = new RepertoireSimple();
	}

	@ParameterizedTest
	@CsvSource({ "'John,Lennon,0123456789,Paul,McCartney,0912345678','Paul,McCartney',0912345678",
			"'John,Lennon,0123456789,Paul,McCartney,0912345678','John,Lennon',0123456789",
			"'Freddie,Mercury,0123456789,Brian,May,0912345678,Roger,Taylor,0812345679,John,Deacon,0712345689','Freddie,Mercury',0123456789",
			"'Freddie,Mercury,0123456789,Brian,May,0912345678,Roger,Taylor,0812345679,John,Deacon,0712345689','Brian,May',0912345678",
			"'Freddie,Mercury,0123456789,Brian,May,0912345678,Roger,Taylor,0812345679,John,Deacon,0712345689','Roger,Taylor',0812345679",
			"'Freddie,Mercury,0123456789,Brian,May,0912345678,Roger,Taylor,0812345679,John,Deacon,0712345689','John,Deacon',0712345689" })
	void testChercheNumero(String repPersonnes, String personne, String num) {
		String[] tab = repPersonnes.split(",");
		if ((tab.length % 3) != 0) {
			throw new IllegalArgumentException("La chaine est mal formee : " + repPersonnes);
		}
		for (int i = 0; i < tab.length / 3; i++) {
			rep.addPersonne(tab[3 * i], tab[3 * i + 1], tab[3 * i + 2]);
		}

		tab = personne.split(",");
		if (tab.length != 2) {
			throw new IllegalArgumentException("Le chaine est mal formee : " + personne);
		}
		assertEquals(num, rep.chercheNumero(tab[0], tab[1]));
	}

}