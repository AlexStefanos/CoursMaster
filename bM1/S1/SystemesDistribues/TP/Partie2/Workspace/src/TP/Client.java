package TP;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Alexandre Stefanos
 */
public class Client {
    private String id;
    private Compte compte;

    /**
     * Constructeur de la classe Client
     * @param id : identite du client
     * @param compte : compte du client
     */
    public Client(String id, Compte compte) {
        this.id = id;
        this.compte = compte;
    }

    /**
     * Permet de demander une opération sur le compte
     * @param montant : montant de l'opération
     */
    public void demandeOperation(double montant) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        if(compte.getEtatOuvert() == false) {
            compte.setEtatOuvert(true);
            compte.setDateOuverture(formatter.format(date));
        }
        compte.ajoutOperationQueue(montant, formatter.format(date));
    }
}