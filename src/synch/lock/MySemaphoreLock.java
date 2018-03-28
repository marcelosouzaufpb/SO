package synch.lock;

public class MySemaphoreLock implements Lock {

	private int value;
	
	public MySemaphoreLock(int permits) {
		this.value = permits;
	}
	
	@Override
	public synchronized void acquireLock() throws InterruptedException {
		value--;
		if (value < 0) {
			wait();
		}
	}

	@Override
	public synchronized void releaseLock() throws InterruptedException {
		value++;
		if (value <= 0) {
			notify();
		}
	}
	
	public static void main(String[] args) {
		Lock lock = new MySemaphoreLock(1);
		LockCounter counter = new LockCounter(lock);
		counter.start(1000);
	}
}
