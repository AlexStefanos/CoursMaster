package TP;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Client {
    private String id;
    private Compte compte;
    private Context context;
    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer msgProducer;
    public Client(String id, Compte compte) {
        this.id = id;
    }

    public void demandeOperation() {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.exolab.jms.jndi.InitialContextFactory");
            props.put(Context.PROVIDER_URL, "tcp://localhost:3035/");
            context = new InitialContext(props);
            factory = (ConnectionFactory) context.lookup("CF");
            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        } catch(JMSException e) {
            System.err.println(e.getMessage());
        } catch(NamingException e) {
            System.err.println(e.getMessage());
        }
    }
}