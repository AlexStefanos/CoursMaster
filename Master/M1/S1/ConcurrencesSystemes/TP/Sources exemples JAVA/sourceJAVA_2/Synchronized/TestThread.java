
class Compteur {

    int val=0;
    public  int getVal(){
    //public synchronized int getVal(){
			return val;
    }
    public void setVal( int v){
    //public synchronized void setVal( int v){
			  val =  v;
    }
    public  void inc(int p){
    //public synchronized void inc(int p){
	int v =  getVal(); // reentrant
	Thread.currentThread().yield();
	val = v + p;
    }

}


class Th extends Thread {
     
    Compteur unCompteur;
    public Th(Compteur cpt){
	unCompteur=cpt;
    }  
    
    public void run() {
	// compute primes larger than minPrime
	System.out.println("th run");
	System.out.println("CurrentThread : "+Thread.currentThread());
	for(int i=0;i<1000;i++) {
            unCompteur.inc(1);
	    System.out.println(this +"  val : " + unCompteur.getVal());
	      

	}
    }
}

public  class TestThread {
    
    public static void main (String[] args) {
	Compteur unC = new Compteur();
	Th th;
	    for (int i=0;i<25;i++){
              (new Th(unC)).start();
	     

	    }

  
   } // end of main ()
   
   
 }

  
