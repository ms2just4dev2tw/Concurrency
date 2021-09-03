package jcip.ex07;

import java.util.concurrent.*;

/**
 * <h6>CodeList 7-8 InterruptBorrowedThread</h6>
 * <i>Scheduling an interrupt on a borrowed thread</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class TimedRun1 {
	
	private static final ScheduledExecutorService cancelExec = Executors
			.newScheduledThreadPool(1);

	public static void timedRun(Runnable r, long timeout, TimeUnit unit) {
		final Thread taskThread = Thread.currentThread();
		cancelExec.schedule(new Runnable() {
			public void run() {
				taskThread.interrupt();
			}
		}, timeout, unit);
		r.run();
	}
}
