package CorrectionsTD03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import CorrectionsTD03.DicoT9;

class DicoT9Test {
	DicoT9 test;
	

	@BeforeAll
	void init() {
		test = new DicoT9();
		
	}
	
	@BeforeEach
	public void setUp() {
		
	}
	
	@Test
	void getChiffreT9Test() {
		
	}
	
	@Test
	void conversionTest() {
		
	}
	
	@Test
	void initTest() {
		ArrayList<String> liste = new ArrayList<String>();
		//assertIterableEquals(liste, test.getDico().get("26663"));	
	}
	
	@Test
	void enregistrerTestOne() {
		
	}
	
	@Test
	void enregistrerTestTwo() {
		
	}
	
	@Test
	void enregistrerTestTwoBis() {
		
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"prog", "descartes"})
	void recupererParameterizedTestFail(String str) {
		assertFalse(test.recuperer("26663").contains(str));
	}
}
