
import java.util.*;




public class MyLinkedBlockingQueue<E>
{

    class BlockingThread{
	
	private  Thread  thread;
	private  E  o;
	
	public BlockingThread(Thread th){
	    this.thread=th;
	}
	public void setObject(E o){
	    this.o=o;
	}
	public E getObject(){
	    return o;
	}
	public Thread getThread(){
	    return thread;
	}
	
    }
    
    
    private LinkedList<BlockingThread> listeTh;
    private LinkedList<E> listeObject;
    

    public MyLinkedBlockingQueue()
    {
	listeTh = new LinkedList<BlockingThread>();
	listeObject =  new LinkedList<E>();
    }

    public synchronized void put(E o){
    
	if (listeTh.size() != 0){
	    BlockingThread tb =  listeTh.remove();
	    tb.setObject(o);
	    synchronized(tb.getThread()){
		tb.getThread().notify();
	    }
	} else
	    listeObject.add(o);
    }
    
    public  E take() {
	Thread	my=Thread.currentThread();
	BlockingThread tb =null;
	synchronized(my){
	    synchronized(this){
		if (listeObject.size() != 0)
		    return  listeObject.remove();
	        tb = new BlockingThread(my);
		listeTh.add(tb);
	    }
	    try{
		my.wait();
		return tb.getObject();
	    }catch(InterruptedException e){};
	}
	return null;
    }
    
    
    public  E take2() {   //  avec le return à la fin
	Thread	my=Thread.currentThread();
	E result=null;
	
	synchronized(my){
	    BlockingThread tb =null;
	    synchronized(this){
		if (listeObject.size() != 0)
		    result=  listeObject.remove();
		else {
	           tb = new BlockingThread(my);
		   listeTh.add(tb);
	        }
	    }
	    if ( result == null) {
	      try{
		my.wait();
		result=tb.getObject();
	      }catch(InterruptedException e){};
	    }
	}
	return result;
    }
    
     


}
