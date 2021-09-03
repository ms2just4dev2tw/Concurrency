package jcip.ex07;

import java.math.BigInteger;
import java.util.concurrent.*;

/**
 * <h6>CodeList 7-5 PrimeProducer</h6>
 * <i>Using interruption for cancellation</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class PrimeProducer extends Thread {
	
	private final BlockingQueue<BigInteger> queue;

	PrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while (!Thread.currentThread().isInterrupted())
				queue.put(p = p.nextProbablePrime());
		} catch (InterruptedException consumed) {
			/* Allow thread to exit */
		}
	}

	public void cancel() {
		interrupt();
	}
}
