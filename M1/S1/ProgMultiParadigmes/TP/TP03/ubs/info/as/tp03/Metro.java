package ubs.info.as.tp03;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * TP03 Programmation Multi-Paradigmes
 * Classe de départ du TP02
 */
public class Metro {
    /**
     * Recupere les couples de noms de stations et le numéro de la ligne d'un fichier donné en parametre
     * @param nomDeFichier_
     * @return coupleMetro : ArrayList<String> des couples de noms de stations avec le numero de la ligne
     * @throws IOException
     */
    public static ArrayList<String> lireMetro(String nomDeFichier_) throws IOException {
        FileReader fileReader = new FileReader(nomDeFichier_);
        ArrayList<String> coupleMetro = new ArrayList<String>();
        char[] cbuf = new char[13300];
        fileReader.read(cbuf);
        String metros = new String(cbuf);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringTokenizer stringTokenizer = new StringTokenizer(metros, "\",");
        while(stringTokenizer.hasMoreTokens()) {
            String str = new String();
            str = stringTokenizer.nextToken();
            if(str.equals("  ") || str.equals("\n")) {
                coupleMetro.add(stringTokenizer.nextToken());
            }
            else {
                coupleMetro.add(str);
            }
        }
        bufferedReader.close();
        return(coupleMetro);
    }

    public static void main(String[] args) throws IOException {
        lireMetro("metro.txt");
    }
}