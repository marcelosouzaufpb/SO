package synch.lock;

public class IncrementThread implements Runnable {
	
	private Counter counter;
	private int times;

	public IncrementThread(Counter counter, int times) {
		this.counter = counter;
		this.times = times;
	}
	
	@Override
	public void run() {
		System.out.println("Incrementando contador " + times + " vezes.");
		for (int i = 0; i < times; i++) {
			counter.increment();
		}
	}
}
