package TP08;

class Producteur extends Thread {
    private MessageBox msgBox;
    private int delay;

    public Producteur(MessageBox msgBox_, int delay_) {
        this.msgBox = msgBox_;
        this.delay = delay_;
    }
}

class Consommateur extends Thread {
    private MessageBox msgBox;
    private Thread thread;
    private int delay;

    public Consommateur(MessageBox msgBox_, Thread thread_, int delay_) {
        this.msgBox = msgBox_;
        this.thread = thread_;
        this.delay = delay_;
    }

    public void run() {
        int val = 0;
        Filter filter = new Filter(thread);
        while(true) {
            Message msg;
            msg = msgBox.receive(filter);
            try {
                Thread.sleep(500 + (int)(Math.random() * delay));
            } catch(InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
public class TestMessageBox {
    public static void main(String[] args) {
        MessageBox msgBox = new MessageBox();
        int i;
        for(int j = 0; j < 10; j++) {
            msgBox.deposit(new Message(j));
        }
        Filter filtre = new Filter(Thread.currentThread());
        Producteur prod1 = new Producteur(msgBox, 1000);
        Consommateur cons1 = new Consommateur(msgBox, prod1, 1000);
        Producteur prod2 = new Producteur(msgBox, 2000);
        Consommateur cons2 = new Consommateur(msgBox, prod2, 2000);
        cons1.start();
        prod1.start();
        cons2.start();
        prod2.start();
        try {
            Thread.sleep(10000);
        } catch(InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
