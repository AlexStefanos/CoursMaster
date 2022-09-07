package TD_TP05;

import java.util.StringTokenizer;

public class Ex01 {
	public static void main(String[] args) {
		StringTokenizer st = new StringTokenizer("Bonjour, tout le monde*il fait beau", ", *");
		while(st.hasMoreElements()) {
			System.out.println("Element : " + st.nextElement());
		}
	}
}
