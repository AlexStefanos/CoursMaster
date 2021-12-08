package up.mi.as.td04;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Exo2Unix {
	public static void cp(String nomFile1, String nomFile2) throws FileNotFoundException, IOException {
		File f1 = new File(nomFile1);
		File f2 = new File(nomFile2);
		int c;
		
		try {
			FileInputStream f1Input = new FileInputStream(f1);
			if(!f1.exists()) {
				System.out.println("Il n'existe pas => Création");
				f2.createNewFile();
			}
			FileOutputStream f2Output = new FileOutputStream(f2);
			c = f1Input.read();
			while(c != -1) {
				f2Output.write(c);
				c = f1Input.read();
			}
			f1Input.close();
			f2Output.close();
		} catch(FileNotFoundException e) {
			System.out.println("Impossible d'ouvrir le fichier : " + e.getMessage());
		} catch(IOException e) {
			System.out.println("La création du fichier " + f2 + " a échoué => " + e.getMessage());
		}
	}
}
