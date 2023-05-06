class producteur extends Thread {

    private tamponCirc tampon;
    private int val = 0;
    
    public producteur(tamponCirc tampon) {
        this.tampon = tampon;
    }

    public void run() {
        while (true) {
            System.out.println("je depose "+val);
            tampon.depose( val++);
            try {
                Thread.sleep((int)(Math.random()*50));    // attend jusqu'a 100 ms
            } catch (InterruptedException e) {}
        }
    }
}


