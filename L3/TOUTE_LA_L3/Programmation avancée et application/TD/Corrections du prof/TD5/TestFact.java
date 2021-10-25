package up.mi.jgm.td05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import up.mi.jgm.td04.utilmaths.UtilMaths;

class TestFact {

	@Test
	void testFact_00() {
		assertEquals(1, UtilMaths.fact(0));
	}
	
	@Test
	void testFact_01() {
		assertEquals(1, UtilMaths.fact(1));
	}

	@Test
	void testFact_02() {
		assertEquals(2, UtilMaths.fact(2));
	}

	@Test
	void testFact_03() {
		assertEquals(6, UtilMaths.fact(3));
	}

	@Test
	void testFact_04() {
		assertEquals(24, UtilMaths.fact(4));
	}

	@Test
	void testFact_05() {
		assertEquals(3628800, UtilMaths.fact(10));
	}

	@Test
	void testNegative_01() {
		assertThrows(IllegalArgumentException.class, () -> {
			UtilMaths.fact(-1);
		});
	}
	
	@Test
	void testNegative_02() {
		assertThrows(IllegalArgumentException.class, () -> {
			UtilMaths.fact(-2);
		});
	}

	@Test
	void testNegative_03() {
		assertThrows(IllegalArgumentException.class, () -> {
			UtilMaths.fact(-737);
		});
	}
	
}
