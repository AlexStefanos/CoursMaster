import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class UseBarrier extends Thread{

	static CyclicBarrier barrier;



	public UseBarrier(){

	}


	public void run() {
		int ite=1;
		while (true) {
			System.out.println("ite "+ite+" " +Thread.currentThread());
			ite++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				barrier.await();
			} catch (InterruptedException ex) {
				return;
			} catch (BrokenBarrierException ex) {
				return;
			}
			 
			
		}
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int N = 5;

		barrier = new CyclicBarrier(N);  
		 
		for (int i = 0; i < N; ++i)
			new UseBarrier().start();


	}
}


