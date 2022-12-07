package TP01;

public class MessageBoxImpl implements MessageBox {
    private transient String msg;
    private String sender;

    public MessageBoxImpl(String msg, String sender) {
        this.msg = msg;
        this.sender = sender;
    }

    public String getMessage() {
        return(msg);
    }
    public String sendMessage() {
        return(msg);
    }

    public void printMessage() {
        System.out.println("Sender : " + sender);
        System.out.println("Message : " + msg);
    }
}
