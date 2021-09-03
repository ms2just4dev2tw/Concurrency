package jcip.ex14;

import net.jcip.annotations.*;

/**
 * <h6>CodeList 14-6|14-8 BoundedBuffer</h6>
 * <i>Bounded buffer using condition queues</i>
 * <i>Using conditional notification in BoundedBuffer.put.</i>
 * <p>
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
	// CONDITION PREDICATE: not-full (!isFull())
	// CONDITION PREDICATE: not-empty (!isEmpty())
	public BoundedBuffer() {
		this(100);
	}

	public BoundedBuffer(int size) {
		super(size);
	}

	// BLOCKS-UNTIL: not-full
	public synchronized void put(V v) throws InterruptedException {
		while (isFull())
			wait();
		doPut(v);
		notifyAll();
	}

	// BLOCKS-UNTIL: not-empty
	public synchronized V take() throws InterruptedException {
		while (isEmpty())
			wait();
		V v = doTake();
		notifyAll();
		return v;
	}

	// BLOCKS-UNTIL: not-full
	// Alternate form of put() using conditional notification
	public synchronized void alternatePut(V v) throws InterruptedException {
		while (isFull())
			wait();
		boolean wasEmpty = isEmpty();
		doPut(v);
		if (wasEmpty)
			notifyAll();
	}
}
