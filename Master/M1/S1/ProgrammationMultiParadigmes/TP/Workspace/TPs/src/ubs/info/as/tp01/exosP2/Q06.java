package ubs.mi.as.tp01.exosP2;

public class Q06 {
	public void compareStrings(String s1, String s2) {
		if(s1 == s2)
			System.out.println("== : " + true);
		else
			System.out.println("== : " + false);
		
		if(s1 != s2)
			System.out.println("!= : " + true);
		else
			System.out.println("!= : " + false);
		
		if(s1.equals(s2))
			System.out.println("equals() : " + true);
		else
			System.out.println("equals() : " + false);
	}
	
	public static void main(String[] args) {
		Q06 test = new Q06();
		
		test.compareStrings("hey", "salut");
		test.compareStrings("bien", "bien");
	}
}
