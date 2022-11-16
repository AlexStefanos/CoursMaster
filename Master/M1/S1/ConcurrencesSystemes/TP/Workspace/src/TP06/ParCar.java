package TP06;

import java.io.*;

public class ParCar {
    private Thread th;

    public ParCar() throws InterruptedException {
        this.th = new Thread();
        th.start();
        th.join();
    }
    public void read(String[] args) {
        try {
            File file = new File(args[0]);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = new String();
            Runtime runtime = Runtime.getRuntime();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String mot[] = line.split(" ");
                th.sleep(2000);
            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (InterruptedException ie) {
            System.err.println(ie);
        }
    }
}
