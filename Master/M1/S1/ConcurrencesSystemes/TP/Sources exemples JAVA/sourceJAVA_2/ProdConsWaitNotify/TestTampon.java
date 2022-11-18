class tamponCirc {

    private Object tampon[];
    private int taille;
    private int in, out, nbMess;

    /** constructeur d'un tampon borne */
    public tamponCirc (int taille) {
        tampon = new Object[taille];
        this.taille = taille;
        in = 0;
        out = 0;
        nbMess = 0;
    }

    public synchronized void depose(Object obj) {
        while (nbMess == taille) {    // si plein
            try {
                wait();                // attends non-plein
            } catch (InterruptedException e) {}
        }
        tampon[in] = obj;
        nbMess++;
        in = (in + 1) % taille;
        notify();                // envoie un signal non-vide
    }

    public synchronized Object preleve() {
        while (nbMess == 0) {    // si vide
            try {
                wait();            // attends non-vide
            } catch (InterruptedException e) {}
        }
        Object obj = tampon[out];
        tampon[out] = null;    // supprime la ref a l'objet GC
        nbMess--;
        out = (out + 1) % taille;
        notify();                // envoie un signal non-plein
        return obj;
    }

}

class consommateur extends Thread {

    private tamponCirc tampon;
    
    public consommateur(tamponCirc tampon) {
        this.tampon = tampon;
    }

    public void run() {
        while (true) {
            System.out.println("je preleve "+((Integer)tampon.preleve()).toString());
            try {
		 
		 Thread.sleep((int)(Math.random()*200));    // attends jusqu'a 200 ms


            } catch (InterruptedException e) {}
        }
    }
}

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
                Thread.sleep((int)(Math.random()*400));    // attend jusqu'a 500 ms
                //Thread.sleep((int)(Math.random()*100));    // attend jusqu'a 100 ms
 

            } catch (InterruptedException e) {}
        }
    }
}

class TestTampon {

    public static void main(String args[]) {
        
        tamponCirc tampon = new tamponCirc(5);
        producteur prod = new producteur(tampon);
        consommateur cons = new consommateur(tampon);
    
        prod.start();
        cons.start();
      
    }

}
