package TP04;

public class TriNombres {
	private int[] tab;
	
	public TriNombres (int[] tab) {
		this.tab = tab;
	}
	
	public void Trier() {
		boolean permute = true;
		while(permute){
			permute=false;
		    for (int i=0;i<tab.length-1; i++){
		       if (tab[i]>tab[i+1]){
		           int temp = tab[i];
		           tab[i] = tab[i+1];
		           tab[i+1] = temp;
		           permute = true;
		       }
		     }//fin for
		  }//fin while	   
	}// fin trier
	
	//affichage du tableau
	public void afficher(){
		for(int i=0; i<tab.length; i++){
			System.out.println(tab[i]);
		}
	}	
}
