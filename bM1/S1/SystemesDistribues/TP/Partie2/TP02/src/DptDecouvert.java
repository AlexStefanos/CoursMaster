package TP;

/**
 * TP MOM 2 : Application bancaire avec OpenJMS (Systeme Distribue)
 * @author Alexandre Stefanos
 */
public class DptDecouvert {
    private Compte compteSurveille;
    private Gerant gerant;

    /**
     * Constructeur de la classe DptDecouvert
     * @param compteSurveille : compte surveille par ce departement decouvert
     * @param gerant : gerant de ce compte
     */
    public DptDecouvert(Compte compteSurveille, Gerant gerant) {
        this.compteSurveille = compteSurveille;
        this.gerant = gerant;
    }

    /**
     * Permet au departement decouvert d'effectue une operation si le solde est inferieur a 0
     */
    public void retraitSolde() {
        if(compteSurveille.getSolde() < 0)
            gerant.faireOperation(compteSurveille, -10.0);
    }
}