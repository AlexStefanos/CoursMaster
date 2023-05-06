package TP06;

import java.io.File;
import java.util.LinkedList;

public class Find {
    private Thread th;

    public Find() throws InterruptedException {
        th = new Thread();
        th.start();
        th.join();
    }
    public void run(String[] args) {
        File repertoire = new File(args[1]);
        if (repertoire.isDirectory()) {
            File[] listeFichiers = repertoire.listFiles();
            LinkedList<Thread> works = new LinkedList<Thread>();
            for (File entree : listeFichiers) {
                if (entree.getName().equals(args[0]))
                    System.out.println(entree.getName());
            }
        }
    }
}
