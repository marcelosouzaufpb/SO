package synch.lock;

public class MonitorCounter implements Counter {

	int count;
	Object mutex1 = new Object();
	Object mutex2 = new Object();

	public MonitorCounter() {
		this.count = 0;
	}

	public void increment() {
		//System.out.println("Iniciando incremento");
		synchronized (mutex1) {
			count++;
		}
		//System.out.println("Terminei.");
	}

	public void decrement() {
		//System.out.println("Iniciando decremento");
		synchronized (mutex1) {
			count--;
		}
		//System.out.println("Terminei");
	}

	public int getCount() {
		return count;
	}
	
	public void start(int times) {
		System.out.println("Iniciando com contador = " + getCount());
		Thread incrementThread = new Thread(new IncrementThread(this, times));
		Thread decrementThread = new Thread(new DecrementThread(this, times));
		incrementThread.start();
		decrementThread.start();
		try {
			incrementThread.join();
			decrementThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finalizando com contador = " + getCount());
	}
	
	public static void main(String[] args) {
		MonitorCounter counter = new MonitorCounter();
		counter.start(1000);
	}
}
