 
import java.util.concurrent.*;

class Producer extends Thread {

   private int cpt=0;
   private final BlockingQueue<Integer> queue;
   Producer(BlockingQueue<Integer> q) { queue = q; }
   public void run() {
     try {
	 while(true) { 
	     queue.put(produce()); 
	     Thread.sleep((int)(Math.random()*500));   
        }
       
     } catch (InterruptedException ex) { }
   }
   Integer produce() {
       System.out.println(this+" produce "+(Integer)cpt);
       return  cpt++ ;
   }
 }

 class Consumer extends Thread {
   private final BlockingQueue<Integer> queue;
   Consumer(BlockingQueue<Integer> q) { queue = q; }
   public void run() {
     try {
       while(true) {
	   consume(queue.take());
	   Thread.sleep((int)(Math.random()*500));  
       }
     } catch (InterruptedException ex) { };
   }
   void consume(Integer x) { 
       System.out.println(this+" consume "+(Integer)x);
     }
 }

 class ProducteurConsommateur {
   static public void main(String[] args) {
        
     LinkedBlockingQueue<Integer> q = new LinkedBlockingQueue<Integer>();
     Producer p = new Producer(q);
     Consumer c1 = new Consumer(q);
     Consumer c2 = new Consumer(q);
     p.start();
     c1.start();
     c2.start();
   }
 }
 



/*
Each of the five queues offers something different:

    * ArrayBlockingQueue: A bounded queue backed by an array

    * LinkedBlockingQueue: An optionally bounded queue backed by linked nodes

    * PriorityBlockingQueue: An unbounded priority queue backed by a priority heap

    * DelayQueue: A time-based scheduling queue backed by a priority heap

    * SynchronousQueue: A simple rendezvous mechanism utilizing the
      BlockingQueue interface 

*/
