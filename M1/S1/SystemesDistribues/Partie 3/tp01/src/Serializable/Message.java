package Serializable;


public class Message {
    private String sender;
    private String message;

    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public void printMessage() {
        System.out.println(sender + ": " + message);
    }
}
