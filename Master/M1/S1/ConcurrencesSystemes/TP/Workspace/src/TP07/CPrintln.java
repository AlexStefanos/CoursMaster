package TP07;

public class CPrintln {
    static int sval = 0;
    String mot;
    CPrintln next;

    public void setNext(CPrintln n) {
        next = n;
    }

    public void run() {
        synchronized(this) {
            bar.synchr();
            while(true) {
                try {
                    wait();
                } catch(Exception e) {

                }
                System.out.println(mot);
                synchronized (next) {
                    next.notify();
                }
            }
         }
    }
    public static void main(String[] args) {
        if(args.length < 2) {

        }
        Pprintln tabCprintln[] = new CPrintln[args.length];
        Barrier bar = new Barrier(args.length + 1);
        for(int i = 0; i < args.length; i++)
            tabCprintln[]
    }
}
