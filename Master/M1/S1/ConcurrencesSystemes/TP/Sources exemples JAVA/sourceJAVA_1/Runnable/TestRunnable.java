  class Toto implements Runnable  {

          public void run() {
                   // compute primes larger than minPrime
	    System.out.println(this+"  toto runnable");
          }
  }


  public  class TestRunnable {
   
   public static void main (String[] args) {
     Toto toto= new Toto(); // UN  RUNNABLE
     Thread p = new Thread((Runnable)toto); // UN THREAD ATTACHE AU RUNNABLE
                                        // LE THREAD  VA EXECUTER LE RUNNABLE
     p.start();
     try {
        p.join();
     } catch ( Exception  e) {
       
     } // end of try-catch
     
   } // end of main ()
   
   
 }

  
