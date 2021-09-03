package jcip.ex07;

import java.math.BigInteger;
import java.util.concurrent.*;

/**
 * <h6>CodeList 7-3 BrokenPrimeProducer</h6>
 * <i>Unreliable cancellation that can leave producers stuck in a blocking
 * operation</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
class BrokenPrimeProducer extends Thread {
	
	private final BlockingQueue<BigInteger> queue;
	private volatile boolean cancelled = false;

	BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while (!cancelled)
				queue.put(p = p.nextProbablePrime());
		} catch (InterruptedException consumed) {
		}
	}

	public void cancel() {
		cancelled = true;
	}
}
