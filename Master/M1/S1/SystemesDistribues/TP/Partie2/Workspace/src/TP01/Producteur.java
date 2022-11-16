package TP01;

//import jms.openjms.*;
import javax.jms.*;

public class Producteur {
    ConnectionFactory factory;
    Connection connection;
    Session session;
    Destination destination;
    MessageProducer msgProducer;

    public Producteur() {
        try {
            factory = (ConnectioComptenFactory) context.lookup("ConnectionFactory");
            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
            destination = (Destination) context.lookup("file1");
            MessageProducer sender = session.createProducer(destination);
        } catch(JMSException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        TextMessage message = session.createMessage("hey");
        sender.send(message);
        String msg = new String();
    }
}