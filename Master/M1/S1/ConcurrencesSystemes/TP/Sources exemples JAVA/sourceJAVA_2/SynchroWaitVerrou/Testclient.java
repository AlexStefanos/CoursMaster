import java.io.*;


class SynchroL {
  
   private Object lock; // Un objet pour obtebir un verrou
   private boolean isLock; // Le premier thread doit etre bloque
   
   public SynchroL() {
      lock = new Object(); // creer un objet donc un verrou
      isLock = true; //   bloque
   }
   public void testSynchro(){
       synchronized (lock){ // prend le verrou sur l'objet lock
	   if (isLock) // 
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
      
      //int delay=1000 ; 
      int delay=10000 ; 
   try {
	System.out.println("Client demarre dans  "+delay + "ms");
	Thread.sleep(delay);
    }catch (Exception e){};
      
    System.out.println("Client : Demande Service");
    unLock.testSynchro();
   System.out.println("Client : Service Obtenu");
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
    System.out.println("Serveur : Mise en Place du service");  
    try {
	Thread.sleep(5000);
    }catch (Exception e){};
    unLock.ok(); // previent de la fin de t&
    System.out.println("Serveur : Service disponible ");
    //Etape2;
    //...
  }  


}

public class Test1client {

    public static void main(String args[]) {
        
        SynchroL sync = new SynchroL();
        Serveur serveur = new Serveur(sync);
   
        Client client = new Client(sync);
              
	      serveur.start();
        client.start();
        try {
          serveur.join();
        } catch (InterruptedException e) {}
  }

}

