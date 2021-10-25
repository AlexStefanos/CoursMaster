package up.mi.jgm.td04.unix;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Ce programme attend deux arguments sur la ligne de commande :");
			System.err.println("\t- le chemin vers le fichier qui doit etre copie ;");
			System.err.println("\t- le chemin vers le fichier cible de la copie.");
			System.exit(1);
		}

		String source = args[0];
		String cible = args[1];

		try (FileInputStream fis = new FileInputStream(new File(source));
				FileOutputStream fos = new FileOutputStream(new File(cible))) {
			int data = 0;
			do {
				data = fis.read();
				if(data != -1) {
					fos.write(data);
				}
			} while (data != -1);
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(2);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(3);
		}
	}
}
