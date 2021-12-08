package up.mi.as.td04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UtilMathTest {
	
	@Test
	public void factTest() {
		assertEquals(6, UtilMath.fact(3));
		assertEquals(1, UtilMath.fact(0));
		assertEquals(10*9*8*7*6*5*4*3*2*1, UtilMath.fact(10));
	}
	
	@Test
	public void factExceptionTest() {
		assertThrows(IllegalArgumentException.class, () -> UtilMath.fact(-1));
		assertThrows(IllegalArgumentException.class, () -> UtilMath.fact(-100));
		assertThrows(IllegalArgumentException.class, () -> UtilMath.fact(-999));
	}
	
	@Test
	public void combTest() {
		assertEquals(10, UtilMath.comb(5, 2));
		assertEquals(1, UtilMath.comb(5, 5));
		assertEquals(1, UtilMath.comb(15, 0));
	}
	
	public void combExceptionTest() {
		assertThrows(IllegalArgumentException.class, () -> UtilMath.comb(-2,3));
		assertThrows(IllegalArgumentException.class, () -> UtilMath.comb(3,-1));
		assertThrows(IllegalArgumentException.class, () -> UtilMath.comb(-1,-2));
		assertThrows(IllegalArgumentException.class, () -> UtilMath.comb(2,5));
		assertThrows(IllegalArgumentException.class, () -> UtilMath.comb(1,2));
	}
}
