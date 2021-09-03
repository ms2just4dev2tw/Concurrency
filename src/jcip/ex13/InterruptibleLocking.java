package jcip.ex13;

import java.util.concurrent.locks.*;

/**
 * <h6>CodeList 13-5 InterruptibleLocking</h6>
 * <i>Interruptible lock acquisition.</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class InterruptibleLocking {
	
	private Lock lock = new ReentrantLock();

	public boolean sendOnSharedLine(String message) throws InterruptedException {
		lock.lockInterruptibly();
		try {
			return cancellableSendOnSharedLine(message);
		} finally {
			lock.unlock();
		}
	}

	private boolean cancellableSendOnSharedLine(String message)
			throws InterruptedException {
		/* send something */
		return true;
	}
}
