package ubs.mi.as.tp01.exosP1;

//Premier exercice de Thinking in Java - Bruce Eckel

public class Q05 {
	static int storage(String s) {
		return s.length() * 2;
	}
	
	public static void main(String[] args) {
		String s = new String("Thinking in Java");
		System.out.println("s : " + s + " , s' storage : " + storage(s));
	}
}