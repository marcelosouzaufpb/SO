package synch.lock;

import java.util.concurrent.Semaphore;

public class SemaphoreLock implements Lock {

	private Semaphore mutex;
	
	public SemaphoreLock() {
		this.mutex = new Semaphore(1);
	}
	
	@Override
	public void acquireLock() throws InterruptedException {
		mutex.acquire();
	}

	@Override
	public void releaseLock() {
		mutex.release();
	}
	
	public static void main(String[] args) {
		Lock lock = new SemaphoreLock();
		LockCounter counter = new LockCounter(lock);
		counter.start(1000);
	}

}
