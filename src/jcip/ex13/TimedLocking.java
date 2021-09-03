package jcip.ex13;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * <h6>CodeList 13-4 TimedLocking</h6>
 * <i>Locking with a time budget</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class TimedLocking {
	
	private Lock lock = new ReentrantLock();

	public boolean trySendOnSharedLine(String message, long timeout,
			TimeUnit unit) throws InterruptedException {
		long nanosToLock = unit.toNanos(timeout)
				- estimatedNanosToSend(message);
		if (!lock.tryLock(nanosToLock, NANOSECONDS))
			return false;
		try {
			return sendOnSharedLine(message);
		} finally {
			lock.unlock();
		}
	}

	private boolean sendOnSharedLine(String message) {
		/* send something */
		return true;
	}

	long estimatedNanosToSend(String message) {
		return message.length();
	}
}
