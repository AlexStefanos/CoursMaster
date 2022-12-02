package TP09;

import java.util.ArrayList;

public class SynchroPrioRedacteur {
    private ArrayList<Thread> lecteurs, redacteurs;
    private int nbLecteurs, nbRedacteurs;

    public SynchroPrioRedacteur() {
        this.lecteurs = new ArrayList<Thread>();
        this.redacteurs = new ArrayList<Thread>();
        this.nbLecteurs = 0;
        this.nbRedacteurs = 0;
    }

    public void debutLecture() {
        synchronized(this) {
            if(this.nbRedacteurs != 0) {
                this.lecteurs.add(Thread.currentThread());
                synchronized(Thread.currentThread()) {
                    try {
                        Thread.currentThread().wait();
                    } catch(InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
                this.lecteurs.remove(Thread.currentThread());
            }
            this.nbLecteurs++;
        }
    }

    public void finLecture() {
        synchronized(this) {
            if((this.nbLecteurs == 0) & (!(this.redacteurs.isEmpty()))) {
                synchronized(redacteurs.get(0)) {
                    redacteurs.get(0).notify();
                }
            }
        }
    }

    public void debutEcriture() {
        synchronized(this) {
            if(this.nbLecteurs != 0 || this.nbRedacteurs != 0) {
                this.redacteurs.add(Thread.currentThread());
                synchronized(Thread.currentThread()) {
                    try {
                        Thread.currentThread().wait();
                    } catch(InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
                this.redacteurs.remove(Thread.currentThread());
            }
            this.nbRedacteurs++;
        }
    }

    public void finEcriture() {
        synchronized(this) {
            this.nbRedacteurs--;
            if(!(this.lecteurs.isEmpty())) {
                for(Thread thread : this.lecteurs) {
                    synchronized(thread) {
                        thread.notify();
                    }
                }
            } else {
                if(!(this.redacteurs.isEmpty())) {
                    synchronized(this.redacteurs.get(0)) {
                        this.redacteurs.get(0).notify();
                    }
                }
            }
        }
    }
}
