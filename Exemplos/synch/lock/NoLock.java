package synch.lock;

public class NoLock implements Lock {

	@Override
	public void acquireLock() {
		// do nothing!
	}

	@Override
	public void releaseLock() {
		// do nothing!
	}
	
	public static void main(String[] args) {
		Lock lock = new NoLock();
		LockCounter counter = new LockCounter(lock);
		counter.start(1000);
	}
}