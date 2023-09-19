package TP;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * @author Alexandre Stefanos
 */
public class Compte {
    private int num;
    private String idClient;
    private String dateOuverture;
    private Double[] historiqueOperations;
    private double solde;
    private boolean etatOuvert;
    private Context context;
    private QueueConnection queueConnection;
    private QueueConnectionFactory queueConnectionFactory;
    private QueueSession queueSession;
    private Destination destination;
    private QueueSender queueSender;
    private MessageProducer msgProducer;
    private MapMessage mapMessageCompte;

    /**
     * Constructeur de la classe Compte
     * @param num : numero de compte
     * @param idClient : identite du client
     */
    public Compte(int num, String idClient) {
        this.num = num;
        this.idClient = idClient;
        this.etatOuvert = false;
        historiqueOperations = new Double[100];
        solde = 0;
    }

    /**
     * Permet d'ajouter une operation à la file d'operations de ce gerant
     * @param montant : montant de l'operation
     * @param date : date de l'operation
     */
    public void ajoutOperationQueue(double montant, String date) {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.exolab.jms.jndi.InitialContextFactory");
            props.put(Context.PROVIDER_URL, "tcp://localhost:3035/");
            context = new InitialContext(props);
            queueConnectionFactory = (QueueConnectionFactory) context.lookup("CF");
            Queue operationsEnAttente = (Queue) context.lookup("OperationsEnAttente");
            queueConnection = queueConnectionFactory.createQueueConnection();
            queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = (Destination) context.lookup("OperationsEnAttente");
            queueSender = queueSession.createSender(operationsEnAttente);
            queueConnection.start();
            mapMessageCompte = queueSession.createMapMessage();
            mapMessageCompte.setInt("Numero de Compte", num);
            mapMessageCompte.setString("Identité de Client", idClient);
            mapMessageCompte.setDouble("Operations", montant);
            mapMessageCompte.setString("Date", date);
            msgProducer = queueSession.createProducer(destination);
            msgProducer.send(mapMessageCompte);
            queueConnection.stop();
        } catch(JMSException e) {
            System.err.println(e.getMessage());
        } catch(NamingException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Permet de fermer les processus ouverts
     */
    public void close() {
        try {
            if(queueConnection != null)
                queueConnection.close();
            if(msgProducer != null)
                msgProducer.close();
            if(queueSender != null)
                queueSender.close();
            if(queueSession != null)
                queueSession.close();
        } catch(JMSException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Getter du solde de ce compte
     * @return le solde de ce compte
     */
    public double getSolde() {
        return(this.solde);
    }

    /**
     * Setter du solde de ce compte
     * @param montant : montant du nouveau solde
     */
    public void setSolde(double montant) {
        solde = montant;
    }

    /**
     * Getter du numero de ce compte
     * @return le numero du compte
     */
    public int getNum() {
        return(num);
    }

    /**
     * Getter de l'identite du client de ce compte
     * @return l'identite du client de ce compte
     */
    public String getIdClient() {
        return(idClient);
    }

    /**
     * Getter de l'etat d'ouverture de ce compte
     * @return l'etat d'ouverture de ce compte : true si le compte est ouvert, false sinon
     */
    public boolean getEtatOuvert() {
        return(etatOuvert);
    }

    /**
     * Setter de l'etat d'ouverture de ce compte
     * @param bool : l'etat d'ouverture de ce compte
     */
    public void setEtatOuvert(boolean bool) {
        etatOuvert = bool;
    }

    /**
     * Getter de la date d'ouverture de ce compte
     * @return la date d'ouverture de ce compte
     */
    public String getDateOuverture() {
        return(dateOuverture);
    }

    /**
     * Setter de la date d'ouverture de ce compte
     * @param date : date d'ouverture de compte
     */
    public void setDateOuverture(String date) {
        dateOuverture = date;
    }
}
