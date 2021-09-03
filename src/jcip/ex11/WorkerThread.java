package jcip.ex11;

import java.util.concurrent.*;

/**
 * <h6>CodeList 11-1 WorkerThread</h6>
 * <i>Serialized access to a task queue</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class WorkerThread extends Thread {

	private final BlockingQueue<Runnable> queue;

	public WorkerThread(BlockingQueue<Runnable> queue) {
		this.queue = queue;
	}

	public void run() {
		while (true) {
			try {
				Runnable task = queue.take();
				task.run();
			} catch (InterruptedException e) {
				break; /* Allow thread to exit */
			}
		}
	}
}
