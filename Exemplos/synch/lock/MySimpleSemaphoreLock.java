package synch.lock;

public class MySimpleSemaphoreLock implements Lock {

	private int value;
	
	public MySimpleSemaphoreLock(int permits) {
		this.value = permits;
	}
	
	@Override
	public void acquireLock() {
		while (value <= 0);
		value--;
	}

	@Override
	public void releaseLock() {
		value++;
	}
	
	public static void main(String[] args) {
		Lock lock = new MySimpleSemaphoreLock(1);
		LockCounter counter = new LockCounter(lock);
		counter.start(1000);
	}
}
