import java.io.*;


class  Th extends Thread{

  static  int Cpt =0;

  //static   int getVal() {
  static  synchronized int getVal() {
	  return Cpt;
  }

  //static   int Incremente(){
  static  synchronized int Incremente(){
	int val=getVal();
	//Thread.currentThread().yield();
        Cpt = ++val;
        return Cpt;
  }

  public Th(){
  }
  public void run(){
      for(int i=0;i<1000;i++){// iwhile(true){
      System.out.println(this + "CPT: " +Incremente());
    }
  }

}

public class Test {

    public static void main(String args[]) {
        
        Th t1 = new Th();
        Th t2 = new Th();
	Th t3 = new Th();
        Th t4 = new Th();
     
        t1.start();
        t2.start();
	t3.start();
        t4.start();
/*
        try {
          t1.join();
          t2.join();
        } catch (InterruptedException e) {}
        * */
  }

}

