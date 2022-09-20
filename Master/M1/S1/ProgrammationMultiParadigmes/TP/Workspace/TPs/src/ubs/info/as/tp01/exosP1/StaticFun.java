package ubs.info.as.tp01.exosP1;

//Premier exercice de Thinking in Java - Bruce Eckel

public class StaticFun {
	static void incr() {
		StaticTest.i++;
	}
	
	public static void main(String args[]) {
		StaticTest st = new StaticTest();
		System.out.println("st.i = " + st.i);
		incr();
		System.out.println("st.i incrémenté = " + st.i);
	}
}