package synch.lock;

public class DecrementThread implements Runnable {
	
	private Counter counter;
	private int times;

	public DecrementThread(Counter counter, int times) {
		this.counter = counter;
		this.times = times;
	}
	
	@Override
	public void run() {
		System.out.println("Decrementando contador " + times + " vezes.");
		for (int i = 0; i < times; i++) {
			counter.decrement();
		}
	}
}