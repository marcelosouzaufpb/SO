package synch.lock;

public interface Lock {

	public void acquireLock() throws InterruptedException;
	public void releaseLock() throws InterruptedException;
	
}
