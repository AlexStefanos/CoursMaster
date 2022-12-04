package TP;

import javax.jms.*;
import javax.naming.Context;

public class DptDecouvert {
    private Compte compteSurveille;
    private Context context;
    private Gerant gerant;
    private TopicConnectionFactory topicConnectionFactory;
    private Topic topic;
    private TopicConnection topicConnection;
    private TopicSession topicSession;
    private TopicPublisher topicPublisher;
    private TopicSubscriber topicSubscriber;
    private Destination destination;

    public DptDecouvert(Compte compteSurveille, Gerant gerant) {
        this.compteSurveille = compteSurveille;
        this.gerant = gerant;
    }

    public void retraitSolde() {
        if(compteSurveille.getSolde() < 0)
            gerant.faireOperation(compteSurveille, -10.0);
    }
}