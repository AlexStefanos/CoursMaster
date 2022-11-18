
import java.io.*;


class SynchroL {
  
   private Object lock; // Un objet pour obtebir un verrou
   private boolean isLock; // verrou pris ou pas
   
   public SynchroL() {
      lock = new Object(); // creer un objet donc un verrou
      isLock = true; // verrou pris
   }
   public void testSynchro(){
       synchronized (lock){ // prend le verrou sur l'objet lock
	   if (isLock) // le deuxieme thread n'a pas termier
	       try {
		   lock.wait(); // attente relanche le verrou
	       }  catch (InterruptedException e) {};
       }
   }
    public void ok(){
	synchronized(lock){ // prend le verou pour le notify
	    lock.notifyAll();  // reveille eventuellement les threads bloques
	    isLock = false; // le premeir thread ne doit pas etre bloque  
	}
    }
}


class  Client extends Thread{
  SynchroL unLock;
  public Client(SynchroL l){
    this.unLock = l;
  }
  public void run(){
  try {
	Thread.sleep(Math.round(Math.random() *10000));
    }catch (Exception e){};
    System.out.println("Client : Demande Service "+ this);
    unLock.testSynchro();
   System.out.println("Client : Service Obtenu "+ this);
    //...
  }

}

class  Serveur extends Thread {
  SynchroL unLock;
  public Serveur(SynchroL l){
    this.unLock = l;
  }
  public void run(){
    //Etape1;
    System.out.println("Serveur : Mise en Place du Service");  
    try {
	Thread.sleep(5000);
    }catch (Exception e){};
    unLock.ok(); // preveint de la fin de t&
    System.out.println("Serveur : Service disponible");
    //Etape2;
    //...
  }  
public void etape1(){
    }

}

public class TestMultiClient {

    public static void main(String args[]) {
        
        SynchroL sync = new SynchroL();
        Serveur serveur = new Serveur(sync);
   
        for (int i=0 ;i < 10 ;i++) 

         (new Client(sync)).start();
            
            
         try {
	   Thread.sleep(1000);  // attente  1 seconde 
         }catch (Exception e){};
              
         serveur.start();
       
       
       
	 try {
	   Thread.sleep(10000);
         }catch (Exception e){};
         
         
         
       for (int i=0 ;i < 10 ;i++) 
	 new Client(sync).start();
       
        try {
          serveur.join();
        } catch (InterruptedException e) {}
  }

}

