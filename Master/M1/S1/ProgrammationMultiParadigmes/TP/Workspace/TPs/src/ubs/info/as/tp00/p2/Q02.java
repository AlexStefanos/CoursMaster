package ubs.info.as.tp00.p2;

public class Q02 {
	public static int ternary(int i) {
		return(i < 10 ? i * 100 : i * 100);
	}
	
	public static int alternative(int i) {
		if(i < 10)
			return(i * 100);
		else
			return(i * 10);
	}
	
	public static void main(String[] args) {
		Q02 test = new Q02();
		
		System.out.println(ternary(5));
		System.out.println(alternative(2));
	}
}
