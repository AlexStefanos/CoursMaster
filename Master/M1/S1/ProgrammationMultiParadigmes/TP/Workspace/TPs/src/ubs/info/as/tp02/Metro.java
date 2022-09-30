package ubs.info.as.tp02;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Metro {
	
	public static void lireMetro(String nomDeFichier_) throws IOException { //peut-être return une String
		try(FileReader fReader = new FileReader(nomDeFichier_)) {
			BufferedReader bReader = new BufferedReader(fReader);
			ArrayList<String> metros = new ArrayList<String>();
			//while((str = bR.readLine()) != null)
			bReader.mark(0);
			while(bReader.readLine() != null) {				
				bReader.reset();
				metros.add(bReader.readLine());
				bReader.mark(0);
//				StringTokenizer strTokenizer = new StringTokenizer(bReader.readLine(), " ");
//				while(strTokenizer.hasMoreElements()) {
//					metros.add(strTokenizer.nextToken());
//				}
			}
			System.out.println(metros);
		}
	}
	
	public static void main(String[] args) throws IOException {
		lireMetro("metro.txt");
	}
}
