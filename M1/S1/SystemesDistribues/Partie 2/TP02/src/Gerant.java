import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.exolab.jms.administration.AdminConnectionFactory;
import org.exolab.jms.administration.JmsAdminServerIfc;

import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class Gerant {
    private ArrayList<Compte> comptes;

    public Gerant() {
        comptes = new ArrayList<Compte>();
    }

    public ArrayList<Compte> getComptes() {
        return(this.comptes);
    }

    public void setComptes(ArrayList<Compte> comptes) {
        this.comptes = comptes;
    }

    public boolean estNouveau(Compte compte) {
        boolean result = true;
        if(comptes.size() > 0) {
            for(Compte cpte : comptes) {
                if((cpte.getId() == compte.getId())) {
                    result = false;
                }
            }
        }
        return(result);
    }

    public void creerFile() {
        try {
            String url = "tcp://localhost:3035/";
            JmsAdminServerIfc admin = AdminConnectionFactory.create(url);
            String queue = "operations";
            Boolean isQueue = Boolean.TRUE;
            if (!admin.addDestination(queue, isQueue)) {
                System.err.println("Failed to create queue " + queue);
            }
        } catch(JMSException e) {
            e.printStackTrace();
        } catch(MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void faireOperation() {
        Context context = null;
        ConnectionFactory factory = null;
        Connection connection = null;
        Destination dest = null;
        Session session = null;
        MessageConsumer receiver = null;
        Message message = null;
        ObjectMessage objectMessage = null;
        Compte compte = null, compteExistant = null;
        Hashtable<String, String> properties;

        try {
            properties = new Hashtable<String, String>();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.exolab.jms.jndi.InitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://localhost:3035");
            context = new InitialContext(properties);
            factory = (ConnectionFactory)context.lookup("CF");
            dest = (Destination)context.lookup("operations");
            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            receiver = session.createConsumer(dest);
            connection.start();
            System.out.println("Connexion reussie a la file operations");
            message = receiver.receive();
            if(message != null) {
                objectMessage = (ObjectMessage)message;
                compte = (Compte)objectMessage.getObject();
                if(compte != null) {
                    System.out.println("Operations recu de : " + compte.getNom());
                    if(estNouveau(compte)) {
                        System.out.println("Nouveau Compte");
                        comptes.add(compte);
                        System.out.println("Solde avant operation : " + compte.getSolde());
                        for(int operation : compte.getOperationsEnAttente()) {
                            System.out.println("Operation recus : " + operation);
                            compte.setSolde(compte.getSolde() + operation);
                            compte.ajouteOperationHistorique(operation);
                            System.out.println("Solde apres operation : " + compte.getSolde());
                        }
                    } else {
                        System.out.println("Compte deja existant");
                        for(Compte cpte : comptes) {
                            if(cpte.getId() == compte.getId()) {
                                compteExistant = cpte;
                            }
                        }
                        System.out.println("Solde avant operation : " + compteExistant.getSolde());
                        if(compteExistant.getOperationsEnAttente().size() > 0) {
                            for(int operation : compteExistant.getOperationsEnAttente()) {
                                System.out.println("Operation recus : " + operation);
                                compte.setSolde(compte.getSolde() + operation);
                                System.out.println("Solde apres operation : " + compte.getSolde());
                            }
                        }
                        if(compte.getOperationsEnAttente().size() > 0) {
                            for(int operation : compte.getOperationsEnAttente()) {
                                System.out.println("Operation recus : " + operation);
                                compte.setSolde(compte.getSolde() + operation);
                                System.out.println("Solde apres operation : " + compte.getSolde());
                            }
                        }
                    }
                }
                System.out.println("Operations realisees");
                for(Compte cpte : comptes) {
                    System.out.println("Solde " + cpte.getNom() + " : " + cpte.getSolde());
                }
            }
        } catch(JMSException e) {
            e.printStackTrace();
        } catch(NamingException e) {
            e.printStackTrace();
        } finally {
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Deconnexion reussie de la file operations");
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Gerant gerant = new Gerant();
        gerant.creerFile();
        while(true) {
            if(gerant.getComptes().size() > 0) {
                System.out.println("Voici les comptes");
                for(Compte cpte : gerant.getComptes()) {
                    System.out.println("Comptes de " + cpte.getNom() + " d'id : " + cpte.getId());
                    System.out.println("Historique : " + cpte.getHistorique());
                }
            } else {
                System.out.println("Y a po d'comptes");
            }
            gerant.faireOperation();
        }
    }
}
