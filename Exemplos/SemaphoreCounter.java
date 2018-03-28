package synch.lock;

import java.util.concurrent.Semaphore;

public class SemaphoreCounter implements Counter {

	int count;
	Semaphore mutex;
    
	public SemaphoreCounter() {
		this.count = 0;
		this.mutex = new Semaphore(1);
	}
	
	public void increment() {
		try {
			mutex.acquire();
			count++;
			mutex.release();
		} catch (InterruptedException e) {}
	}
	
	public void decrement() {
		try {
			mutex.acquire();
			count--;
			mutex.release();
		} catch (InterruptedException e) {}
	}

	public int getCount() {
		return count;
	}

	public static void main(String[] args) {
		SemaphoreCounter counter = new SemaphoreCounter();
		System.out.println("Iniciando com contador = " + counter.getCount());
		int times = 1000;
		Thread incrementThread = new Thread(new IncrementThread(counter, times));
		Thread decrementThread = new Thread(new DecrementThread(counter, times));
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