package TP;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DptPlacement {
    private Compte compteSurveille;
    private double seuil;
    public DptPlacement(Compte compteSurveille) {
        this.compteSurveille = compteSurveille;
        seuil = 1000.0;
    }

    public void propositionPlacement(String dateOuverture) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String dateActuelle = formatter.format(date);
        String anneeActuelle = new String();
        String anneeOuverture = new String();
        for(int i = 0; i < 4; i++) {
            anneeActuelle += dateActuelle.charAt(i);
            anneeOuverture += dateOuverture.charAt(i);
        }
        int anneeActuelleInt = Integer.parseInt(anneeActuelle);
        int anneeOuvertureInt = Integer.parseInt(anneeOuverture);
        if(anneeActuelleInt - anneeOuvertureInt < 1) {
            if(compteSurveille.getSolde() > seuil) {
                System.out.println("Il vous est possible de faire un placement financier");
            }
        }
    }
}