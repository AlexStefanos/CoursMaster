package TD05;
import java.util.StringTokenizer;

public class Ex01 {
	
	public static void main (String[] args) {
		String chaine = "Bonjour, tout le monde* il fait beau";
		StringTokenizer st = new StringTokenizer(chaine, ", *");
		while(st.hasMoreElements()) {
			System.out.println("Element : " + st.nextElement());
		}
	}
}
