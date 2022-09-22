package ubs.info.as.tp00.p2;

import java.util.*;

public class Q07 {
	public void randomCompare() {
		Random rand = new Random();
		int b = rand.nextInt();
		
		System.out.println("b : " + b);
		for(int i = 0; i < 25; i++) {
			int a = rand.nextInt();
			if(a > b)
				System.out.println("a = " + a + " donc a est supérieur à b");
			else if(a < b)
				System.out.println("a = " + a + " donc a est inférieur à b");
			else
				System.out.println("a = " + a + " donc a est égale à b");
		}
	}
	
	public static void main(String[] args) {
		Q07 test = new Q07();
		
		test.randomCompare();
	}
}
