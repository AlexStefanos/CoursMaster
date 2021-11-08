package up.mi.jgm.td05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import up.mi.jgm.td03.dicot9.DicoT9;

class TestDico {
	private DicoT9 dico;
	private Map<String, ArrayList<String>> map;
	private ArrayList<String> liste;

	@BeforeEach
	void beforeAll() {
		dico = new DicoT9();
		dico.enregistrer("bonjour");
		dico.enregistrer("bonne");
		dico.enregistrer("comme");
		map = new HashMap<String, ArrayList<String>>();
		map.put("2665687", new ArrayList<String>());
		map.get("2665687").add("bonjour");
		map.put("26663", new ArrayList<String>());
		map.get("26663").add("bonne");
		map.get("26663").add("comme");
		liste = new ArrayList<String>();
	}

	@Test
	void testEnregistrer_01() {
		assertEquals(map, dico.getMap());
	}

	@Test
	void testEnregistrer_02() {
		dico.enregistrer("toto");
		map.put("8686", new ArrayList<String>());
		map.get("8686").add("toto");

		assertEquals(map, dico.getMap());
	}

	@Test
	void testEnregistrer_03() {
		dico.enregistrer("toto");
		dico.enregistrer("vovo");
		map.put("8686", new ArrayList<String>());
		map.get("8686").add("toto");
		map.get("8686").add("vovo");

		assertEquals(map, dico.getMap());
	}

	@Test
	void testEnregistrer_04() {
		dico.enregistrer("toto");
		dico.enregistrer("vovo");
		dico.enregistrer("paris");
		map.put("8686", new ArrayList<String>());
		map.get("8686").add("toto");
		map.get("8686").add("vovo");
		map.put("72747", new ArrayList<String>());
		map.get("72747").add("paris");

		assertEquals(map, dico.getMap());
	}

	@Test
	void testRecuperer_01() {
		liste.add("bonne");
		liste.add("comme");
		assertEquals(liste, dico.recuperer("26663"));
	}

	@Test
	void testRecuperer_02() {
		liste.add("bonjour");
		assertEquals(liste, dico.recuperer("2665687"));
	}

	@Test
	void testRecuperer_03() {
		liste.add("toto");
		liste.add("vovo");
		dico.enregistrer("toto");
		dico.enregistrer("vovo");
		assertEquals(liste, dico.recuperer("8686"));
	}

	@Test
	void testRecuperer_04() {
		liste.add("paris");
		dico.enregistrer("paris");
		assertEquals(liste, dico.recuperer("72747"));
	}

	@Test
	void testRecupererAbsent() {
		assertEquals(liste, dico.recuperer("42"));
	}

	@ParameterizedTest
	@CsvSource({ "'bonne,comme',26663,'bonne,comme'", "'bonne,comme,paris',26663,'bonne,comme'",
			"'bonne,comme,toto,vovo',8686,'toto,vovo'", "'bonne,comme,toto,paris,vovo',8686,'toto,vovo'",
			"'bonne,comme,toto,paris,vovo',72747,paris"})
	void testRecupererParam(String enreg, String t9, String result) {
		DicoT9 dico = new DicoT9();
		ArrayList<String> liste = stringToList(enreg);
		for (String str : liste) {
			dico.enregistrer(str);
		}
		assertEquals(stringToList(result), dico.recuperer(t9));
	}

	/**
	 * Cette methode retourne une ArrayList de String correspondant a un objet
	 * String, en utilisant la virgule comme separateur.
	 * 
	 * Attention : je considere ici que la methode est correcte en raison de sa
	 * simplicite. Idealement, elle devrait egalement etre testee...
	 * 
	 * @param str la chaine a decouper
	 * @return l'ArrayList qui correspond a str
	 */
	private ArrayList<String> stringToList(String str) {
		ArrayList<String> liste = new ArrayList<String>();
		String[] tab = str.split(",");
		for (String s : tab)
			liste.add(s);
		return liste;
	}

}
