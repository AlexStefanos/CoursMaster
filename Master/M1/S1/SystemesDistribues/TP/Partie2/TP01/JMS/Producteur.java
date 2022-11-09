import openjms.*;

public class Producteur {
    ConnectionFactory factory;
    Connection connection;
    Session session;
    Destination destination;
    MessageProducer msgProducer;

    public static void main(String[] args) {
        factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        connection = factory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        destination = (Destination)context.lookup("file1");
        msgProducer sender = session.createProducer(destination);
        TextMessage message = session.createMessage("hey");
        sender.send(message);
        String msg = new String();
    }
}