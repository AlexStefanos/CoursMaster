package tp02;

import java.util.ArrayList;

public class Gerant {
    private ArrayList<Compte> comptesGeres;
    //private String/Operation/File operation(s)

    public Gerant() {
        comptesGeres = new ArrayList<Compte>();
    }

    public void addCompte(Compte compte) {
        this.comptesGeres.add(compte);
    }

    public ArrayList<Compte> getComptesGeres() {
        return(this.comptesGeres);
    }

    public void faireOperation() {
        //file JMS
    }
}
