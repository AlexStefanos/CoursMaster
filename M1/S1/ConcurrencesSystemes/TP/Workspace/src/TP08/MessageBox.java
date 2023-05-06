package TP08;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageBox {
    private ArrayList<Message> listMessage;
    private HashMap<Thread, Thread> waitingMessage;

    public MessageBox() {
        this.listMessage = new ArrayList<Message>();
        this.waitingMessage = new HashMap<Thread, Thread>();
    }

    public void deposit(Message msg_) {
        this.listMessage.add(msg_);
        Thread attent = this.waitingMessage.get(msg_.getSender());
        if(attent != null) {
            synchronized(msg_.getSender()) {
                msg_.getSender().notify();
            }
        }
    }

    public Message tryReceive(Filter f_) {
        Message result = null;
        for (Message msg : this.listMessage) {
            if (f_.isValid(msg)) {
                result = msg;
                return result;
            }
        }
        return(result);
    }

    public Message receive(Filter f_) {
        Message result = tryReceive(f_);
        if(result == null) {
            synchronized (f_.getSender()) {
                this.waitingMessage.put(f_.getSender(), Thread.currentThread());
                try {
                    f_.getSender().wait();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        for(Message msg : this.listMessage) {
            if(f_.isValid((msg))) {
                result = msg;
                this.listMessage.remove(msg);
                return(result);
            }
        }
        return(result);
    }
}
