package synch.lock;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicGetAndSetLock implements Lock {

	private AtomicBoolean lockValue;
	
	public AtomicGetAndSetLock() {
		this.lockValue = new AtomicBoolean(false);
	}
	
	@Override
	public void acquireLock() {
		while(lockValue.getAndSet(true));
	}

	@Override
	public void releaseLock() {
		lockValue.set(false);
	}
	
	public static void main(String[] args) {
		Lock lock = new AtomicGetAndSetLock();
		LockCounter counter = new LockCounter(lock);
		counter.start(1000);
	}
}
