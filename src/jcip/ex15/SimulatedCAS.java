package jcip.ex15;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 15-1 SimulatedCAS</h6>
 * <i>Simulated CAS operation</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */

@ThreadSafe
public class SimulatedCAS {

	@GuardedBy("this")
	private int value;

	public synchronized int get() {
		return value;
	}

	public synchronized int compareAndSwap(int expectedValue, int newValue) {
		int oldValue = value;
		if (oldValue == expectedValue)
			value = newValue;
		return oldValue;
	}

	public synchronized boolean compareAndSet(int expectedValue, int newValue) {
		return (expectedValue == compareAndSwap(expectedValue, newValue));
	}
}
