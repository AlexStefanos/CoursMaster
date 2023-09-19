package TP09;

import java.util.concurrent.Semaphore;

public class SynchroPrioLecteur {
    private Semaphore mutex;

    public SynchroPrioLecteur() {
        this.mutex = new Semaphore(1);
    }

    public void debutLectureEcriture() {
        this.mutex.acquireUninterruptibly();
    }

    public void finLectureEcriture() {
        this.mutex.release();
    }
}
