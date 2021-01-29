public class Ex01 {

	public static void permuter(int a, int b) {
		int temp = a;
		a = b;
		b = temp;
		}
	
	public static void permuter(Entier e1, Entier e2) {
		int temp = e1.a;
		e1.a = e2.a;
		e2.a = temp;
		}
	public static void main(String[] args) {
		int nb1 = 10;
		int nb2 = 20;
		permuter(nb1,nb2);
		System.out.println("nb1 = " + nb1);
		System.out.println("nb2 = " + nb2);
		
		Entier e1 = new Entier();
		Entier e2 = new Entier();
		e1.a=10;
		e2.a=20;
		permuter(e1,e2);
		System.out.println("e1.a = " + e1.a);
		System.out.println("e2.a = " + e2.a);
		}
}

class Entier{
	int a;
}
