package jcip.ex07;

import java.util.concurrent.*;
import static java.util.concurrent.Executors.newScheduledThreadPool;
import static jcip.ex05.LaunderThrowable.launderThrowable;

/**
 * <h6>CodeList 7-9 TimedRun2</h6>
 * <i>Interrupting a task in a dedicated thread<i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class TimedRun2 {
	
	private static final ScheduledExecutorService cancelExec = newScheduledThreadPool(1);

	public static void timedRun(final Runnable r, long timeout, TimeUnit unit)
			throws InterruptedException {
		class RethrowableTask implements Runnable {
			private volatile Throwable t;

			public void run() {
				try {
					r.run();
				} catch (Throwable t) {
					this.t = t;
				}
			}

			void rethrow() {
				if (t != null)
					throw launderThrowable(t);
			}
		}

		RethrowableTask task = new RethrowableTask();
		final Thread taskThread = new Thread(task);
		taskThread.start();
		cancelExec.schedule(new Runnable() {
			public void run() {
				taskThread.interrupt();
			}
		}, timeout, unit);
		taskThread.join(unit.toMillis(timeout));
		task.rethrow();
	}
}
