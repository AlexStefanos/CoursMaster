 
 
class Producer extends Thread {

   private int cpt=0;
   private final MyLinkedBlockingQueue<Integer> queue;
   Producer(MyLinkedBlockingQueue<Integer> q) { queue = q; }
   public void run() {
     try {
	 while(true) { 
	     queue.put(produce()); 
	     Thread.sleep((int)(Math.random()*100));   
        }
       
     } catch (InterruptedException ex) { }
   }
   Integer produce() {
       System.out.println("Producteur produce "+(Integer)cpt);
       return  cpt++;
   }
 }

 class Consumer extends Thread {
   private final MyLinkedBlockingQueue<Integer> queue;
   Consumer(MyLinkedBlockingQueue<Integer> q) { queue = q; }
   public void run() {
     try {
       while(true) {
	   consume(queue.take2());
	   Thread.sleep((int)(Math.random()*500));  
       }
     } catch (InterruptedException ex) { };
   }
   void consume(Integer x) { 
       System.out.println("Conso " +this+" consume "+(Integer)x);
     }
 }

 public class ProducteurConsommateurMyLBQ {
   static public void main(String[] args) {
        
     MyLinkedBlockingQueue<Integer> q = new MyLinkedBlockingQueue<Integer>();
     Producer p = new Producer(q);
     Consumer c1 = new Consumer(q);
     Consumer c2 = new Consumer(q);
     p.start();
     c1.start();
     c2.start();
   }
 }
 

 
