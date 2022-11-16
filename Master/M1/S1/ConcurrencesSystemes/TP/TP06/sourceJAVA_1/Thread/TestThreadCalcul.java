 





 class TotoCalcul extends Thread {

          public void run() {
                   
	    System.out.println("Toto calcule ");
	    System.out.println("CurrentThread : "+Thread.currentThread());
	    int i=0;
	    while(true) {
		i++;
		double f=1.0;
		for(long  j=1;j<100000000;j++) 
		    f/=j;
		     
		System.out.println(this +"  i" + i);
		 
	    }
          }
  }

  public  class TestThreadCalcul {
   
   public static void main (String[] args) {
     TotoCalcul toto= new TotoCalcul();
      toto.start();
     //toto.run();
     TotoCalcul unAutreToto= new TotoCalcul();
      unAutreToto.start();
     TotoCalcul unAutreToto2= new TotoCalcul();
      unAutreToto2.start();
     TotoCalcul unAutreToto3= new TotoCalcul();
      unAutreToto3.start();
      TotoCalcul unAutreToto4= new TotoCalcul();
      unAutreToto4.start();
     TotoCalcul unAutreToto5= new TotoCalcul();
      unAutreToto5.start();
     // LES QUATRE THREADS SE PARTAGENT LES COEURS DU PROCESSEUR
     // AVEC EVENTUELLEMENT DES DEPLACMENTS DE THREADS De COEURS VERS D'AUTRES
   
  
   } // end of main ()
   
   
 }

  
