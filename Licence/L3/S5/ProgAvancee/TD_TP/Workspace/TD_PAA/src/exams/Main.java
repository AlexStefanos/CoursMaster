package exams;

public class Main {
	public static void main(String[] args) {
		Dictionnaire dict = new Dictionnaire();
		
		dict.parser("dico.txt");
		dict.save("test.txt");
	}
}
