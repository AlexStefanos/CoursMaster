package exo1;

public class TestExo1 {
	
	public static void main(String[] args) {
		int i;

		String[] str1 = {"hello", "bonjour", "shalom"};
		String[] str2 = {"abchello", "abcbonjour", "abcshalom"};
		String[] str3 = {};
		String[] str4 = {"00100100", "01010010"};
		
		//Question1
		System.out.println("Question 1 - Test appartientTableau (1) : " + UtilString.appartientTableau("bonjour", str1));
		System.out.println("Question 1 - Test appartientTableau (2) : " + UtilString.appartientTableau("salut", str1));
		System.out.println("Question 1 - Test appartientTableau (3) : " + UtilString.appartientTableau("bonjour", str3));
		
		//Question2
		System.out.println("Question 2 - Test apparaitDansTableau (1) : " + UtilString.apparaitDansTableau("bonjour", str2));
		System.out.println("Question 2 - Test apparaitDansTableau (2) : " + UtilString.apparaitDansTableau("salut", str2));
		System.out.println("Question 2 - Test apparaitDansTableau (3) : " + UtilString.apparaitDansTableau("bonjour", str3));

		//Question3
		System.out.println("Question 3 - Test estPalindrome (1) : " + UtilString.estPalindrome("00100100"));
		System.out.println("Question 3 - Test estPalindrome (2) : " + UtilString.estPalindrome("001001001"));

		//Question4
		String[] str5 = UtilString.estPalindromeParMorceaux("01010010");
		String[] str6 = UtilString.estPalindromeParMorceaux("010100101");
		System.out.println("Question 4 - Test estPalindromeParMorceaux (1) : " + str5.length);
		
		for(i = 0 ; i < str5.length ; i++) {
			System.out.print(" " + str5[i] + " ");
			if (i == (str5.length -1)) 
				System.out.println();
		}
			
		System.out.println("Question 4 - Test estPalindromeParMorceaux (2) : " + str6.length);
		
		for(i = 0 ; i < str6.length ; i++) {
			System.out.print(" " + str6[i] + " ");
			if (i == (str6.length -1)) 
				System.out.println();
		}
		
		//Question5
		String[] str7 = UtilString.listerPalindrome(str4);
		String[] str8 = UtilString.listerPalindrome(str3);
		System.out.println("Question 5 - Test listerPalindrome (1) : " + str7.length);
		
		for(i = 0 ; i < str7.length ; i++) {
			System.out.print(" " + str7[i] + " ");
			if (i == (str7.length -1)) 
				System.out.println();
		}
			
		System.out.println("Question 5 - Test listerPalindrome (2) : " + str8.length);
		
		for(i = 0 ; i < str8.length ; i++) {
			System.out.print(" " + str8[i] + " ");
			if (i == (str8.length -1)) 
				System.out.println();
		}


	}

}
