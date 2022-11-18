  class Toto extends Thread {

          public void run() {
                    
	    System.out.println(this + " run");
	    System.out.println("toto sleep 10 s");
	    try {
		Thread.sleep(10000);
	    } catch ( Exception  e) {
		
	    } ; 
	     System.out.println("toto fin");
	  } 

  }

  public  class TestThread {
   
   public static void main (String[] args) {
     Toto toto= new Toto();
     toto.setDaemon(true); // COMMENTER ET DECOMMENTER CETTE LIGNE
       
     toto.start();
	    
	    
     System.out.println("FIN DU main CurrentThread : "+Thread.currentThread());
  
    // L'EXECUTION DOIT S'ARRETER DE SUITE OU ATTENDRE LA FIN DU THREAD
  } // end of main ()
   
   
 }

  
