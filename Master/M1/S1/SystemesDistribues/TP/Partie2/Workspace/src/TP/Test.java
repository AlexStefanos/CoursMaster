package TP;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Test {
    static Context context;
    ConnectionFactory factory;
    Connection connection;
    static Session session;
    Destination destination;
    MessageProducer msgProducer;

    public void start() {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.exolab.jms.jndi.InitialContextFactory");
            props.put(Context.PROVIDER_URL, "tcp://localhost:3035/");
            context = new InitialContext(props);
            factory = (ConnectionFactory)context.lookup("CF");
            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = (Destination)context.lookup("file1");
            MessageProducer sender = session.createProducer(destination);
            TextMessage msgSend = session.createTextMessage("hey");
            connection.start();
            sender.send(msgSend);
            MessageConsumer receiver = session.createConsumer(destination);
            TextMessage msgReceived = (TextMessage)receiver.receive();
            System.out.println(msgReceived.getText());
            System.exit(1);
        } catch(JMSException e) {
            System.err.println(e);
        } catch(NamingException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) throws NamingException, JMSException {
        Test prod = new Test();
        prod.start();
    }
}