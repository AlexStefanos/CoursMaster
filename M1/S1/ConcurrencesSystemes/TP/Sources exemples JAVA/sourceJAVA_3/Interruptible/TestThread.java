  class Toto extends Thread {

      
      // a decommenter pour empecher exception interrup
      //
      public void interrupt(){
            System.out.println(this +  "interrup recu...");
      };
      
      
     

 
      public void run() {
	  System.out.println(this +  "lance ...    Sleep 10 secondes");
	  
	 	  
	  try {
		  sleep(10000);
	  } catch (InterruptedException e) { 

	      System.out.println(this +"Exception levee  + " + e);
	  }
	  System.out.println(this +"Sortie du sleep");

	  

      }
  }

  public  class TestThread {
   
   public static void main (String[] args) {
     Toto toto= new Toto();
     toto.start();
     
     System.out.println("Main attend 2 secondes");
     try {
	 Thread.sleep(2000);
     } catch ( Exception  e) {} ; 
     System.out.println(Thread.currentThread()+" envoie  toto.interrupt() ");
     toto.interrupt();
     System.out.println("toto.isInterrupted()" +  toto.isInterrupted());
   } 
   
 }

  
