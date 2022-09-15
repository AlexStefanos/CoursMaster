package ubs.mi.as.tp01.exosP2;

public class Q03 {
	public static boolean test1(int val) {
		System.out.println("test1(" + val + ")");
		System.out.println("result: " + (val < 1));
		return(val < 1);
	}
	
	public static boolean test2(int val) {
		System.out.println("test2(" + val + ")");
		System.out.println("result: " + (val < 2));
		return(val < 2);
	}
	
	public static boolean testFinal() {
		if((test1(22) == true) & (test2(22) == true))
			return(true);
		else
			return(false);
	}
	
	public static void main(String[] args) {
		System.out.println(Q03.testFinal());
	}
}
