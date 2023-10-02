package ubs.info.as.tp02;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Metro {
    private int numLigne;

    public Metro(int numLigne) {
        this.numLigne = numLigne;
    }

    //voir aussi Pattern.split / Regexp
    public static ArrayList<String> lireMetro(String nomDeFichier_) throws IOException {
        FileReader fileReader = new FileReader(nomDeFichier_);
        ArrayList<String> coupleMetro = new ArrayList<String>();
        char[] cbuf = new char[13300];
        fileReader.read(cbuf);
        String metros = new String(cbuf);
        // System.out.println(metros);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringTokenizer stringTokenizer = new StringTokenizer(metros, "\"");
        while(stringTokenizer.hasMoreTokens()) {
            String str = new String();
            str = stringTokenizer.nextToken();
            if(str.equals("  ")) {
                coupleMetro.add(stringTokenizer.nextToken());
            }
            else {
                coupleMetro.add(str);
            }
        }
        return(coupleMetro);
    }

    public static void main(String[] args) {
        try {
            System.out.println(lireMetro("metro.txt"));
        } catch(IOException e ) {
            System.err.println(e.getMessage());
        }
    }
}