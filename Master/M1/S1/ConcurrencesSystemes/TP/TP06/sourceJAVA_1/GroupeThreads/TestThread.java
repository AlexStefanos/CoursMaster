  class Toto extends Thread {
	  private int delay;
	  
	  public Toto(ThreadGroup groupe,String nom){ // SON GROUPE ET SON PTIT NOM
		  super(groupe,nom);
	  }
	  
	  public void setDelay(int delay){ // delay d'attente pour le sleep du run
		  this.delay=delay;
	  }

          public void run() {
                   
	    
	    //System.out.println("CurrentThread : run "+Thread.currentThread());
	     
		try {
		    Thread.sleep(delay);
		} catch ( Exception  e) {
		    
		} ;  
	    
	    System.out.println("Fin "+Thread.currentThread());
          }
  }

  public  class TestThread {
   
   public static void main (String[] args) {
	   
     ThreadGroup groupeToto = new ThreadGroup("Groupe de Totos "); // GROUPE DE THTREADS
     Toto toto= new Toto(groupeToto,"un toto"); //  TOTO DANS LE GROUPE groupeToto
     toto.setDelay(2000);
       
       
     Toto unAutreToto= new Toto(groupeToto,"un autre toto");
     unAutreToto.setDelay(5000);
     
 
     
     
     System.out.println("Threads Toto Actifs "+ groupeToto.activeCount()); // 0
     // pas de thread Toto starté
     
     System.out.println("toto.start();");
     toto.start();
    
     System.out.print("Threads Toto Actifs "+ groupeToto.activeCount() + ": "); // 1
     Thread [] listeTh   = new Thread[groupeToto.activeCount()];
     groupeToto.enumerate(listeTh); // LES THREADS DU GROUPES
     for (Thread  th : listeTh){
	     System.out.print(th + " "); // un Toto
     }
     System.out.println();
     
     System.out.println("unAutreToto.start();");
     unAutreToto.start();
     
     
     System.out.print("Threads Toto Actifs "+ groupeToto.activeCount() + ": "); //2
     listeTh = new Thread[groupeToto.activeCount()];
     groupeToto.enumerate(listeTh);
     for (Thread  th : listeTh){
	     System.out.print(th + " "); // un Toto et un Autre Toto
     }
     System.out.println();
     
   
     
     try {    Thread.sleep(2000);} catch ( Exception  e) {} ;
     System.out.println("Threads Toto Actifs "+ groupeToto.activeCount()); // 1 
                    // il doit rester un autre Toto qui est encore dans le sleep
   
  
   } // end of main ()
   
   
 }

  
