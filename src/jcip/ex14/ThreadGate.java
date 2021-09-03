package jcip.ex14;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 14-9 ThreadGate</h6>
 * <i>Recloseable gate using wait and notifyAll</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class ThreadGate {
	
	// CONDITION-PREDICATE: opened-since(n) (isOpen || generation>n)
	@GuardedBy("this")
	private boolean isOpen;
	@GuardedBy("this")
	private int generation;

	public synchronized void close() {
		isOpen = false;
	}

	public synchronized void open() {
		++generation;
		isOpen = true;
		notifyAll();
	}

	// BLOCKS-UNTIL: opened-since(generation on entry)
	public synchronized void await() throws InterruptedException {
		int arrivalGeneration = generation;
		while (!isOpen && arrivalGeneration == generation)
			wait();
	}
}
