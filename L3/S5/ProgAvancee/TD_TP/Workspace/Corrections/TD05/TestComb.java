package up.mi.jgm.td05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import up.mi.jgm.td04.utilmaths.UtilMaths;

class TestComb {

	@Test
	void testComb_01() {
		assertEquals(3, UtilMaths.comb(3, 2));
	}

	@Test
	void testComb_02() {
		assertEquals(10, UtilMaths.comb(5, 2));
	}

	@Test
	void testComb_03() {
		assertEquals(1716, UtilMaths.comb(13, 6));
	}

	@Test
	void testComb_04() {
		assertEquals(455, UtilMaths.comb(15, 12));
	}

	@Test
	void testComb_05() {
		assertEquals(20, UtilMaths.comb(20, 19));
	}

	@Test
	void testNeg_01() {
		assertThrows(IllegalArgumentException.class, () -> {
			UtilMaths.comb(20, -1);
		});
	}
	@Test
	void testNeg_02() {
		assertThrows(IllegalArgumentException.class, () -> {
			UtilMaths.comb(1, -3);
		});
	}
	@Test
	void testNeg_03() {
		assertThrows(IllegalArgumentException.class, () -> {
			UtilMaths.comb(6, -4);
		});
	}
	@Test
	void testNeg_04() {
		assertThrows(IllegalArgumentException.class, () -> {
			UtilMaths.comb(-10, -20);
		});
	}
	@Test
	void testNeg_05() {
		assertThrows(IllegalArgumentException.class, () -> {
			UtilMaths.comb(-20, -100);
		});
	}
	
	@Test
	void testPlusPetit_01() {
		assertThrows(IllegalArgumentException.class, () -> {
			UtilMaths.comb(1, 2);
		});
	}
	
	@Test
	void testPlusPetit_02() {
		assertThrows(IllegalArgumentException.class, () -> {
			UtilMaths.comb(20, 25);
		});
	}
	
	@Test
	void testPlusPetit_03() {
		assertThrows(IllegalArgumentException.class, () -> {
			UtilMaths.comb(4, 6);
		});
	}
	
	@Test
	void testPlusPetit_04() {
		assertThrows(IllegalArgumentException.class, () -> {
			UtilMaths.comb(10, 15);
		});
	}
	
	@Test
	void testPlusPetit_05() {
		assertThrows(IllegalArgumentException.class, () -> {
			UtilMaths.comb(8, 20);
		});
	}

}
