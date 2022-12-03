package Tests;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Test {
    Context context;
    ConnectionFactory factory;
    Connection connection;
    Session session;
    Destination destination;
    MessageProducer msgProducer;
    int nbMessage;

    public Test(int nbMessage) {
        this.nbMessage = nbMessage;
    }

    public int start() {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.exolab.jms.jndi.InitialContextFactory");
            props.put(Context.PROVIDER_URL, "tcp://localhost:3035/");
            context = new InitialContext(props);
            factory = (ConnectionFactory)context.lookup("CF");
            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = (Destination)context.lookup("file1");
            connection.start();
            for(int i = 0; i < nbMessage; i++) {
                MessageProducer sender = session.createProducer(destination);
                TextMessage msgSend = session.createTextMessage("hey");
                sender.send(msgSend);
            }
            return(0);
        } catch(JMSException e) {
            System.err.println(e.getMessage());
        } catch(NamingException e) {
            System.err.println(e.getMessage());
        }
        return(-1);
    }

    public int getMessages() {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.exolab.jms.jndi.InitialContextFactory");
            props.put(Context.PROVIDER_URL, "tcp://localhost:3035/");
            context = new InitialContext(props);
            factory = (ConnectionFactory) context.lookup("CF");
            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = (Destination) context.lookup("file1");
            connection.start();
            for (int i = 0; i < nbMessage; i++) {
                MessageConsumer receiver = session.createConsumer(destination);
                TextMessage msgReceived = (TextMessage) receiver.receive();
                System.out.println(msgReceived.getText());
            }
            return(0);
        } catch(JMSException e) {
            System.err.println(e.getMessage());
        } catch(NamingException e) {
            System.err.println(e.getMessage());
        }
        return(-1);
    }

    public static void main(String[] args) throws NamingException, JMSException {
        int tmp;
        Test prod = new Test(5);
        tmp = prod.start();
        tmp = prod.getMessages();
        System.exit(1);
    }
}