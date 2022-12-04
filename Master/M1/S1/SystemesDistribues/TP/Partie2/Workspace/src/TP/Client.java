package TP;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Client {
    private String id;
    private Compte compte;
    public Client(String id, Compte compte) {
        this.id = id;
        this.compte = compte;
    }

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