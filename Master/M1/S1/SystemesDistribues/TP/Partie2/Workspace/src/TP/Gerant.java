package TP;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**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
 * @author Alexandre Stefanos
 */
public class Gerant {
    private Context context;
    private TopicConnectionFactory topicConnectionFactory;
    private Topic topic;
    private TopicConnection topicConnection;
    private TopicSession topicSession;
    private TopicPublisher topicPublisher;
    private TopicSubscriber topicSubscriber;
    private Destination destination;
    private MapMessage mapMessageCompte;

    /**
     * Permet de demarrer la session de ce gerant
     * @param compte : premier compte dont ce gerant doit s'occuper
     */
    public void start(Compte compte) {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.exolab.jms.jndi.InitialContextFactory");
            props.put(Context.PROVIDER_URL, "tcp://localhost:3035/");
            context = new InitialContext(props);
            topicConnectionFactory = (TopicConnectionFactory)context.lookup("CF");
            topicConnection = topicConnectionFactory.createTopicConnection();
            topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = (Destination)context.lookup("ComptesGeres");
            mapMessageCompte = topicSession.createMapMessage();
            mapMessageCompte.setInt("Numero de Compte", compte.getNum());
            mapMessageCompte.setString("Identité de Client", compte.getIdClient());
//            mapMessage.setObject("Historique d'Opération", compte.getHistoriqueOperations());
            mapMessageCompte.setDouble("Solde", 0.0);
            send(compte);
        } catch(JMSException e) {
            System.err.println(e.getMessage());
        } catch(NamingException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Permet d'envoyer les donnees changees
     * @param compte : compte dont les donnees doivent etre envoyees
     */
    public void send(Compte compte) {
        try {
            if(compte.getEtatOuvert() == false) {
                topic = topicSession.createTopic(/*compte.getIdClient()*/"ComptesGeres");
                compte.setEtatOuvert(true);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                compte.setDateOuverture(formatter.format(date));
            }
            else
                topic = (Topic) context.lookup(/*compte.getIdClient()*/"ComptesGeres");
            topicPublisher = topicSession.createPublisher(topic);
            topicConnection.start();
            topicPublisher.publish(mapMessageCompte);
            topicConnection.stop();
        } catch(JMSException e) {
            System.err.println(e.getMessage());
        } catch(NamingException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Permet de recevoir les donnees changees
     * @param compte : compte dont les donnees doivent etre recues
     */
    public void receive(Compte compte) {
        try {
            if(compte.getEtatOuvert() == false) {
                topic = topicSession.createTopic(/*compte.getIdClient()*/"ComptesGeres");
                compte.setEtatOuvert(true);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                compte.setDateOuverture(formatter.format(date));
            }
            else
                topic = (Topic) context.lookup(/*compte.getIdClient()*/"ComptesGeres");
            topicConnection = topicConnectionFactory.createTopicConnection();
            topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = (Destination) context.lookup(/*compte.getIdClient()*/"ComptesGeres");
            topicSubscriber = topicSession.createSubscriber(topic);
            topicConnection.start();
            //ObjectMessage msgReceived = (ObjectMessage)topicSubscriber.receive(); le receive était infini
            topicConnection.stop();
        } catch (JMSException e) {
            System.err.println(e.getMessage());
        } catch (NamingException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Permet de fermer tous les processus ouverts
     */
    public void close() {
        try {
            if(topicConnection != null)
                topicConnection.close();
            if(topicSession != null)
                topicSession.close();
            if(topicPublisher != null)
                topicPublisher.close();
            if(topicSubscriber != null)
                topicSubscriber.close();
        } catch(JMSException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Permet de faire la prochaine operation
     * @param compte : compte sur lequel l'operation doit etre faite
     * @param montant : montant de l'operation
     */
    public void faireOperation(Compte compte, Double montant) {
        //if(si cette Operation est la dernière de la Queue) {
        try {
            if (compte.getEtatOuvert() == false) {
                topic = topicSession.createTopic(/*compte.getIdClient()*/"ComptesGeres");
                compte.setEtatOuvert(true);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                compte.setDateOuverture(formatter.format(date));
            } else {
                //topic = (Topic) context.lookup(compte.getIdClient());
            }
            compte.setSolde(compte.getSolde() + montant);
            mapMessageCompte.setDouble("Solde", compte.getSolde());
        } catch (JMSException e) {
            System.err.println(e.getMessage());
        } /*catch(NamingException e) {
                System.err.println(e.getMessage());
            }*/
        //}
    }
}