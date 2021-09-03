package jcip.ex15;

import java.util.concurrent.locks.*;

import net.jcip.annotations.*;

/**
 * <h6>CodeList 15-4 ReentrantLockPseudoRandom</h6>
 * <i>Random number generator using ReentrantLock</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class ReentrantLockPseudoRandom extends PseudoRandom {
	
	private final Lock lock = new ReentrantLock(false);
	private int seed;

	ReentrantLockPseudoRandom(int seed) {
		this.seed = seed;
	}

	public int nextInt(int n) {
		lock.lock();
		try {
			int s = seed;
			seed = calculateNext(s);
			int remainder = s % n;
			return remainder > 0 ? remainder : remainder + n;
		} finally {
			lock.unlock();
		}
	}
}
