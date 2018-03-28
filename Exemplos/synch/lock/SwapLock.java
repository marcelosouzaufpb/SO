package synch.lock;

import java.util.concurrent.atomic.AtomicBoolean;

public class SwapLock implements Lock {

	private volatile AtomicBoolean lockValue;

	public SwapLock() {
		lockValue = new AtomicBoolean(false);
	}

	public synchronized void swap(AtomicBoolean otherValue) {
		boolean tmp = this.lockValue.get();
		this.lockValue = new AtomicBoolean(otherValue.get());
		otherValue = new AtomicBoolean(tmp);
	}

	@Override
	public void acquireLock() {
		AtomicBoolean tmp = new AtomicBoolean(true);
		do {
			this.swap(tmp);
		} while (tmp.get());
	}

	@Override
	public void releaseLock() {
		lockValue.set(false);
	}
	
	public static void main(String[] args) {
		Lock lock = new SwapLock();
		LockCounter counter = new LockCounter(lock);
		counter.start(1000);
	}

}
