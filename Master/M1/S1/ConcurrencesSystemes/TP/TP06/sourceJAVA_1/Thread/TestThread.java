  class Toto extends Thread {

  	public void run() {
                   // compute primes larger than minPrime
  		System.out.println("toto run");
  		System.out.println("CurrentThread : "+Thread.currentThread());
  		for(int i=0;i<10;i++) {
  			System.out.println(this +"  i" + i);
  			try {
  				Thread.sleep(500);
  			} catch ( Exception  e) {
  				
  			} ;  
  		}
  	}
  }

  public  class TestThread {
  	
  	public static void main (String[] args) {
  		Toto toto= new Toto();
  		toto.start();
	     //toto.run();
  		Toto unAutreToto= new Toto();
  		unAutreToto.start();
  		System.out.println("main CurrentThread : "+Thread.currentThread());


  		for(int i=0;i<10;i++) {
  			System.out.println(Thread.currentThread()+"main  i" + i);
  			try {
  				Thread.sleep(1000);

  			} catch ( Exception  e) {
  				
  			} ;  
  		}
  		try {
  			System.in.read();
  		} catch ( Exception  e) {
  			
		     } // end of try-catch
		     
		     try {
		     	
		     	toto.join();
		     	unAutreToto.join();
		     } catch ( Exception  e) {
		     	
		     } // end of try-catch
		     


		     
	   } // end of main ()
	   
	   
	}

	
