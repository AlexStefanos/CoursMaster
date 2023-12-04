import java.util.Hashtable;

import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;

public class DptPlacement {
    public static void main(String[] args) {
        Context context = null;
        TopicConnectionFactory topicConnectionFactory = null;
        TopicConnection topicConnection = null;
        Topic topic = null;
        TopicSession topicSession = null;
        TopicSubscriber topicSubscriber = null;
        TextMessage text = null;
        Message message = null;
        Hashtable<String, String> properties;

        properties = new Hashtable<String, String>();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.exolab.jms.jndi.InitialContextFactory");
        properties.put(Context.PROVIDER_URL, "tcp://localhost:3035");
        try {
            while(true) {
                /**
                 * Je n'ai pas reussi a bien le faire fonctionner mais le but était d'avoir un topic par compte. Ce topic contient le solde
                 * mis a jour du compte et où les deux departements sont deux démons qui font ce qu'ils doivent faire apres chaque mise a jour
                 * du solde d'un compte
                 */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
