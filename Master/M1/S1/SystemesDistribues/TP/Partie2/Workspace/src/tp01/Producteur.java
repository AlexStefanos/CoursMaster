package tp01;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class Producteur {
    static Context context;
    ConnectionFactory factory;
    Connection connection;
    static Session session;
    Destination destination;
    MessageProducer msgProducer;

    public void start() {
        try {
            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.exolab.jms.jndi.InitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://127.0.1.1:3035/");
            context = new InitialContext(properties);
            factory = (ConnectionFactory) context.lookup("ConnectionFactory");
            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
            Destination destination = (Destination) context.lookup("file1");
            MessageProducer sender = session.createProducer(destination);
            TextMessage message = session.createTextMessage("hey");
            sender.send(message);
        } catch(JMSException e) {
            System.err.println(e);
        } catch(NamingException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) throws NamingException, JMSException {
        Producteur prod = new Producteur();
        prod.start();
        Destination destination = (Destination) context.lookup("file1");
        MessageProducer sender = session.createProducer(destination);
        TextMessage message = session.createTextMessage("hey");
        sender.send(message);
    }
}

