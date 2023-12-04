import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.io.Serializable;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Destination;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class Client implements Serializable {
    private Compte compte;

    public Client(Compte compte) {
        this.compte = compte;
    }

    public void ajouteOperationEnAttente(int montantOperation) {
        this.compte.getOperationsEnAttente().add(montantOperation);
    }

    public void supprimeOperationEnAttente(int montantOperation) {
        this.compte.getOperationsEnAttente().remove(montantOperation);
    }

    public void envoieOperationsEnAttente() {
        Hashtable<String, String> properties;
        Context context = null;
        ConnectionFactory factory = null;
        Connection connection = null;
        Destination dest = null;
        Session session = null;
        MessageProducer sender = null;

        try {
            properties = new Hashtable<String, String>();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.exolab.jms.jndi.InitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://localhost:3035");
            context = new InitialContext(properties);
            factory = (ConnectionFactory)context.lookup("CF");
            dest = (Destination)context.lookup("operations");
            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            sender = session.createProducer(dest);
            connection.start();
            System.out.println("Connexion reussie a la file comptes");
            ObjectMessage objectMessage = session.createObjectMessage();
            objectMessage.setObject(this.compte);
            sender.send(objectMessage);
            System.out.println("Message envoye par " + this.compte.getNom() + ": " + this.compte.getSolde());
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
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Compte compte = new Compte();
        System.out.println("id : " + compte.getId());
        compte.setNom("Alex");
        System.out.println("nom : " + compte.getNom());
        Client client = new Client(compte);
        client.ajouteOperationEnAttente(22);
        client.ajouteOperationEnAttente(200);
        client.ajouteOperationEnAttente(-10);
        client.envoieOperationsEnAttente();
    }
}
