package TP04;

public class TriNombres {
	private int[] tab;
	
	public TriNombres(int tab[]) {
		this.tab = tab;
	}
	
	public void Tri() {
		int tmp;
		for (int i = tab.length; tab.length>2; i--) {
			for (int j = 1; j<i-1;i++) {
				if (tab[j] > tab[j + 1]) {
					tmp = tab[j];
					tab[j] = tab[j+1];
					tab[j+1] = tmp;
					i = 0;
				}
			}
		}
	}
	
	public void afficher() {
		for (int i = 0; i < tab.length; i++) {
			System.out.println(tab[i]);
		}
	}
}