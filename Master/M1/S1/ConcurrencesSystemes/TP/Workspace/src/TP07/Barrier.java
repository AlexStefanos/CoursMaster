package TP07;

/*
wait <-   |                <-
clavier<->|                 |
wait <-   |                 |
wait <-   |                 |
wait <-   |                 |
         (S)                |
     |                      |
     -> synch()            -
 */

public class Barrier {
    private final int nbThread;
    private int nb;
    public Barrier(int nbThread) {
        this.nbThread = nbThread;
    }
    public synchronized synchr() {

    }
}
