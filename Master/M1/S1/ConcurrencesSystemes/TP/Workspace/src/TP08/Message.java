package TP08;
public class Message {
    private Object message;
    private Thread sender;

    public Message(Object o_) {
        this.message = o_;
        this.sender = Thread.currentThread();
    }

    public Object getObject() {
        return(this.message);
    }

    public Object getSender() {
        return(this.sender);
    }
}
