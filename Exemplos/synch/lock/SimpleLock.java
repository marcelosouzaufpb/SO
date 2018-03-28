package synch.lock;

public class SimpleLock implements Lock {

	private boolean lockValue;
	
	public SimpleLock() {
		lockValue = false;
	}
	
	@Override
	public void acquireLock() {
		while (lockValue == true);
		lockValue = true;
	}

	@Override
	public void releaseLock() {
		lockValue = false;
	}
	
	public static void main(String[] args) {
		Lock lock = new SimpleLock();
		LockCounter counter = new LockCounter(lock);
		counter.start(1000);
	}

}
