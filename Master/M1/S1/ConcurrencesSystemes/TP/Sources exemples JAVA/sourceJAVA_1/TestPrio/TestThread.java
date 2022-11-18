  class Toto extends Thread {
      private int prio;
      public Toto(int prio){
	  this.prio=prio;
      }


          public void run() {
                  
	    
	    System.out.println("CurrentThread : "+Thread.currentThread() +
			       " priority " + this.getPriority());
	    float val =1;
	    
            float x=0;
	    for(int i=0;i<5;i++){ 
		val +=1;
		
		// un  peu de calcul
		for( int p=0;p<100000000;p++) val /= 2.;
		 
	
	    }
	   
	    System.out.println("FIN "  +this + "prio "+ prio );
	
          }
  }

  public  class TestThread {
   
   public static void main (String[] args) {


       System.out.println("java TestThread");
       
       System.out.println("ou  java -XX:+UseThreadPriorities TestThread");



       System.out.println("lancement de Nthreads avec prio min,max et normal"+ 
			  Thread.MIN_PRIORITY +":"+Thread.MAX_PRIORITY + ":" +Thread.NORM_PRIORITY );
         
       
       
   
        
     for (int i=0;i < 20 ; i++){
	      
	 Toto toto= new Toto(Thread.NORM_PRIORITY );
	 toto.setPriority(Thread.NORM_PRIORITY );
	 toto.start();
	 toto= new Toto(Thread.MIN_PRIORITY);
	 toto.setPriority(Thread.MIN_PRIORITY);  
         toto.start();
         toto= new Toto(Thread.MAX_PRIORITY );
	 toto.setPriority(Thread.MAX_PRIORITY );
	 toto.start();
     }
   
     
  
   // LES THREADS DE FORTES PRIORITES AURAIENT DU SE FINIR AVANT LES AUTRES
   // CE N'EST PAS LE CAS SUR JAVA openjdk 11.0.8 2020-07-14
   // VOUS AUREZ PEUT ETRE PLUS DE CHANCE
   //  AVEC
   // java -XX:+UseThreadPriorities TestThread
   // ou
   // sudo java -XX:+UseThreadPriorities TestThread
  
   } // end of main ()
   
   
 }

  
