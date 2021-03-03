package TD05;

import java.util.Stack;

public class mirroirLettres {
	public static void main(String[] args) {
		String phrase = "Bonjour, tout le monde*il fait beau";
		Stack<Character> pile = new Stack<Character>();
		for(int i=0; i<phrase.length(); i++) {
			Character c = new Character(phrase.charAt(i));
			pile.push(c);
		}
		while(!pile.empty()) {
			System.out.print(pile.pop() + " ");
		}
	}
}
