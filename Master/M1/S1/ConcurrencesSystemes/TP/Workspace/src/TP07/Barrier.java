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
 */

public class Barrier {
    private final int nbThread;
    private int cpt;
    public Barrier(int nbThread) {
        this.nbThread = nbThread;
        cpt = nbThread;
    }
    public synchronized synchr() {
        if(cpt > 1) { //pas le dernier
            cpt--;
            try {
                wait();
            } catch() {

            }
        }
        else { //le dernier
            notifyAll();
            cpt = nbThread;
        }
    }
}
