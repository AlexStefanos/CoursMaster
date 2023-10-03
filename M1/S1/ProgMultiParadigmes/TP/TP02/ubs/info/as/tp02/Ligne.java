package ubs.info.as.tp02;

import java.util.HashSet;

public class Ligne {
    private int numLigne;
    private HashSet<Station> stations;

    public Ligne(int numLigne) {
        this.numLigne = numLigne;
        this.stations = new HashSet<Station>();
    }

    public int getNumLigne() {
        return(this.numLigne);
    }

    public void setNumLigne(int nouvNumLigne_) {
        this.numLigne = nouvNumLigne_;
    }

    public HashSet<Station> getStations() {
        return(stations);
    }

    public void setStations(HashSet<Station> nouvStation_) {
        this.stations = nouvStation_;
    }
}
