package synch.lock;

import java.util.Arrays;

public class PetersonCounter implements Counter {
	
	static final int PRODUCER_ID = 0;
	static final int CONSUMER_ID = 1;
	
	volatile int count;
    volatile int turn;
    volatile boolean[] flag;
    
	public PetersonCounter(int count) {
		this.count = count;
		this.flag = new boolean[2];
	}
	
	public void increment() {
		flag[PRODUCER_ID] = true;
		turn = CONSUMER_ID;
		while (turn == CONSUMER_ID && flag[CONSUMER_ID]);
		count++; // critical section
		//System.out.println("[PRODUCER] " + this.toString());
		flag[PRODUCER_ID] = false;
	}
	
	public void decrement() {
		flag[CONSUMER_ID] = true;
		turn = PRODUCER_ID;
		while (turn == PRODUCER_ID && flag[PRODUCER_ID]);
		count--; // critical section
		//System.out.println("[CONSUMER] " + this.toString());
		flag[CONSUMER_ID] = false;
	}

	@Override
	public int getCount() {
		return count;
	}
	
	@Override
	public String toString() {
		return "count = " + count + "; turn = " + turn + "; flag = " +  Arrays.toString(flag);
	}

	public static void main(String[] args) {
		PetersonCounter counter = new PetersonCounter(0);
		System.out.println("Iniciando com contador = " + counter.getCount());
		int times = 1000;
		Thread incrementThread = new Thread(new IncrementThread(counter, times), "Producer");
		Thread decrementThread = new Thread(new DecrementThread(counter, times), "Consumer");
		incrementThread.start();
		decrementThread.start();
		try {
			incrementThread.join();
			decrementThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finalizando com contador = " + counter.getCount());
	}
}
