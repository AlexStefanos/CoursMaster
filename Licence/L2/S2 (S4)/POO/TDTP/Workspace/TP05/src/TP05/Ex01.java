package TP05;

import java.util.Random;
import java.util.Vector;

public class Ex01 {
	public static void main (String[] args) {
		Vector<Integer> vct = new Vector<Integer>(7);
		Random random = new Random();
		int nbrAlea;
		
		for (int i = 0; i< 7; i++) {
			nbrAlea = random.nextInt(50);
			while(nbrAlea == 0) {
				nbrAlea = random.nextInt(50);
			}
			vct.addElement(nbrAlea);
			System.out.println(vct.elementAt(i));
		}
	}
}
