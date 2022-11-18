
import java.util.concurrent.Semaphore;
class tamponCirc {

    private Object tampon[];
    private Semaphore sMutex,sPlaceLibre,sArticle;
    private int en,hors;

  

  

    public tamponCirc (int taille) {
        tampon = new Object[taille];
     
        sMutex = new Semaphore(1,true);
        sPlaceLibre = new Semaphore(taille,true);// true FIFO
	sArticle = new Semaphore(0,true); //
	
        en = 0;
        hors = 0;
    }

    public  void depose(Object obj) {
	sPlaceLibre.acquireUninterruptibly();
        sMutex.acquireUninterruptibly();
        tampon[en] = obj;
	en = (en + 1) % tampon.length;
        sMutex.release();
	sArticle.release();

    }

    public Object preleve() {
	sArticle.acquireUninterruptibly();
	sMutex.acquireUninterruptibly();
        Object obj = tampon[hors];
        tampon[hors] = null;    // supprime la ref a l'objet
        hors = (hors + 1) % tampon.length;
	sMutex.release();
	sPlaceLibre.release();
	return obj;
    }

}


