package Tests;

/* Voir CM suite des classes
    synchronized = mutex = exclusion mutuelle
        ils pourraient suffir mais dans beaucoup de cas il est nécessaire d'utiliser wait/Notify
        Attention : les synchronized ralentissent la JVM, à utiliser avec parcimonie
        double synchronized tombe toujours à l'examen
*/

public class TamponCirc {

}

public class Producteur extends Thread {
    private TamponCirc tampon;
    private int val = 0;

    public Producteur(TamponCirc tampon) {
        this.tampon = tampon;
    }

    public void run() {
        vhile(true) {
            System.out.println("Je dépose" + val);
            tampon.depose(val++);
            try {
                Thread.sleep((int)())
            } catch(InterruptedException ie) {}
        }
    }
}

public class TestTampon {

}
