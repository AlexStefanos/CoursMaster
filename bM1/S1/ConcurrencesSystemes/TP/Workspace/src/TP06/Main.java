package TP06;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ParCar pc = new ParCar();
        pc.read(args);
        System.out.println("");
        Find find = new Find();
        find.run(args);
    }
}
