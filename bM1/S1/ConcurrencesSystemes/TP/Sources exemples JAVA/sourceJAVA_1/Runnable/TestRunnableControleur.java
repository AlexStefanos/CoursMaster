 class TotoC implements Runnable { // et peut extendre une classe XXXX

   protected Thread unThread;
  
   public TotoC() {
            
             unThread = new Thread((Runnable)this);
   }
   public void start() {   // pour avoir la meme interface qu’un thread
     unThread.start();
   }
   public void join() throws InterruptedException {
     unThread.join();
   }
   
    public void run() {
                   // compute primes larger than minPrime
	    System.out.println(this+"  toto run");
          }
    
}

  public  class TestRunnableControleur{
   
   public static void main (String[] args) {
     TotoC toto= new TotoC();
     
     toto.start(); // COMME UN TREAD 
     try {
        toto.join();
     } catch ( Exception  e) {
       
     } // end of try-catch
     
   } // end of main ()
   
   
 }

  
