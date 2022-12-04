package TP;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

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
    private QueueReceiver queueReceiver;
    private MessageProducer msgProducer;
    private MapMessage mapMessageCompte;

    public Compte(int num, String idClient) {
        this.num = num;
        this.idClient = idClient;
        this.etatOuvert = false;
        historiqueOperations = new Double[100];
        solde = 0;
    }

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
            queueConnection.close();
        } catch(JMSException e) {
            System.err.println(e.getMessage());
        } catch(NamingException e) {
            System.err.println(e.getMessage());
        }
    }

    public double getSolde() {
        return(this.solde);
    }

    public void setSolde(double montant) {
        solde = montant;
    }

    public int getNum() {
        return(num);
    }

    public String getIdClient() {
        return(idClient);
    }

    public boolean getEtatOuvert() {
        return(etatOuvert);
    }

    public void setEtatOuvert(boolean bool) {
        etatOuvert = bool;
    }

    public Double[] getHistoriqueOperations() {
        return(historiqueOperations);
    }

    public String getDateOuverture() {
        return(dateOuverture);
    }

    public void setDateOuverture(String date) {
        dateOuverture = date;
    }
}
