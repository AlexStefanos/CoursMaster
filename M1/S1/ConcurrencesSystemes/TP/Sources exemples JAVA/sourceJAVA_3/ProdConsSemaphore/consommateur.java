class consommateur extends Thread {

    private tamponCirc tampon;
    
    public consommateur(tamponCirc tampon) {
        this.tampon = tampon;
    }

    public void run() {
        while (true) {
            System.out.println("je preleve "+((Integer)tampon.preleve()).toString());
            try {
                Thread.sleep((int)(Math.random()*100));    // attends jusqu'a 200 ms
            } catch (InterruptedException e) {}
        }
    }
}


