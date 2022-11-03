package ubs.info.as.tp00.p2;

public class Q01 {
	int x, y, z;
	
	public Q01(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void calculs() {
		int a = 0;
		
		a = x + y - 2/2 + z;
		System.out.println(a);
		a = x + (y - 2)/(2 + z);
		System.out.println(a);
	}
	
	public static void main(String[] args) {
		Q01 test = new Q01(5, 7, 9);
		
		test.calculs();
	}
}
