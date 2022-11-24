package TP07;

import java.util.ArrayList;

public class CPrintln {
    static int sval = 0;
    String mot;
    CPrintln next;
    Barrier bar;

    public CPrintln(String mot_, Barrier bar_) {
        this.mot = mot_;
        this.bar = bar_;
    }
    public void setNext(CPrintln n) {next = n;}

    public void run() {
        synchronized(this) {
            bar.synchr();
            while(true) {
                try {
                    wait();
                } catch(Exception e) {
                    System.err.println(e.getMessage());
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
            System.err.println("Error args");
            System.exit(-1);
        }
        Barrier bar = new Barrier(args.length + 1);
        ArrayList<CPrintln> tabCPrintln = new ArrayList<CPrintln>();
        for(int i = 0; i < args.length; i++) {
            tabCPrintln.add(new CPrintln(args[i], bar));
        }
        for(int i = 0; i < tabCPrintln.size() - 1; i++) {
            tabCPrintln.get(i).setNext(tabCPrintln.get(0));
        }
        tabCPrintln.get(tabCPrintln.size() - 1).setNext(tabCPrintln.get(0));
        for(int i = 0; i < tabCPrintln.size() - 1; i++) {
            Thread thread = new Thread();
            thread.start();
        }
        try {
            synchronized(tabCPrintln.get(0)) {
                tabCPrintln.get(0).notify();
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
