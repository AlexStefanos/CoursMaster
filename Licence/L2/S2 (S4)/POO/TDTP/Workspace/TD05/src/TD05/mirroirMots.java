package TD05;
import java.util.StringTokenizer;
import java.util.Stack;

public class mirroirMots {
	public static void main(String[] args) {
		StringTokenizer st = new StringTokenizer("Bonjour, tout le monde*il fait beau", "* ,");
		Stack<String> pile = new Stack<String>();
		while(st.hasMoreTokens()) {
			pile.push(st.nextToken());
		}
		while(!pile.empty()) {
			System.out.print(pile.pop() + " ");
		}
	}
}
