package jcip.ex08;

import java.util.concurrent.*;

import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 8-4 BoundedExecutor</h6>
 * <i>Using a Semaphore to throttle task submission</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class BoundedExecutor {
	
	private final Executor exec;
	private final Semaphore semaphore;

	public BoundedExecutor(Executor exec, int bound) {
		this.exec = exec;
		this.semaphore = new Semaphore(bound);
	}

	public void submitTask(final Runnable command) throws InterruptedException {
		semaphore.acquire();
		try {
			exec.execute(new Runnable() {
				public void run() {
					try {
						command.run();
					} finally {
						semaphore.release();
					}
				}
			});
		} catch (RejectedExecutionException e) {
			semaphore.release();
		}
	}
}
