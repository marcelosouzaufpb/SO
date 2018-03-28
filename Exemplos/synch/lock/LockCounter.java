package synch.lock;

public class LockCounter implements Counter {

	int count;
	Lock lock;

	public LockCounter(Lock lock) {
		this.count = 0;
		this.lock = lock;
	}

	public void increment() {
		try {
			lock.acquireLock();
			count++;
			lock.releaseLock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void decrement() {
		try {
			lock.acquireLock();
			count--;
			lock.releaseLock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getCount() {
		return count;
	}
	
	public void start(int times) {
		System.out.println("Iniciando com contador = " + getCount());
		Thread incrementThread = new Thread(new IncrementThread(this, times));
		Thread decrementThread = new Thread(new DecrementThread(this, times));
		incrementThread.start();
		decrementThread.start();
		try {
			incrementThread.join();
			decrementThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finalizando com contador = " + getCount());
	}
}
