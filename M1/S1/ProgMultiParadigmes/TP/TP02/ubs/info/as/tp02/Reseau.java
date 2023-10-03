package ubs.info.as.tp02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Reseau {
    public Reseau(ArrayList<Station> stations, HashSet<Station> graphe) {
        stations = new ArrayList<Station>();
        graphe = new HashSet<Station>();
    }

    public static Reseau CreeReseauAPartirDuFichier(String nomDeFichier_) throws IOException {
        ArrayList<String> listeLireMetro = Metro.lireMetro(nomDeFichier_);
        ArrayList<Station> listeStations = new ArrayList<Station>();
        HashSet<Station> graphe = new HashSet<Station>();
        Reseau reseau = new Reseau(listeStations, graphe);
        for(String str : listeLireMetro) {
            System.out.println(str);
            listeStations.add(new Station(str));
        }
        System.out.println(listeStations);
        return(reseau);
    }

    public static void main(String[] args) {
        try {
            System.out.println(CreeReseauAPartirDuFichier("metro.txt"));
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
