package TP06;

import java.io.File;
import java.util.LinkedList;

public class FindCorrec extends Thread {
    private Thread th;
    private String motif;
    private File repertoire;
    private int tailleRep;
    static int nbRep = 0;
    static synchronized void incNbRep() {
        nbRep++;
    }
    public static synchronized int getNbRep() {
        return(nbRep);
    }
    static boolean trouve = false;
    public static synchronized void setTrouve() {
        trouve = true;
    }
    public static synchronized boolean isTrouve() {
        return(trouve);
    }

    public FindCorrec(String unFichier, File unRepertoire) {
        motif = unFichier;
        repertoire = unRepertoire;
    }

    public void run() {

    }
}
