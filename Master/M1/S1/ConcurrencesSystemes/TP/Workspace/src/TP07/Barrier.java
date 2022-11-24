package TP07;

/*
wait <-   |                <-
clavier<->notifyAll()|      |
wait <-   |                 |
wait <-   |                 |
wait <-   |                 |
         (S)                |
     |                      |
     -> synch()            -

le problème de synchronisation c'est qu'il ne faut pas que le wait arrivant le notify
il ne faut faire le notify sur un thread avant que ce thr est fait son wait
Tombe très souvent en Exam
 */

public class Barrier {
    private int nbThread;
    private int cpt;
    private boolean isLock;
    public Barrier(int nbThread) {
        this.nbThread = nbThread;
        cpt = nbThread;
    }
    public synchronized void synchr() {
        if(cpt > 1) {
            cpt--;
            try {
                wait();
            } catch(InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        else {
            this.notifyAll();
            cpt = nbThread;
        }
    }
}
