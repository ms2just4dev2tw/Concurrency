package jcip.ex05;

import java.util.concurrent.*;

/**
 * <h6>CodeList 5-10 TaskRunnable</h6>
 * <p/>
 * Restoring the interrupted status so as not to swallow the interrupt
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class TaskRunnable implements Runnable {

	BlockingQueue<Task> queue;

	public void run() {
		try {
			processTask(queue.take());
		} catch (InterruptedException e) {
			// restore interrupted status
			Thread.currentThread().interrupt();
		}
	}

	void processTask(Task task) {
		// Handle the task
	}

	interface Task {
	}
}
