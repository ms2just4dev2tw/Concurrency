package jcip.ex07;

import java.util.concurrent.*;

/**
 * <h6>CodeList 7-7 NoncancelableTask</h6>
 * <i>Noncancelable task that restores interruption before exit</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class NoncancelableTask {

	public Task getNextTask(BlockingQueue<Task> queue) {
		boolean interrupted = false;
		try {
			while (true) {
				try {
					return queue.take();
				} catch (InterruptedException e) {
					interrupted = true;
					// fall through and retry
				}
			}
		} finally {
			if (interrupted)
				Thread.currentThread().interrupt();
		}
	}

	interface Task {
	}
}
