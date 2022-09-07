package TD_TP05;
import java.util.Stack;
import java.util.StringTokenizer;

public class Mots {
	private String phrase;
	
	public Mots(String phrase) {
		this.phrase = phrase;
	}
	
	public void mirroirMots() {
		StringTokenizer st = new StringTokenizer(phrase);
		Stack<String> pile = new Stack<String>();
		while(st.hasMoreElements()) {
			pile.push(st.nextToken());
		}
		while(!pile.isEmpty()) {
			System.out.println(pile.pop()+ " ");
		}
		System.out.println("");
	}
	
	public void mirroirLettres() {
		Stack<Character> pile = new Stack<Character>();
		for (int i = 0; i<phrase.length(); i++) {
			Character c = new Character(phrase.charAt(i));
			pile.push(c);
		}
		while(!pile.isEmpty()) {
			System.out.println(pile.pop());
		}
		System.out.println("");
	}
	
	public String getPhrase() {
		return phrase;
	}
}
