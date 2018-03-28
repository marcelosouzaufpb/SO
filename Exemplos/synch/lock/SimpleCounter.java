package synch.lock;

public class SimpleCounter implements Counter {

	int count;
    
	public SimpleCounter() {
		this.count = 0;
	}
	
	public void increment() {
		count++;
	}
	
	public void decrement() {
		count--;
	}

	public int getCount() {
		return count;
	}

	public static void main(String[] args) {
		SimpleCounter counter = new SimpleCounter();
		System.out.println("Iniciando com contador = " + counter.getCount());
		int times = 1000;
		Thread incrementThread = new Thread(new IncrementThread(counter, times));
		Thread decrementThread = new Thread(new DecrementThread(counter, times));
		incrementThread.start();
		decrementThread.start();
		try {
			incrementThread.join();
			decrementThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finalizando com contador = " + counter.getCount());
	}
}