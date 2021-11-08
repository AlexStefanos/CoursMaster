package exo3;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class TestUtilMots {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choix, n;
		String str;
		
		do {
			System.out.println("Menu");
			System.out.println("1 - miroir");
			System.out.println("2 - palidrome");
			System.out.println("3 - trierListeMots");
			System.out.println("0 - Quitter");
			choix = sc.nextInt();
			sc.nextLine();
			
			switch(choix) {
				case 1 : System.out.println("Mot = ?");
				         str = sc.nextLine();
				         System.out.println("resault :" + UtilMots.miroir(str));
					     break;
			
				case 2 : System.out.println("Mot = ?");
		         		str = sc.nextLine();
		         		System.out.println("resault :" + UtilMots.palidrome(str));
		         		break;

				case 3 : List<String> strList = new ArrayList<String>();
						System.out.println("n = ?");
						 n = sc.nextInt();
						 sc.nextLine();
						 for(int i = 0 ; i < n ; i++) {
							 System.out.println("mot" + (i +1) + " :");
							 str = sc.nextLine();
							 strList.add(str);
						 }
						 
						 System.out.println("resault :" + UtilMots.trierListeMots(strList).toString());
						 break;

				case 0 : System.out.println("Merci !");
						 break;

				default : System.out.println("Erreur !");
			}
		}while(choix != 0);
		
		
		sc.close();

	}

}
