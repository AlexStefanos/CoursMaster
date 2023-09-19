package ubs.info.as.tp00.p2;

public class Q04 {
	public void affiche100() {
		for(int i = 1; i < 101; i++)
			System.out.println(i);
	}
	
	public void affiche100break() {
		for(int i = 1; i < 101; i++) {
			System.out.println(i);
			if(i == 47)
				break;
		}
	}
	
	public int affiche100return() {
		for(int i = 1; i < 101; i++) {
			System.out.println(i);
			if(i == 47)
				return(i);
		}
		return(0);
	}
	
	public static void main(String[] args) {
		Q04 test = new Q04();
		
		test.affiche100();
		test.affiche100break();
		int a = test.affiche100return();
		System.out.println(a);
	}
}