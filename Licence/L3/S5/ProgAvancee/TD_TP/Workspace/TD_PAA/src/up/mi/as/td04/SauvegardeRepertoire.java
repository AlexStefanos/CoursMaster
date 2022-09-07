package up.mi.as.td04;

import java.io.FileWriter;
import java.io.IOException;

public class SauvegardeRepertoire {
	public static void Sauvegarde(RepertoireSimple repS, String file) {
		try(FileWriter fW = new FileWriter(file)) {
			fW.write(repS.toString());
		} catch(IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
