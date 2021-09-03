package jcip.ex14;

import java.util.concurrent.locks.*;

import net.jcip.annotations.*;

/**
 * <h6>CodeList 14-14 OneShotLatch</h6>
 * <i>Binary latch using AbstractQueuedSynchronizer</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class OneShotLatch {
	
	private final Sync sync = new Sync();

	public void signal() {
		sync.releaseShared(0);
	}

	public void await() throws InterruptedException {
		sync.acquireSharedInterruptibly(0);
	}

	private class Sync extends AbstractQueuedSynchronizer {
		protected int tryAcquireShared(int ignored) {
			// Succeed if latch is open (state == 1), else fail
			return (getState() == 1) ? 1 : -1;
		}

		protected boolean tryReleaseShared(int ignored) {
			setState(1); // Latch is now open
			return true; // Other threads may now be able to acquire

		}
	}
}
