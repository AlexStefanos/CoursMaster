import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.Properties;

public class Producteur {
    static Context context;
    ConnectionFactory factory;
    Connection connection;
    static Session session;
    Destination destination;
    MessageProducer msgProducer;

    public void start() {
        try {
            //Hashtable properties = new Hashtable();
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.exolab.jms.jndi.InitialContextFactory");
            props.put(Context.PROVIDER_URL, "tcp://localhost:3035");
            context = new InitialContext(props);
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
    }
}

