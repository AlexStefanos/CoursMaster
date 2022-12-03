package Tests;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
public class Test2 {
    Context context;
    QueueConnectionFactory queueConnectionFactory;
    Queue file1;
    QueueConnection queueConnection;
    QueueSession queueSession;
    QueueSender queueSender;
    QueueReceiver queueReceiver;
    ConnectionFactory factory;
    Connection connection;
    Session session;
    Destination destination;
    MessageProducer msgProducer;
    int nbMessage;

    public Test2(int nbMessage) {
        this.nbMessage = nbMessage;
    }

    public int start() {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.exolab.jms.jndi.InitialContextFactory");
            props.put(Context.PROVIDER_URL, "tcp://localhost:3035/");
            context = new InitialContext(props);
            queueConnectionFactory = (QueueConnectionFactory)context.lookup("CF");
            file1 = (Queue)context.lookup("file1");
            queueConnection = queueConnectionFactory.createQueueConnection();
            queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            queueSender = queueSession.createSender(file1);
            queueConnection.start();
            Destination destination = (Destination)context.lookup("file1");
            for(int i = 0; i < nbMessage; i++) {
                MessageProducer sender = queueSession.createProducer(destination);
                TextMessage msgSend = queueSession.createTextMessage("hey");
                sender.send(msgSend);
            }
            return(0);
        } catch(NamingException e) {
            System.err.println(e.getMessage());
        } catch(JMSException e) {
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
            destination = (Destination) context.lookup("file1");
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

    public static void main(String[] args) {
        int tmp;
        Test2 test = new Test2(5);
        tmp = test.start();
        tmp = test.getMessages();
        System.exit(1);
    }
}
